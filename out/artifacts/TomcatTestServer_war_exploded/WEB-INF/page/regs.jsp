<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/12/17
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
</head>
<body>
<form action="/regs/doregs">
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
                <input type="password" name="password">
            </td>
        </tr>
        <tr>
            <td>
                <p>性别（填 Female/Male ）：</p>
            </td>
            <td>
                <select name="sex">
                    <option></option>
                    <option value="Female">Female</option>
                    <option value="Male">Male</option>
                </select>
                <!--
                <input type="text" name="sex">
                -->
            </td>
        </tr>
        <tr>
            <td>
                <p>生日（yyyy-MM-dd）：</p>
            </td>
            <td>
                <input type="date" name="birthday">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">注册！</button>
            </td>
            <td>
                <p style="color: orangered">${regsErrorMessage}</p>
            </td>
        </tr>
    </table>
    <br>
</form>
</body>
</html>
