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
            <h1 class="text-center">Modifier Utilisateur</h1>
            <core:if test="${!empty 'message'}">
                <h3 style="color: #17a2b8">${message}</h3>
            </core:if>
            <form method="post" style="margin-bottom: 40px;">
                <label class="label col-md-3 control-label">Role</label>
                <div class="col-md-9">
                    <select name="role">
                        <option value="${user.role}" selected>${user.role}</option>
                        <core:if test="${user.role eq 'ROLE_ADMIN'}">
                            <option value="ROLE_USER">ROLE_USER</option>
                        </core:if>
                        <core:if test="${user.role eq 'ROLE_USER'}">
                            <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                        </core:if>
                    </select>
                </div>
                <input type="submit" class="btn btn-info">Soumettre</input>
            </form>

        </div>
    </div>
</div>
</body>
</html>
