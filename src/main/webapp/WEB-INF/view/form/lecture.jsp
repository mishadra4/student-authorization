<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<page:template>
    <jsp:body>
        <div class="nav-side-menu">
            <div class="brand">Brand Logo</div>
            <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>

            <div class="menu-list">

                <ul id="menu-content" class="menu-content collapse out">
                    <li>
                        <a href="#">
                            <i class="fa fa-dashboard fa-lg"></i> Dashboard
                        </a>
                    </li>

                    <li data-toggle="collapse" data-target="#products" class="collapsed active">
                        <a href="#"><i class="fa fa-gift fa-lg"></i> <spring:message code="form.lectures.title"/><span
                                class="arrow"></span></a>
                    </li>
                    <ul class="sub-menu collapse" id="products">
                        <c:forEach items="${lectures}" var="lecture">
                            <li class="active"><a href="/lecture/${lecture.lectureId}"><spring:message
                                    code="form.lectures.lab.title"/>${lecture.name}</a></li>
                        </c:forEach>
                    </ul>


                    <li data-toggle="collapse" data-target="#service" class="collapsed">
                        <a href="#"><i class="fa fa-globe fa-lg"></i> <spring:message code="form.lectures.labs.title"/>
                            <span class="arrow"></span></a>
                    </li>
                    <ul class="sub-menu collapse" id="service">
                        <c:forEach items="${labs}" var="lab">
                            <li><a href="/labs/${lab.id}"><spring:message code="form.lectures.lab.title"/>${lab.id} </a>
                            </li>
                        </c:forEach>
                    </ul>

                    <li>
                        <a href="/createLecture">
                            <i class="fa fa-user fa-lg"></i> <spring:message code="form.lectures.create"/>
                        </a>
                    </li>

                    <li>
                        <a href="/createLab">
                            <i class="fa fa-users fa-lg"></i> <spring:message code="form.lectures.labs.create"/>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <c:if test="${not empty groups}">
            <div class="students">
                <table class="students-table">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Student`s Group</th>
                        <th>Lecture presence</th>
                    </tr>
                    <c:forEach items="${groups}" var="groups">
                        <c:forEach items="${groups.students}" var="student">
                            <tr>
                                <td>${student.firstName}</td>
                                <td>${student.lastName}</td>
                                <td>${groups.name}</td>
                                <td><input type="checkbox" class="change-js" ${lecture.students.contains(student)?'checked':''} onchange="lectureComponent.enrollCheckbox()"/></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </div>
        </c:if>

    </jsp:body>
</page:template>