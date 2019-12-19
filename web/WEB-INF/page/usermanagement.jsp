<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/12/17
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理界面</title>
    <style>
        table {
            padding: 0;
            border-collapse: collapse;
        }

        td, th {
            border: 1px solid;
            padding: 6px 6px 6px 12px;
            color: #4f6b72;
            text-align: center;
        }

        th {
            background: #66ccff;
        }
    </style>
    <script type="text/javascript">

        var json = ${json}
        var headarr = [];

        function parseHead(oneRow) {
            for (var i in oneRow) {
                headarr[headarr.length] = i;
            }
        }

        function appendTable() {
            parseHead(json[0]);
            var div = document.getElementById("divTable");
            var table = document.createElement("table");
            var thead = document.createElement("tr");
            for (var count = 0; count < headarr.length; count++) {
                var td = document.createElement("th");
                td.innerHTML = headarr[count];
                thead.appendChild(td);
            }
            table.appendChild(thead);
            for (var tableRowNo = 0; tableRowNo < json.length; tableRowNo++) {
                var tr = document.createElement("tr");
                for (var headCount = 0; headCount < headarr.length; headCount++) {
                    var cell = document.createElement("td");
                    cell.innerHTML = json[tableRowNo][headarr[headCount]];
                    tr.appendChild(cell);
                }
                table.appendChild(tr);
            }
            div.appendChild(table);

        }
    </script>
</head>
<body onload="appendTable()">
<form action="/user/changePassword">
    <table>
        <tr>
            <td>
                旧密码：
            </td>
            <td>
                <input type="password" name="oldPassword">
            </td>
        </tr>
        <tr>
            <td>
                新密码：
            </td>
            <td>
                <input type="password" name="newPassword">
            </td>
        </tr>
        <tr>
            <td>
                确认新密码：
            </td>
            <td>
                <input type="password" name="checkPassword">
            </td>
        </tr>
    </table>
    <button type="submit">确认修改</button>
    <p style="color: orangered">${message}</p>
</form>
<form action="/user/cancel">
    <input type="number" name="orderID"><br>
    <button type="submit">删除订单</button>
</form>
<form action="/user/printOrder">
    <button type="submit">SEACH YOUR ORDERS</button>
</form>
<form action="/user/printOrderInfo">
    <button type="submit">SEACH YOUR ORDERINFOS</button>
</form>
<div id="errorZone"></div>
<div id="divTable"></div>
</body>
</html>
