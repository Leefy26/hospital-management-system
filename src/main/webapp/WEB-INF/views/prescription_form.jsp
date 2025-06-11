<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>为 ${patient.name} 开立处方</title>
    <style>
        body { font-family: sans-serif; }
        .container { width: 80%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input, .form-group select, .form-group textarea { width: 98%; padding: 8px; border-radius: 4px; border: 1px solid #ddd; }
        .btn { padding: 10px 15px; color: white; border: none; cursor: pointer; border-radius: 5px; text-decoration: none; display: inline-block; }
        .btn-primary { background-color: #007bff; }
        .btn-success { background-color: #28a745; }
        .btn-danger { background-color: #dc3545; }
        .medicine-row { display: flex; align-items: center; margin-bottom: 10px; padding: 10px; border: 1px dashed #eee; }
        .medicine-row > div { margin-right: 10px; }
        .message { padding: 10px; margin-bottom: 15px; border-radius: 5px; }
        .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb;}
    </style>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;">为病人【${patient.name}】开立新处方</h2>

    <c:if test="${not empty errorMessage}">
        <div class="message error">${errorMessage}</div>
    </c:if>

    <form action="/prescription/save" method="post">
        <input type="hidden" name="patientId" value="${patient.id}">

        <div class="form-group">
            <label for="diagnosis">临床诊断:</label>
            <textarea id="diagnosis" name="diagnosis" rows="3" required></textarea>
        </div>
        <div class="form-group">
            <label for="doctorId">开方医生:</label>
            <select id="doctorId" name="doctorId" required>
                <option value="">--请选择医生--</option>
                <c:forEach items="${doctorList}" var="doc">
                    <option value="${doc.id}">${doc.name}</option>
                </c:forEach>
            </select>
        </div>

        <fieldset>
            <legend>药品详情</legend>
            <div id="medicine-container">
            </div>
            <button type="button" id="add-medicine-btn" class="btn btn-primary">添加药品</button>
        </fieldset>

        <div class="form-group" style="text-align: center; margin-top: 20px;">
            <input type="submit" value="保存处方" class="btn btn-success">
        </div>
    </form>
</div>

<div id="medicine-row-template" style="display: none;">
    <div class="medicine-row">
        <div>
            <label>药品:</label>
            <select name="medicineIds" class="medicine-select" required>
                <option value="">--请选择药品--</option>
                <c:forEach items="${medicineList}" var="med">
                    <option value="${med.id}">${med.name} (${med.specification}) - ${med.unitPrice}元</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label>数量:</label>
            <input type="number" name="quantities" min="1" value="1" style="width: 60px;" required>
        </div>
        <div>
            <label>用法备注:</label>
            <input type="text" name="notes" placeholder="如：每日三次，饭后服用" style="width: 300px;">
        </div>
        <button type="button" class="btn btn-danger" onclick="removeMedicineRow(this)">移除</button>
    </div>
</div>

<script>
    // JS代码，用于实现动态添加和移除
    document.getElementById('add-medicine-btn').addEventListener('click', function() {
        // 1. 找到模板并克隆
        const template = document.getElementById('medicine-row-template').firstElementChild;
        const newRow = template.cloneNode(true);

        // 2. 将克隆的行添加到容器中
        document.getElementById('medicine-container').appendChild(newRow);
    });

    function removeMedicineRow(button) {
        // 移除按钮所在的整个药品行
        button.parentElement.remove();
    }
</script>

</body>
</html>