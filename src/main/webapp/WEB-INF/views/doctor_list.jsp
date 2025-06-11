<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>医生信息管理</title>
    <link rel="stylesheet" href="/css/style.css"> <%-- 假设我们把样式抽离到外部文件 --%>
    <style> /* 也可以继续使用内部样式 */
    table { border-collapse: collapse; width: 80%; margin: 20px auto; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    a { text-decoration: none; margin-right: 10px; }
    .add-btn { display:block; width: 120px; margin: 20px auto; padding: 10px; text-align: center; background-color: #007bff; color: white; border-radius: 5px;}
    </style>
</head>
<body>
<h2 style="text-align: center;">医生列表</h2>
<a href="/doctor/add" class="add-btn">新增医生</a>
<table>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>职称</th>
        <th>所属科室</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${doctorList}" var="doc">
        <tr>
            <td>${doc.id}</td>
            <td>${doc.name}</td>
            <td>${doc.title}</td>
            <td>${doc.department.name}</td> <%-- 直接通过 doc.department 拿到关联的科室对象，再获取其name --%>
            <td>
                <a href="/doctor/edit/${doc.id}">修改</a>
                <a href="/doctor/delete/${doc.id}" onclick="return confirm('确定要删除这位医生吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>