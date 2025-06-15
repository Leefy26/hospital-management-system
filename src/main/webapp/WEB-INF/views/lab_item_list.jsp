<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>检验项目管理</title>
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
        body { font-family: sans-serif; }
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; margin-right: 10px; }
        .container { width: 90%; margin: auto; }
    </style>
</head>
<body>
<div class="container">
    <div class="page-header">
        <a href="/admin/dashboard" class="header-btn btn-back">返回主页</a>
        <h2>检验项目列表</h2>
        <a href="/labitem/add" class="header-btn btn-add">新增检验项目</a>
    </div>
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