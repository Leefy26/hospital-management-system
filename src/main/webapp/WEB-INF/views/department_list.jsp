<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>科室信息管理</title>
    <style> /* 简单美化一下表格 */
    table { border-collapse: collapse; width: 80%; margin: 20px auto; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    a { text-decoration: none; margin-right: 10px; }
    .add-btn { display:block; width: 120px; margin: 20px auto; padding: 10px; text-align: center; background-color: #4CAF50; color: white; }
    </style>
</head>
<body>
<h2 style="text-align: center;">科室列表</h2>
<a href="/department/add" class="add-btn">新增科室</a>
<table>
    <tr>
        <th>ID</th>
        <th>科室名称</th>
        <th>位置</th>
        <th>电话</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${departmentList}" var="dept">
        <tr>
            <td>${dept.id}</td>
            <td>${dept.name}</td>
            <td>${dept.location}</td>
            <td>${dept.tel}</td>
            <td>
                <a href="/department/edit/${dept.id}">修改</a>
                <a href="/department/delete/${dept.id}" onclick="return confirm('确定要删除这个科室吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>