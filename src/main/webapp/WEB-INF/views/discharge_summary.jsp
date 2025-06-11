<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>为 ${summary.patient.name} 办理出院结算</title>
    <style>
        body { font-family: sans-serif; }
        .container { width: 70%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; }
        .patient-info, .fee-summary { margin-bottom: 20px; }
        .fee-details table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .fee-details th, .fee-details td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        .total-fee { font-size: 24px; font-weight: bold; color: #dc3545; text-align: right; }
        .actions { text-align: center; margin-top: 30px; }
        .btn-submit { padding: 10px 20px; background-color: #dc3545; color: white; border: none; cursor: pointer; border-radius: 5px;}
    </style>
</head>
<body>
<div class="container">
    <h2 style="text-align: center;">出院结算单</h2>

    <div class="patient-info">
        <h3>病人信息</h3>
        <p><strong>姓名:</strong> ${summary.patient.name}</p>
        <p><strong>身份证号:</strong> ${summary.patient.idCard}</p>
        <p><strong>入院日期:</strong> ${summary.patient.admissionDate}</p> <%-- 已修正 --%>
    </div>

    <div class="fee-details">
        <h3>费用明细 (处方)</h3>
        <table>
            <tr>
                <th>处方ID</th>
                <th>诊断</th>
                <th>开立时间</th>
                <th>金额 (元)</th>
            </tr>
            <c:forEach items="${summary.prescriptions}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.diagnosis}</td>
                    <td>${p.createdAt}</td> <%-- 已修正 --%>
                    <td>${p.totalFee}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="fee-details">
        <h3>费用明细 (检验)</h3>
        <table>
            <tr>
                <th>检验单ID</th>
                <th>项目名称</th>
                <th>开单时间</th>
                <th>金额 (元)</th>
            </tr>
            <c:forEach items="${summary.labTests}" var="test">
                <tr>
                    <td>${test.id}</td>
                    <td>${test.labItem.name}</td>
                    <td>${test.testTime}</td> <%-- 已修正 --%>
                    <td>${test.fee}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="fee-summary">
        <p class="total-fee">应付总金额: ${summary.totalFee} 元</p>
    </div>

    <div class="actions">
        <form action="/patient/discharge" method="post">
            <input type="hidden" name="patientId" value="${summary.patient.id}">
            <input type="submit" value="确认出院并结算" class="btn-submit" onclick="return confirm('请确认费用无误，此操作将正式为病人办理出院。')">
        </form>
    </div>
</div>
</body>
</html>