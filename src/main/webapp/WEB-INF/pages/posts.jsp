<%--
  Created by IntelliJ IDEA.
  User: mint
  Date: 16/8/14
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forum posts</title>
    <style type="text/css">
        body{
            background-color: lightgray;
        }

        div.post-list{
            background-color: lightblue;
            width: 640px;
            padding: 10px;
            margin: 10px;
        }

        div.post{
            font-family: sans-serif;
            background-color: darkblue;
            color: wheat;
            width: 100%;
            padding: 10px;
            margin: 10px;
        }

        div.post h2{
            font-family: serif;
            color: white;
            margin: 0;
            padding: 10px;
            width: 100%;
            background-color: blue;
            text-align: center;
        }

        div.post img{
            max-width: 128px;
            max-height: 128px;
            float: left;
            margin: 10px;
            border-right: 1px dashed gray;
        }

        div.post p{
            color: lightgray;
        }

        div.splitter{
            clear: both;
            width: 100%;
        }

        div.post-footer{
            border-bottom: 1px dashed gray;
        }

    </style>
</head>
<body>
    <h1>Forum posts</h1>
    <div class="post-list">
        <c:forEach items="${posts}" var="post">
            <div class="post">
                <h2><c:out value="${post.title}"/></h2>
                <img src="<c:out value="${post.avatar}"/>"/>
                <p><c:out value="${post.text}"/></p>
                <div class="splitter"></div>
                <div class="post-footer">
                    Created by: <c:out value="${post.author}"/>, <c:out value="${post.created}"/>
                    <form action="/forum/posts/delete" method="post">
                        <input type="submit" name="id" value="${post.id}">
                    </form>
                    <!-- <a method="delete" href="/forum/posts/delete?id=${post.id}">delete</a> -->
                </div>
            </div>
        </c:forEach>
    </div>
    <div>
        <form action="/forum/posts" method="POST">
            <input type="text" name="title" value="Title">
            <input type="text" name="text" value="Text">
            <input type="text" name="author" value="Author">
            <input type="text" name="avatar" value="Avatar URL">
            <input type="submit" name="submit" value="submit">
        </form>
    </div>
</body>
</html>
