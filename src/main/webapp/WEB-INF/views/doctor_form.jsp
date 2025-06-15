<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action == 'add' ? '新增' : '修改'}医生</title>
    <style>
        form { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        div { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        input[type="text"], select { width: 262px; padding: 5px; }
        input[type="submit"] { padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer; border-radius: 5px;}
    </style>
</head>
<body>
<h2 style="text-align: center;">${action == 'add' ? '新增' : '修改'}医生</h2>
<form action="${action == 'add' ? '/doctor/add' : '/doctor/update'}" method="post">
    <c:if test="${action == 'edit'}">
        <input type="hidden" name="id" value="${doctor.id}">
    </c:if>

    <!-- 医生姓名 -->
    <div>
        <label for="name">医生姓名:</label>
        <input type="text" id="name" name="name" value="${doctor.name}" required>
    </div>

    <!-- 职称 -->
    <div>
        <label for="title">职　　称:</label>
        <input type="text" id="title" name="title" value="${doctor.title}">
    </div>

    <!-- 所属科室 -->
    <div>
        <label for="department">所属科室:</label>
        <select id="department" name="department.id" required>
            <option value="">--请选择科室--</option>
            <c:forEach items="${departmentList}" var="dept">
                <option value="${dept.id}" ${doctor.department.id == dept.id ? 'selected' : ''}>
                        ${dept.name}
                </option>
            </c:forEach>
        </select>
    </div>

    <!-- 只在新增时显示 -->
    <c:if test="${action == 'add'}">
        <hr>
        <div>
            <label for="username">登录用户名:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">初始密码:</label>
            <input type="password" id="password" name="password" required>
        </div>
    </c:if>

    <!-- 提交按钮 -->
    <div>
        <input type="submit" value="${action == 'add' ? '确认新增' : '确认修改'}">
    </div>
</form>
