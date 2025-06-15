<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统登录</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: sans-serif;
            background-color: #f4f7f6;
        }
        .login-container {
            padding: 40px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            width: 320px;
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 30px;
            color: #333;
        }
        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box; /* 保证 padding 不会撑大宽度 */
        }
        .btn-login {
            width: 100%;
            padding: 12px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .btn-login:hover {
            background-color: #0056b3;
        }
        .message {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-size: 14px;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
        }
        .logout {
            background-color: #d4edda;
            color: #155724;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>住院管理信息系统</h2>

    <%-- 用于显示登录失败的提示 --%>
    <c:if test="${not empty loginError}">
        <div class="message error">${loginError}</div>
    </c:if>

    <%-- 用于显示成功登出的提示 --%>
    <c:if test="${not empty logoutMessage}">
        <div class="message logout">${logoutMessage}</div>
    </c:if>

    <%-- 登录表单，action必须是/login，method必须是post --%>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <%-- 输入框的 name 必须是 "username" --%>
            <input type="text" id="username" name="username" required autofocus>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <%-- 输入框的 name 必须是 "password" --%>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn-login">登 录</button>
    </form>
</div>
</body>
</html>