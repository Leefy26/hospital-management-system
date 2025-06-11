<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>为 ${patient.name} 开立检验单</title>
    <style>
        body { font-family: sans-serif; }
        .container { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group select { width: 100%; padding: 8px; border-radius: 4px; border: 1px solid #ddd; }
        .btn-submit { padding: 10px 20px; background-color: #ffc107; color: #212529; border: none; cursor: pointer; border-radius: 5px;}
        .patient-info { background-color: #f8f9fa; padding: 10px; border-radius: 5px; margin-bottom: 20px;}
    </style>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;">开立检验单</h2>

    <div class="patient-info">
        <strong>病人姓名:</strong> ${patient.name} (ID: ${patient.id})
    </div>

    <%-- 【关键】请确保 action 指向 "/labtest/order"，method 是 "post" --%>
    <form action="/labtest/order" method="post">

        <input type="hidden" name="patientId" value="${patient.id}">

        <div class="form-group">
            <label for="doctorId">开单医生:</label>
            <select id="doctorId" name="doctorId" required>
                <option value="">--请选择医生--</option>
                <c:forEach items="${doctorList}" var="doc">
                    <option value="${doc.id}">${doc.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="labItemId">检验项目:</label>
            <select id="labItemId" name="labItemId" required>
                <option value="">--请选择检验项目--</option>
                <c:forEach items="${labItemList}" var="item">
                    <option value="${item.id}">${item.name} - ${item.price}元</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group" style="text-align: center; margin-top: 20px;">
            <input type="submit" value="确认开立" class="btn-submit">
        </div>
    </form>
</div>
</body>
</html>