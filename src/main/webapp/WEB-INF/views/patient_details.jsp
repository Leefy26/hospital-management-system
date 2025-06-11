<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>病人详情 - ${patient.name}</title>
    <style>
        body { font-family: sans-serif; background-color: #f8f9fa; }
        .container { width: 80%; margin: 20px auto; }
        .card { background: white; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); margin-bottom: 20px; padding: 20px; }
        .card h2, .card h3 { border-bottom: 2px solid #eee; padding-bottom: 10px; margin-top: 0; }
        .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; }
        .info-grid p { margin: 5px 0; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; font-size: 14px; }
        th { background-color: #f2f2f2; }
        .back-link { display: inline-block; margin-bottom: 20px; color: #007bff; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <a href="/patient/list" class="back-link">&larr; 返回病人列表</a>

    <div class="card">
        <h2>病人基本信息</h2>
        <div class="info-grid">
            <p><strong>姓名:</strong> ${patient.name}</p>
            <p><strong>病人ID:</strong> ${patient.id}</p>
            <p><strong>性别:</strong> ${patient.gender}</p>
            <p><strong>年龄:</strong> ${patient.age}</p>
            <p><strong>身份证号:</strong> ${patient.idCard}</p>
            <p><strong>联系电话:</strong> ${patient.phone}</p>
            <p><strong>当前状态:</strong> ${patient.status}</p>
            <p><strong>主治医生:</strong> ${patient.doctor.name}</p>
            <p><strong>入院日期:</strong> ${patient.admissionDate}</p>
            <p><strong>出院日期:</strong> ${patient.dischargeDate}</p>
            <p><strong>过敏史:</strong> ${patient.allergyHistory}</p>
            <p><strong>家庭住址:</strong> ${patient.address}</p>
        </div>
    </div>

    <div class="card" style="text-align: right;">
        <c:if test="${patient.status == '住院'}">
            <a href="/prescription/new/${patient.id}" style="text-decoration: none; padding: 8px 12px; background-color: #28a745; color: white; border-radius: 5px; margin-left: 10px;">开立处方</a>
            <a href="/labtest/new/${patient.id}" style="text-decoration: none; padding: 8px 12px; background-color: #ffc107; color: #212529; border-radius: 5px; margin-left: 10px;">开检验单</a>
            <a href="/patient/discharge/${patient.id}" style="text-decoration: none; padding: 8px 12px; background-color: #dc3545; color: white; border-radius: 5px; margin-left: 10px;">办理出院</a>
        </c:if>
    </div>

    <div class="card">
        <h3>历史处方</h3>
        <c:if test="${not empty prescriptions}">
            <c:forEach items="${prescriptions}" var="pres" varStatus="status">
                <%-- varStatus="status" 可以让我们拿到循环的序号 --%>
                <h4>处方 ${status.count} (ID: ${pres.id})</h4>
                <p><strong>诊断:</strong> ${pres.diagnosis}</p>
                <p><strong>开方医生:</strong> ${pres.doctor.name}</p>
                <p><strong>开立时间:</strong> ${pres.createdAt}</p>
                <table>
                    <tr><th>药品名称</th><th>数量</th><th>用法备注</th><th>小计</th></tr>
                    <c:forEach items="${pres.details}" var="detail">
                        <tr>
                            <td>${detail.medicine.name}</td>
                            <td>${detail.quantity}</td>
                            <td>${detail.notes}</td>
                            <td>${detail.subtotal} 元</td>
                        </tr>
                    </c:forEach>
                </table>
                <p style="text-align: right; font-weight: bold;">处方${status.count}金额: ${pres.totalFee} 元</p>
                <hr>
            </c:forEach>

            <p style="text-align: right; font-size: 18px; font-weight: bold; color: #28a745;">
                处方总计金额: ${totalPrescriptionFee} 元
            </p>
        </c:if>
        <c:if test="${empty prescriptions}">
            <p>暂无处方记录。</p>
        </c:if>
    </div>

    <div class="card">
        <h3>历史检验单</h3>
        <c:if test="${not empty labTests}">
            <c:forEach items="${labTests}" var="test" varStatus="status">
                <h4>检验单 ${status.count} (ID: ${test.id})</h4>
                <p><strong>开单医生:</strong> ${test.doctor.name}</p>
                <p><strong>开单时间:</strong> ${test.testTime}</p>
                <table>
                    <tr><th>项目名称</th><th>费用</th></tr>
                    <tr>
                        <td>${test.labItem.name}</td>
                        <td>${test.fee} 元</td>
                    </tr>
                </table>
                <p><strong>检验结果:</strong> ${empty test.result ? '暂无结果' : test.result}</p>
                <hr>
            </c:forEach>
            <p style="text-align: right; font-size: 18px; font-weight: bold; color: #28a745;">
                处方总计金额: ${totalLabFee} 元
            </p>        </c:if>
        <c:if test="${empty labTests}">
            <p>暂无检验记录。</p>
        </c:if>
    </div>
</div>
</body>
</html>