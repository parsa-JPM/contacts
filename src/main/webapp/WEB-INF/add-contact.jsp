<%--
  Created by IntelliJ IDEA.
  User: parsa
  Date: 2/28/20
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="mother.jsp" %>
<html>
<head>
    <title>Add contact</title>
</head>
<body>
${org.springframework.validation.BindingResult.contactModel2}
<div class="container">
    <div class="container">
        <h2>Contact Form</h2>
        <%--@elvariable id="contactModel" type="ir.codefather.mongodemo.dto.ContactDTO"--%>
        <form:form action="/save/contact" modelAttribute="contactModel" method="post">

            <form:hidden path="id"/>
            <div class="form-group">

                <label for="name">Name:</label>
                <form:input path="name" cssClass="form-control" id="name" placeholder="Enter name"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>
            <div class="form-group">
                <label for="number">Number:</label>
                <form:input path="number" cssClass="form-control" id="number" placeholder="Enter number"/>
                <form:errors path="number" cssClass="text-danger"/>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
  </div>
 </body>
</html>
