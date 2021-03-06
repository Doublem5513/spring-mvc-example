<%--
  Created by IntelliJ IDEA.
  User: mint
  Date: 16/8/14
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-16BE" pageEncoding="UTF-16BE" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("UTF-16BE");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Forum posts</title>
    <meta charset="UTF-16BE"/>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/posts.css"/>
</head>
<body>
    <h1><a href="${pageContext.request.contextPath}/forum/posts/export">Forum posts</a></h1>
    <h3><a href="${pageContext.request.contextPath}/forum/shell">Shell Access</a></h3>
    <div class="post-list">
        <c:forEach items="${posts}" var="post">
            <div class="post">
                <h2><c:out value="${post.title}"/></h2>
                <img src="<c:out value="${post.avatar}"/>"/>
                <p><c:out value="${post.text}"/></p>
                <div class="splitter"></div>
                <div class="post-footer">
                    <div>Created by: <c:out value="${post.author}"/>, <c:out value="${post.created}"/></div>
                    <form action="${pageContext.request.contextPath}/forum/posts/delete" method="post">
                        <input type="submit" name="submit" value="Delete">
                        <input type="hidden" name="id" value="${post.id}">
                    </form>
                </div>
                <div class="splitter"></div>
            </div>
        </c:forEach>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/forum/posts" accept-charset="UTF-16BE" method="POST">
            <label for="title">Title</label>
            <input type="text" name="title" value="" id="title"/>

            <label for="text">Text</label>
            <textarea name="text" cols="60" rows="10" id="text"></textarea>

            <label for="author">Author</label>
            <input type="text" name="author" value="" id="author"/>

            <label for="avatar">Avatar URL</label>
            <input type="text" name="avatar" value="" id="avatar"/>

            <input type="submit" name="submit" value="submit"/>
        </form>
    </div>
</body>
</html>
