<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>药品信息管理</title>
    <style> /* 省略部分通用样式 */
    table { border-collapse: collapse; width: 90%; margin: 20px auto; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    a { text-decoration: none; margin-right: 10px; }
    .add-btn { display:block; width: 120px; margin: 20px auto; padding: 10px; text-align: center; background-color: #17a2b8; color: white; border-radius: 5px;}
    </style>
</head>
<body>
<h2 style="text-align: center;">药品信息列表</h2>
<a href="/medicine/add" class="add-btn">新增药品</a>
<table>
    <tr>
        <th>ID</th>
        <th>药品名称</th>
        <th>规格</th>
        <th>生产厂家</th>
        <th>单价 (元)</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${medicineList}" var="med">
        <tr>
            <td>${med.id}</td>
            <td>${med.name}</td>
            <td>${med.specification}</td>
            <td>${med.manufacturer}</td>
            <td>${med.unitPrice}</td>
            <td>
                <a href="/medicine/edit/${med.id}">修改</a>
                <a href="/medicine/delete/${med.id}" onclick="return confirm('确定要删除这个药品吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>