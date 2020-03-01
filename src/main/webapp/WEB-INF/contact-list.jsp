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
    <spring:url value="/resources/css/contacts.css" var="contactsCSS"/>
    <link rel="stylesheet" href="${contactsCSS}">
</head>
<body>


<div class="container">
    <!-- contacts card -->
    <div class="col-12 text-right">
        <a href="/add/contact">
            <button type="button" class="btn btn-secondary btn-lg m-1 ">Add Contact +</button>
        </a>
    </div>
    <div class="card card-default" id="card_contacts">
        <div id="contacts" class="panel-collapse collapse show" aria-expanded="true" style="">
            <ul class="list-group pull-down" id="contact-list">
                <c:forEach items="${contacts}" var="contact" varStatus="counter">

                    <li class="list-group-item">
                        <div class="row w-100">
                            <div class="col-12 col-sm-4 col-md-2 px-0">
                                <img src="https://i.picsum.photos/id/${counter.count+60}/100/100.jpg"
                                     alt="Mike Anamendolla"
                                     class="rounded-circle mx-auto d-block img-fluid">
                            </div>
                            <div class="col-10 col-sm-4 col-md-7 text-center text-sm-left">
                                <label class="name lead">${contact.get("name")}</label>
                                <br>
                                <span class="fa fa-phone fa-fw text-muted" data-toggle="tooltip" title=""
                                      data-original-title="${contact.get("number")}"></span>
                                <span class="text-muted small">${contact.get("number")}</span>
                                <br>
                                <span class="fa fa-envelope fa-fw text-muted" data-toggle="tooltip"
                                      data-original-title="" title=""></span>
                                <span class="text-muted small text-truncate">test@example.com</span>
                            </div>
                            <div class="col-10 col-sm-4 col-md-3 text-right">
                                <a href="/update/contact/${contact.get("id")}">
                                    <button type="button" class="btn btn-outline-primary">Update</button>
                                </a>
                                <a href="/delete/contact/${contact.get("id")}">
                                    <button type="button" class="btn btn-outline-danger">Delete</button>
                                </a>
                            </div>
                        </div>
                    </li>

                </c:forEach>
            </ul>
            <!--/contacts list-->
        </div>
    </div>
</div>
</body>
</html>

