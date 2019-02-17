<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="form-lecture">
                        <h3 class="page-header">Завантаження файлів</h3>
                    </div>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Завантаження файлів</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <c:url value="/uploadStudents" var="studentsUploadControllerURL" />
            <c:url value="/uploadGroups" var="groupsUploadControllerURL" />
            <c:url value="/uploadLecturers" var="lecturersUploadControllerURL" />
            <c:url value="/uploadSubjects" var="subjectsUploadControllerURL" />
            <!-- Content Row -->
            <div class="row">

                <div class="col-lg-12">
                    <form action="${studentsUploadControllerURL}" method="post"
                          enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td><b>File:</b></td>
                                <td><input type="file" name="file"></td>
                                <td><input type="submit" value="Download file"></td>
                            </tr>
                        </table>
                    </form>
                    <p>Завантаження даних студентів</p>
                    <form action="${lecturersUploadControllerURL}" method="post"
                          enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td><b>File:</b></td>
                                <td><input type="file" name="file"></td>
                                <td><input type="submit" value="Download file"></td>
                            </tr>
                        </table>
                    </form>
                    <p>Завантаження даних викладачів</p>
                    <form action="${subjectsUploadControllerURL}" method="post"
                          enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td><b>File:</b></td>
                                <td><input type="file" name="file"></td>
                                <td><input type="submit" value="Download file"></td>
                            </tr>
                        </table>
                    </form>
                    <p>Завантаження даних предметів</p>
                    <form action="${groupsUploadControllerURL}" method="post"
                          enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td><b>File:</b></td>
                                <td><input type="file" name="file"></td>
                                <td><input type="submit" value="Download file"></td>
                            </tr>
                        </table>
                    </form>
                    <p>Завантаження даних груп</p>

                </div>

            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->
    </jsp:body>
</page:template>