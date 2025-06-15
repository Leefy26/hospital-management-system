<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%-- 引入格式化标签库 --%>
<html>
<head>
    <title>病人信息列表</title>
    <style>
        /* 请将这些新样式添加到 <style> 标签内 */
        .doctor-info-card {
            background-color: #e9f5ff;
            border: 1px solid #b3d7ff;
            border-radius: 8px;
            padding: 15px 25px;
            margin-bottom: 20px;
            position: relative; /* 为了登出按钮定位 */
        }
        .doctor-info-card h3 {
            margin: 0 0 10px 0;
            color: #004085;
        }
        .doctor-info-card p {
            margin: 0;
            color: #31708f;
        }
        .doctor-name {
            color: #0056b3;
            font-weight: bold;
        }
        .logout-btn {
            position: absolute;
            top: 20px;
            right: 20px;
            padding: 5px 10px;
            background-color: #6c757d;
            color: white;
            border-radius: 5px;
            font-size: 12px;
        }
        body { font-family: sans-serif; }
        table { border-collapse: collapse; width: 95%; margin: 20px auto; font-size: 14px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; margin-right: 10px; color: #007bff; }
        .action-link-discharge { color: #dc3545; }
        .container { width: 95%; margin: auto; }
        .header { display: flex; justify-content: space-between; align-items: center; }
        .add-btn { display:inline-block; padding: 10px 15px; text-align: center; background-color: #007bff; color: white; border-radius: 5px;}
    </style>
</head>
<body>
<div class="container">
    <c:if test="${not empty currentDoctor}">
        <div class="doctor-info-card">
            <h3>您好，<span class="doctor-name">${currentDoctor.name}</span>！</h3>
            <p>
                <strong>您的ID:</strong> ${currentDoctor.id} |
                <strong>职称:</strong> ${currentDoctor.title} |
                <strong>所属科室:</strong> ${currentDoctor.department.name}
            </p>
            <a href="/logout" class="logout-btn">退出登录</a>
        </div>
        <hr style="border: none; border-top: 1px solid #eee; margin-bottom: 20px;">
    </c:if>


    <div class="header">
        <h2><c:if test="${not empty currentDoctor}">您的</c:if>病人信息总览</h2>
    </div>

    <table>
        <tr>
            <th>病人ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>身份证号</th>
            <th>状态</th>
            <th>入院日期</th>
            <th>所属科室</th>
            <th>主治医生</th>
            <th>病房号</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${patientList}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.gender}</td>
                <td>${p.age}</td>
                <td>${p.idCard}</td>
                <td>${p.status}</td>
                <td>
                        ${p.admissionDate}
                </td>
                    <%-- 这里体现了JPA关系映射的强大之处，可以直接通过 patient 对象获取关联对象的信息 --%>
                <td>${p.department.name}</td>
                <td>${p.doctor.name}</td>
                <td>${p.ward.wardNo}</td>
                <td>
                    <a href="/patient/details/${p.id}">详情</a>

                        <%-- 只有住院病人才可以进行医疗操作 --%>
                    <c:if test="${p.status == '住院'}">
                        <a href="/prescription/new/${p.id}" style="color: #28a745;">开立处方</a>
                        <a href="/labtest/new/${p.id}" style="color: #ffc107;">开检验单</a>
                        <a href="/patient/discharge/${p.id}" class="action-link-discharge" onclick="return confirm('您确定要为病人 ${p.name} 办理出院吗？')">出院</a>
                    </c:if>

                    <c:if test="${p.status == '出院'}">
                        <span>已出院</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>