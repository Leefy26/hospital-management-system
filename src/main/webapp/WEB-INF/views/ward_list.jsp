<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>病房信息管理</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; margin-right: 10px; }
        .add-btn { display:block; width: 120px; margin: 20px auto; padding: 10px; text-align: center; background-color: #28a745; color: white; border-radius: 5px;}
    </style>
</head>
<body>
<h2 style="text-align: center;">病房列表</h2>
<a href="/ward/add" class="add-btn">新增病房</a>
<table>
    <tr>
        <th>ID</th>
        <th>病房号</th>
        <th>总床位数</th>
        <th>已用床位数</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${wardList}" var="w">
        <tr>
            <td>${w.id}</td>
            <td>${w.wardNo}</td>
            <td>${w.totalBeds}</td>
            <td>${w.usedBeds}</td>
            <td>
                <a href="/ward/edit/${w.id}">修改</a>
                <a href="/ward/delete/${w.id}" onclick="return confirm('确定要删除这个病房吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>