<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>检验项目管理</title>
    <style>
        body { font-family: sans-serif; }
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; margin-right: 10px; }
        .container { width: 90%; margin: auto; }
        .add-btn { display:block; width: 150px; margin: 20px auto; padding: 10px; text-align: center; background-color: #fd7e14; color: white; border-radius: 5px;}
    </style>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;">检验项目列表</h2>
    <a href="/labitem/add" class="add-btn">新增检验项目</a>
    <table>
        <tr>
            <th>ID</th>
            <th>项目名称</th>
            <th>所属部门</th>
            <th>价格 (元)</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${labItemList}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.departmentName}</td>
                <td>${item.price}</td>
                <td>
                    <a href="/labitem/edit/${item.id}">修改</a>
                    <a href="/labitem/delete/${item.id}" onclick="return confirm('确定要删除这个检验项目吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>