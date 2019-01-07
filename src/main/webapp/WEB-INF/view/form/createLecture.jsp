
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <div>
            <form action="/createLecture" method="post">
                Name: <input type="text" name="name"/>
                Description <input type="text" name="description"/>
                Ordinal Number <input type="number" max="16" min="1" name="name"/>
                Name: <input type="text" name="name"/>
            </form>
        </div>
    </jsp:body>
</page:template>