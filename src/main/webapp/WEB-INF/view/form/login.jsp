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
            <c:if test="${not isUser}">
                <c:if test="${empty param.error}">
                    <spring:message code="form.registration.authority.not.signed.title"/>
                </c:if>
            </c:if>
        </font>

        <font size="2" color="green">
            <c:if test="${isUser}"><spring:message code="form.registration.authority.title"/>
                <security:authentication property="principal.username"/>,
                <b><security:authentication property="principal.authorities"/></b>
            </c:if>
        </font>
        <br/>
        <c:if test="${not empty param.error}">
            <font size="2" color="red"><b><spring:message code="form.registration.wrong.credentials"/></b></font>
        </c:if>

        <h2 class="form-signin-heading"><spring:message code="form.please.login"/> </h2>

        <label for="inputEmail" class="sr-only"><spring:message code="form.email"/></label>
        <input id="inputEmail" class="form-control" name="j_username" value="admin@gmail.com" required autofocus/>

        <label for="inputPassword" class="sr-only"><spring:message code="form.password" text="Password"/></label>
        <input type="password" id="inputPassword" class="form-control" name="j_password" value="12345" required/>

        <div class="checkbox">
            <label>
                <input type="checkbox" id="rememberme"  name="_spring_security_remember_me"/><spring:message code="form.rememberMe"/>
            </label>
        </div>
        <input type="submit" value="<spring:message code="form.login.title"/>" class="btn btn-lg btn-primary btn-block" >
        <br/>
        <a href="javascript:history.back()"><spring:message code="form.back"/></a>

        <br /><br />
        <p>Roles</p>

        <b>ROLE_SUPER_USER</b><br />
        Login:<span style="color: royalblue">superuser@outlook.com</span> Password: <span style="color: royalblue">12345</span> <br />
        <b>ROLE_ADMIN</b> <br />
        Login:<span style="color: royalblue">admin@gmail.com</span> Password: <span style="color: royalblue">12345</span> <br />
        <b>ROLE_USER</b> <br />
        Login: <span style="color: royalblue">roleuser@outlook.com</span> Password: <span style="color: royalblue">12345</span>
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