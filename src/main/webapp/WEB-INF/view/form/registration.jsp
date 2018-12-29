<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
    <spring:url value="/resources/css/signin.css" var="signin" />
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${signin}" rel="stylesheet" />
</head>
<body>
    <form name="form" action="j_spring_security_check" method="post" class="form-signin">
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUser"/>
        <font size="2" color="red">
            <c:if test="${isUser}">
                <spring:message code="form.registration.alreadyRegistered"/>
            </c:if>
        </font>
        <br/>
        <c:if test="${not empty param.error}">
            <font size="2" color="red"><b><spring:message code="form.registration.wrong.credentials"/> </b></font>
        </c:if>

        <h2 class="form-signin-heading"><spring:message code="form.register"/></h2>

        <label for="inputEmail"><spring:message code="form.email"/></label>
        <input id="inputEmail" class="form-control" name="j_username"  required autofocus />

        <label for="inputPassword"><spring:message code="form.password"/></label>
        <input type="password" id="inputPassword" class="form-control" name="j_password" required/>

        <label for="inputFirstName"><spring:message code="form.first_name"/></label>
        <input  id="inputFirstName" class="form-control" name="j_password" required/>

        <label for="inputLastName"><spring:message code="form.last_name"/></label>
        <input  id="inputLastName" class="form-control" name="j_password" required/>

        <div class="checkbox">
            <label>
                <input type="checkbox" id="rememberme" name="_spring_security_remember_me"/><spring:message code="form.rememberMe"/>
            </label>
        </div>
        <input type="submit" value="<spring:message code="form.registration.title"/>" class="btn btn-lg btn-primary btn-block"/>
        <br/>
        <a href="javascript:history.back()"><spring:message code="form.back"/></a>
    </form>
    <div class="container">
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <a href="<%=request.getContextPath()%>?languageVar=en">EN</a>
                    <a href="<%=request.getContextPath()%>?languageVar=ua">UA</a>
                    <p>Copyright © 2018</p>
                </div>
            </div>
        </footer>
    </div>
</body>

</html>
