<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<page:template>
    <jsp:body>

        <div class="form-lecture">
            <form:form action="/submitLecture?subjectId=${subject.id}" method="POST" modelAttribute="lecture">

                <h3 class="form-signin-heading"><spring:message code="form.please.enter.lecture"/></h3>

                <label for="inputLectureName" class="sr-only"><spring:message code="form.lecture.name"/></label>
                <input id="inputLectureName" class="form-control-lecture" name="name" required autofocus
                       placeholder="<spring:message code="form.lecture.name"/>"/>

                <label for="inputLectureDescription" class="sr-only"><spring:message
                        code="form.lecture.description"/></label>
                <input id="inputLectureDescription" class="form-control-lecture" name="description" required
                       placeholder="<spring:message code="form.lecture.description"/>"/>

                <label for="inputLectureOrdinalNumber" class="sr-only"><spring:message
                        code="form.lecture.ordinal.number"/></label>
                <input id="inputLectureOrdinalNumber" class="form-control-lecture" name="ordinalNumber" required
                       placeholder="<spring:message code="form.lecture.ordinal.number"/>"/>

                        <%--<input type="hidden" name="subject" value="${subject}"/>--%>

                <label>Виберіть необхідні групи</label><br/>
                <%--<label>${groups.name}</label>--%>
                <form:checkboxes items="${groups}" path="groups" />

                <input type="submit" value="<spring:message code="form.save.title"/>"
                       class="btn btn-lg btn-primary btn-block">
            </form:form>
        </div>
    </jsp:body>
</page:template>