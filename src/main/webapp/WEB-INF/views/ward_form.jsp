<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action == 'add' ? '新增' : '修改'}病房</title>
    <style>
        form { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        div { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        input[type="text"], input[type="number"] { width: 250px; padding: 5px; }
        input[type="submit"] { padding: 10px 20px; background-color: #28a745; color: white; border: none; cursor: pointer; border-radius: 5px;}
    </style>
</head>
<body>
<h2 style="text-align: center;">${action == 'add' ? '新增' : '修改'}病房</h2>
<form action="${action == 'add' ? '/ward/add' : '/ward/update'}" method="post">
    <c:if test="${action == 'edit'}">
        <input type="hidden" name="id" value="${ward.id}">
    </c:if>
    <div>
        <label for="wardNo">病房号:</label>
        <input type="text" id="wardNo" name="wardNo" value="${ward.wardNo}" required>
    </div>
    <div>
        <label for="totalBeds">总床位数:</label>
        <input type="number" id="totalBeds" name="totalBeds" value="${ward.totalBeds}" required min="0">
    </div>
    <div>
        <label for="usedBeds">已用床位数:</label>
        <input type="number" id="usedBeds" name="usedBeds" value="${ward.usedBeds}" required min="0">
        <%-- 在真实系统中，“已用床位数”通常由住院和出院流程自动更新，但在这里我们为了维护方便，先让它可以手动修改。 --%>
    </div>
    <div>
        <input type="submit" value="${action == 'add' ? '确认新增' : '确认修改'}">
    </div>
</form>
</body>
</html>