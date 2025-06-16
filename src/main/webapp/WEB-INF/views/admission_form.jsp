<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>病人住院登记</title>
    <style>
    body { font-family: sans-serif; }
    .container { width: 60%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; }
    .form-group { margin-bottom: 15px; }
    .form-group label { display: block; margin-bottom: 5px; }
    .form-group input, .form-group select, .form-group textarea { width: 98%; padding: 8px; border-radius: 4px; border: 1px solid #ddd; }
    .btn-submit { padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer; border-radius: 5px; }
    .message { padding: 10px; margin-bottom: 15px; border-radius: 5px; }
    .success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb;}
    .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb;}
    </style>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;">病人住院登记</h2>

    <c:if test="${not empty successMessage}">
        <div class="message success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="message error">${errorMessage}</div>
    </c:if>

    <form action="/admission/register" method="post">
        <fieldset>
            <legend>病人基本信息</legend>
            <div class="form-group">
                <label for="name">姓名:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="idCard">身份证号:</label>
                <input type="text" id="idCard" name="idCard" required>
            </div>
            <div class="form-group">
                <label for="gender">性别:</label>
                <select id="gender" name="gender">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <div class="form-group">
                <label for="age">年龄:</label>
                <input type="number" id="age" name="age" min="0">
            </div>
            <div class="form-group">
                <label for="phone">联系电话:</label>
                <input type="text" id="phone" name="phone">
            </div>
            <div class="form-group">
                <label for="address">家庭住址:</label>
                <input type="text" id="address" name="address">
            </div>
            <div class="form-group">
                <label for="allergyHistory">过敏史:</label>
                <textarea id="allergyHistory" name="allergyHistory" rows="3"></textarea>
            </div>
        </fieldset>

        <fieldset>
            <legend>住院信息分配</legend>
            <div class="form-group">
                <label for="departmentSelect">分配科室:</label>
                <select id="departmentSelect" name="departmentId" required>
                    <option value="">--请选择科室--</option>
                    <c:forEach items="${departmentList}" var="dept">
                        <option value="${dept.id}">${dept.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="doctorSelect">主治医生:</label>
                <select id="doctorSelect" name="doctorId" required disabled>
                    <option value="">--请先选择科室--</option>
                </select>
            </div>
            <div class="form-group">
                <label for="wardId">分配病房:</label>
                <select id="wardId" name="wardId" required>
                    <option value="">--请选择病房--</option>
                    <c:forEach items="${wardList}" var="w">
                        <option value="${w.id}">${w.wardNo} (可用床位: ${w.totalBeds - w.usedBeds})</option>
                    </c:forEach>
                </select>
            </div>
        </fieldset>

        <div class="form-group" style="text-align: center;">
            <input type="submit" value="确认登记" class="btn-submit">
        </div>
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const departmentSelect = document.getElementById('departmentSelect');
        const doctorSelect = document.getElementById('doctorSelect');

        departmentSelect.addEventListener('change', function() {
            const departmentId = this.value;

            console.log('当前选择的科室ID是: ' + departmentId);

            doctorSelect.innerHTML = '<option value="">--请先选择科室--</option>';
            doctorSelect.disabled = true;

            if (departmentId) {

                const requestUrl = '/api-test/get-doctors/' + departmentId;

                console.log('准备请求的URL是: ' + requestUrl);

                fetch(requestUrl)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('网络响应错误，状态码: ' + response.status);
                        }
                        return response.json();
                    })
                    .then(doctors => {
                        doctorSelect.innerHTML = '<option value="">--请选择医生--</option>';
                        doctors.forEach(doctor => {
                            const option = document.createElement('option');
                            option.value = doctor.id;
                            option.textContent = doctor.name + ' (' + doctor.title + ')';
                            doctorSelect.appendChild(option);
                        });
                        doctorSelect.disabled = false;
                    })
                    .catch(error => console.error('获取医生列表失败:', error));
            }
        });
    });
</script>

</body>
</html>