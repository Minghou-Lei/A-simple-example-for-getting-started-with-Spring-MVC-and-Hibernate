<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/12/17
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error page</title>
</head>
<body>
<h1> <%= request.getAttribute("message")%> </h1>
<p>这里不是你该来的地方...</p>
</body>
</html>
