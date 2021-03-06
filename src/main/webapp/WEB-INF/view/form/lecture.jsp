<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<input type="hidden" id="lectureId" value="${lecture.lectureId}"/>
<c:set var="lecture" value="${lecture}"/>
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

                    <li data-toggle="collapse" data-target="#subjects" class="active">
                        <a href="#"><i class="fa fa-gift fa-lg"></i> <spring:message code="form.subjects.title"/><span
                                class="arrow"></span></a>
                    </li>
                    <ul class="sub-menu collapse in" id="subjects" aria-expanded="true">
                        <c:forEach items="${subjects}" var="subject">
                            <li class="active"><a href="/subject/${subject.id}/lecture">${subject.name}</a></li>
                        </c:forEach>
                    </ul>
                    <c:if test="${not empty subjects}">
                        <li data-toggle="collapse" data-target="#products" class="active">
                            <a href="#"><i class="fa fa-gift fa-lg"></i> <spring:message
                                    code="form.lectures.title"/><span
                                    class="arrow"></span></a>
                        </li>
                        <ul class="sub-menu collapse in" id="products" aria-expanded="true">
                            <c:forEach items="${lectures}" var="lecture">
                                <li class="active"><a href="/lecture/${lecture.lectureId}">${lecture.name}</a></li>
                            </c:forEach>
                        </ul>


                        <li data-toggle="collapse" data-target="#service" class="collapsed">
                            <a href="#"><i class="fa fa-globe fa-lg"></i> <spring:message
                                    code="form.lectures.labs.title"/>
                                <span class="arrow"></span></a>
                        </li>
                        <ul class="sub-menu collapse" id="service">
                            <c:forEach items="${labs}" var="lab">
                                <li><a href="/labs/${lab.id}">${lab.name} </a>
                                </li>
                            </c:forEach>
                        </ul>

                        <li>
                            <a href="/createLecture?subjectId=${subject.id}">
                                <i class="fa fa-user fa-lg"></i> <spring:message code="form.lectures.create"/>
                            </a>
                        </li>

                        <li>
                            <a href="/createLab">
                                <i class="fa fa-users fa-lg"></i> <spring:message code="form.lectures.labs.create"/>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
        <c:if test="${not empty groups}">
            <div class="students">
                <h2>${subject.name}</h2>
                <img src="https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=${lecture.filePath}" width="400"
                     height="400"/>
                <table class="students-table">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Student`s Group</th>
                        <th>Lecture presence</th>
                        <th>Enroll/Unenroll Student</th>
                    </tr>
                    <c:forEach items="${lecture.students}" var="student">

                        <form:form action="/lecture/${lecture.lectureId}/student/${student.username}/enrollStudent?isPresent=${student.checked}"
                                   method="post" modelAttribute="lecture">
                            <input type="hidden" value="${lecture}"/>
                            <tr>
                                <td><span id="firstname">${student.firstName}</span></td>
                                <td>${student.lastName}</td>
                                <td>${student.groupName}</td>
                                <td>${student.checked}</td>
                                <td><input type="submit" value="Submit"/>
                                </td>
                            </tr>
                        </form:form>
                    </c:forEach>
                </table>
            </div>
        </c:if>

    </jsp:body>
</page:template>