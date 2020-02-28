<%--
  Created by IntelliJ IDEA.
  User: parsa
  Date: 2/28/20
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="mother.jsp" %>

<html>
<head>
    <title>Contact list</title>
</head>
<body>
        <c:forEach items="${contacts}" var="contact">
            <h1>${contact.get("name")}</h1>
        </c:forEach>
<h1>This is will be contact list</h1>
</body>
</html>
