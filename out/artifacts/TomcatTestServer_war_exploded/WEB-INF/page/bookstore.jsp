<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/12/17
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎进入购书界面！</title>
    <script type="text/javascript">
        var json =${json}
        var headarr = [];

        function parseHead(oneRow) {
            for (var i in oneRow) {
                headarr[headarr.length] = i;
            }
        }

        function appendTable() {
            parseHead(json[0]);
            var div = document.getElementById("bookListZone");
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
</head>
<body onload="appendTable(json);">
<form action="/bookstore/buyBooks">
    <table>
        <tr>
            <td>
                BookID:
            </td>
            <td>
                <input type="number" name="bookID">
            </td>
        </tr>
        <tr>
            <td>
                Quantity:
            </td>
            <td>
                <input type="number" name="qty">
            </td>
        </tr>
    </table>
    <button type="submit">BUY BOOKS</button>
</form>
<form action="/bookstore/showBooks">
    <button type="submit">SHOW BOOKS</button>
</form>
<form action="/bookstore/toUserManagement">
    <button type="submit">USER MANAGEMENT</button>
</form>
<div id="bookListZone"></div>
<div id="message">
    <p style="color: orangered">${message}</p>
</div>
</body>
</html>
