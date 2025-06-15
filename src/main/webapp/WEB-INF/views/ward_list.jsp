<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>病房信息管理</title>
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
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; margin-right: 10px; }
    </style>
</head>
<body>
<div class="page-header">
    <a href="/admin/dashboard" class="header-btn btn-back">返回主页</a>
    <h2>病房列表</h2>
    <a href="/ward/add" class="header-btn btn-add">新增病房</a>
</div>
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