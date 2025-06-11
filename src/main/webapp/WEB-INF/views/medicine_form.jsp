<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${action == 'add' ? '新增' : '修改'}药品</title>
    <style> /* 省略部分通用样式 */
    form { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
    div { margin-bottom: 15px; }
    label { display: inline-block; width: 100px; }
    input[type="text"], input[type="number"] { width: 250px; padding: 5px; }
    input[type="submit"] { padding: 10px 20px; background-color: #17a2b8; color: white; border: none; cursor: pointer; border-radius: 5px;}
    </style>
</head>
<body>
<h2 style="text-align: center;">${action == 'add' ? '新增' : '修改'}药品</h2>
<form action="${action == 'add' ? '/medicine/add' : '/medicine/update'}" method="post">
    <c:if test="${action == 'edit'}">
        <input type="hidden" name="id" value="${medicine.id}">
    </c:if>
    <div>
        <label for="name">药品名称:</label>
        <input type="text" id="name" name="name" value="${medicine.name}" required>
    </div>
    <div>
        <label for="specification">规格:</label>
        <input type="text" id="specification" name="specification" value="${medicine.specification}">
    </div>
    <div>
        <label for="manufacturer">生产厂家:</label>
        <input type="text" id="manufacturer" name="manufacturer" value="${medicine.manufacturer}">
    </div>
    <div>
        <label for="unitPrice">单价 (元):</label>
        <input type="number" id="unitPrice" name="unitPrice" value="${medicine.unitPrice}" required min="0" step="0.01">
    </div>
    <div>
        <input type="submit" value="${action == 'add' ? '确认新增' : '确认修改'}">
    </div>
</form>
</body>
</html>