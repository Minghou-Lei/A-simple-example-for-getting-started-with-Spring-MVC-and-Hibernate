<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<head>
    <title>欢迎来到 BookStore 登录页面</title>
</head>
<h1>欢迎来到 <b style="color: darkgreen">BookStore</b> 登录界面</h1>
<form action="/login">
    <table>
        <tr>
            <td>
                <p>用户名：</p>
            </td>
            <td>
                <input type="text" name="username">
            </td>
        </tr>
        <tr>
            <td>
                <p>密码：</p>
            </td>
            <td>
                <input type="password" name="password"><br>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">登录</button>
            </td>
            <td>
                <p style="color: orangered">${errorMessage}</p>
            </td>
        </tr>
    </table>
</form>
<form action="/regs/toregs">
    没有账号？
    <button type="submit">注册</button>
</form>
</h1>