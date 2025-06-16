<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统后台管理</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

    <style>
        body {
            font-family: 'Microsoft YaHei', sans-serif;
            background-color: #f4f7f9;
            margin: 0;
            padding: 40px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
        }

        .dashboard-container {
            width: 100%;
            max-width: 1200px;
            text-align: center;
        }

        .dashboard-container h1 {
            color: #333;
            font-size: 36px;
            margin-bottom: 40px;
        }

        /* 【新】使用 CSS Grid 布局，让卡片网格化，更具响应性 */
        .dashboard-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 30px; /* 卡片之间的间距 */
        }

        .dashboard-card {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
            text-decoration: none;
            color: #444;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .dashboard-card:hover {
            transform: translateY(-8px); /* 鼠标悬停时，卡片向上浮动 */
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        .card-icon {
            font-size: 48px; /* 图标大小 */
            margin-bottom: 20px;
        }

        /* 为不同卡片设置不同的主题色 */
        .card-icon.color-1 { color: #007bff; }
        .card-icon.color-2 { color: #6610f2; }
        .card-icon.color-3 { color: #17a2b8; }
        .card-icon.color-4 { color: #28a745; }
        .card-icon.color-5 { color: #fd7e14; }
        .card-icon.color-6 { color: #20c997; }
        .card-icon.color-7 { color: #e83e8c; }


        .card-title {
            font-size: 18px;
            font-weight: 600;
        }

        .message {
            padding: 10px 20px;
            margin: 0 auto 30px;
            border-radius: 5px;
            display: inline-block;
            font-size: 16px;
        }
        .success { background-color: #d4edda; color: #155724; }
    </style>
</head>
<body>
<div class="dashboard-container">
    <h1>系统后台管理</h1>

    <c:if test="${not empty successMessage}">
        <p class="message success">${successMessage}</p>
    </c:if>

    <div class="dashboard-grid">
        <%-- 每个功能都是一个 a 标签包裹的卡片 --%>
        <a href="/admission/register" class="dashboard-card">
            <i class="fas fa-user-plus card-icon color-1"></i>
            <span class="card-title">登记新病人</span>
        </a>
        <a href="/department/list" class="dashboard-card">
            <i class="fas fa-sitemap card-icon color-2"></i>
            <span class="card-title">科室管理</span>
        </a>
        <a href="/doctor/list" class="dashboard-card">
            <i class="fas fa-user-md card-icon color-3"></i>
            <span class="card-title">医生管理</span>
        </a>
        <a href="/ward/list" class="dashboard-card">
            <i class="fas fa-procedures card-icon color-4"></i>
            <span class="card-title">病房管理</span>
        </a>
        <a href="/medicine/list" class="dashboard-card">
            <i class="fas fa-pills card-icon color-5"></i>
            <span class="card-title">药品管理</span>
        </a>
        <a href="/labitem/list" class="dashboard-card">
            <i class="fas fa-vial-virus card-icon color-6"></i>
            <span class="card-title">检验项目管理</span>
        </a>
        <a href="/logout" class="dashboard-card">
            <i class="fas fa-sign-out-alt card-icon color-7"></i>
            <span class="card-title">退出登录</span>
        </a>
    </div>
</div>
</body>
</html>