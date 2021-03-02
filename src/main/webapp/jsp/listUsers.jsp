<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 31/01/2021
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet"
          type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/image.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <title>Inscription</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">E-Album</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ml-1"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <jsp:include page="/include/navigation.jsp"/>
        </div>
    </div>
</nav>
<div class="container" style="margin-top: 80px;">
    <div class="row">
        <core:if test="${!empty 'message'}">
            <h5 style="color: #17a2b8">${message}</h5>
        </core:if>
        <div class="table-responsive" id="sailorTableArea">
            <table id="sailorTable" class="table table-striped table-bordered" width="100%">
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prenom</th>
                    <th>Login</th>
                    <th>Mail</th>
                    <th>Role</th>
                    <th colspan="2">Action</th>
                </tr>
                </thead>
                <tbody>
                <core:choose>
                    <core:when test="${empty users}">
                        <tr>Il y'a pas d'utilisateurs</tr>
                    </core:when>
                    <core:otherwise>
                        <core:forEach items="${users}" var="user">
                            <core:if test="${sessionUser.id != user.id}">
                                <tr>
                                    <td>${user.nom}</td>
                                    <td>${user.prenom}</td>
                                    <td>${user.login}</td>
                                    <td>${user.password}</td>
                                    <td>${user.role}</td>
                                    <td><a href="${pageContext.request.contextPath}/modifier-user?id=${user.id}"
                                           class="modif">Modifier</a>
                                    </td>
                                    <td><a href="${pageContext.request.contextPath}/supprimer-user?id=${user.id}"
                                           class="supp">Supprimer</a>
                                    </td>
                                </tr>
                            </core:if>
                        </core:forEach>

                    </core:otherwise>
                </core:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>
