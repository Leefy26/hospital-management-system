<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%-- 引入格式化标签库 --%>
<html>
<head>
    <title>病人信息列表</title>
    <style>
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
    <div class="header">
        <h2>病人信息总览</h2>
        <a href="/admission/register" class="add-btn">登记新病人</a>
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