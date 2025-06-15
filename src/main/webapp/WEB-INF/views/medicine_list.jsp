<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>药品信息管理</title>
    <style>
        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 20px 0;
        }
        .page-header h2 {
            margin: 0;
        }
        .header-btn {
            text-decoration: none;
            padding: 8px 15px;
            color: white;
            border-radius: 5px;
            font-size: 14px;
            white-space: nowrap;
        }
        .btn-back {
            background-color: #6c757d;
        }
        .btn-add {
            background-color: #007bff;
        }
        table { border-collapse: collapse; width: 90%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; margin-right: 10px; }
    </style>
</head>
<body>
<div class="page-header">
    <a href="/admin/dashboard" class="header-btn btn-back">返回主页</a>
    <h2>药品信息列表</h2>
    <a href="/medicine/add" class="header-btn btn-add">新增药品</a>
</div>
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