<%--
  Created by IntelliJ IDEA.
  User: mint
  Date: 17/8/14
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("application/x-download");
    response.setHeader("content-disposition", "attachment; filename=posts.xml");
%>

<xml>
    <posts>
        <c:forEach items="${posts}" var="post">
            <post>
                <title>${post.title}</title>
                <text>${post.text}</text>
                <created>${post.created}</created>
                <author>${post.author}</author>
                <avatar>${post.avatar}</avatar>
            </post>
        </c:forEach>
    </posts>
</xml>
