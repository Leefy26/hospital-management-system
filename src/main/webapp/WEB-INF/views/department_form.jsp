<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action == 'add' ? '新增' : '修改'}科室</title>
    <style> /* 简单美化一下表单 */
    form { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
    div { margin-bottom: 15px; }
    label { display: inline-block; width: 100px; }
    input[type="text"] { width: 250px; padding: 5px; }
    input[type="submit"] { padding: 10px 20px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<h2 style="text-align: center;">${action == 'add' ? '新增' : '修改'}科室</h2>
<form action="${action == 'add' ? '/department/add' : '/department/update'}" method="post">
    <c:if test="${action == 'edit'}">
        <input type="hidden" name="id" value="${department.id}">
    </c:if>
    <div>
        <label for="name">科室名称:</label>
        <input type="text" id="name" name="name" value="${department.name}" required>
    </div>
    <div>
        <label for="location">科室位置:</label>
        <input type="text" id="location" name="location" value="${department.location}">
    </div>
    <div>
        <label for="tel">联系电话:</label>
        <input type="text" id="tel" name="tel" value="${department.tel}">
    </div>
    <div>
        <input type="submit" value="${action == 'add' ? '确认新增' : '确认修改'}">
    </div>
</form>
</body>
</html>