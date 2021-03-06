<%--
  Created by IntelliJ IDEA.
  User: mint
  Date: 17/8/14
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-16BE" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("UTF-16BE");
%>
<html>
<head>
    <title>Groovy shell</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/posts.css"/>
</head>
<body>
    <form action="${pageContext.request.contextPath}/forum/shell" method="post">
        <label for="code">Groovy code</label>
        <textarea name="code" id="code" cols="120" rows="20">${code}</textarea>
        <input type="submit" name="submit" value="Execute">
    </form>

    <div id="result">
        <c:out value="${result}"></c:out>
    </div>

</body>
</html>
