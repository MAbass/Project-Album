<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 01/02/2021
  Time: 14:03
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
    <title>Login</title>
</head>
<style>
    .error {
        color: red;
    }
</style>
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
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h1 class="text-center">Se connecter</h1>
            <core:if test="${!empty 'connexion'}">
                <h3 style="color: #17a2b8">${connexion}</h3>
            </core:if>

            <form method="post" style="margin-bottom: 40px;" class="login">
                <label class="label col-md-3 control-label">Login</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="username" placeholder="Login">
                </div>
                <label class="label col-md-3 control-label">Password</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" name="password" placeholder="Password">
                </div>

                <input type="submit" class="btn btn-info">Soumettre</input>
            </form>

        </div>
    </div>
</div>
</body>
<jsp:include page="/include/jquery.jsp"/>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

</html>
