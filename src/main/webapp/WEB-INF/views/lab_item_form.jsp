<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action == 'add' ? '新增' : '修改'}检验项目</title>
    <style>
        form { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        div { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        input[type="text"], input[type="number"] { width: 250px; padding: 5px; }
        input[type="submit"] { padding: 10px 20px; background-color: #fd7e14; color: white; border: none; cursor: pointer; border-radius: 5px;}
    </style>
</head>
<body>
<h2 style="text-align: center;">${action == 'add' ? '新增' : '修改'}检验项目</h2>
<form action="${action == 'add' ? '/labitem/add' : '/labitem/update'}" method="post">

    <%-- 如果是修改操作，则包含一个隐藏的ID字段 --%>
    <c:if test="${action == 'edit'}">
        <input type="hidden" name="id" value="${labItem.id}">
    </c:if>

    <div>
        <label for="name">项目名称:</label>
        <input type="text" id="name" name="name" value="${labItem.name}" required>
    </div>
    <div>
        <label for="departmentName">所属部门:</label>
        <input type="text" id="departmentName" name="departmentName" value="${labItem.departmentName}" placeholder="如: 检验科, 放射科">
    </div>
    <div>
        <label for="price">价格 (元):</label>
        <input type="number" id="price" name="price" value="${labItem.price}" required min="0" step="0.01">
    </div>
    <div>
        <input type="submit" value="${action == 'add' ? '确认新增' : '确认修改'}">
    </div>
</form>
</body>
</html>