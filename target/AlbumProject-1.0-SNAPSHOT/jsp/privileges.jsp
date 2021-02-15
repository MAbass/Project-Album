<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 31/01/2021
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/include/link.jsp"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color: #0b2e13">
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
            <h5 style="color: #17a2b8;margin-top: 60px;">${message}</h5>
        </core:if>
        <div class="table-responsive" id="sailorTableArea" style="margin-top: 80px;">
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
                            <core:if test="${user.role eq 'ROLE_USER' && sessionUser.id != user.id}">
                                <tr>
                                    <td>${user.nom}</td>
                                    <td>${user.prenom}</td>
                                    <td>${user.login}</td>
                                    <td>${user.password}</td>
                                    <td>${user.role}</td>
                                    <core:forEach items="${userAlbums}" var="userAlbum">
                                        <core:if test="${userAlbum.userAlbum.id eq user.id && userAlbum.albumUser.id eq param.id }">

                                            <td>
                                                <a href="${pageContext.request.contextPath}/supp-privileges?idUser=${user.id}&idAlbum=${param.id}"
                                                   class="supp">Supprimer privilege${userAlbum.albumUser.id}${userAlbum.albumUser.id}</a></td>

                                        </core:if>
                                    </core:forEach>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/donner-privileges?idUser=${user.id}&idAlbum=${param.id}"
                                           class="modif">Donner privilege</a></td>
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
<jsp:include page="/include/footer.jsp"/>
</body>
</html>

