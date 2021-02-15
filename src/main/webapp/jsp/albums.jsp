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
<!-- Album Photo Grid-->
<section class="page-section bg-light" id="Album Photo">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Albums</h2>
            <h4><a href="${pageContext.request.contextPath}/add-album" class="add-album">Ajouter un album</a></h4>
        </div>
        <div class="row">
            <core:choose>
                <core:when test="${empty albums}">
                    <h2>Pas d'albums pour le moment</h2>
                </core:when>
                <core:otherwise>
                    <core:forEach items="${albums}" var="album">
                        <div class="col-lg-4 col-sm-6 mb-4">
                            <div class="portfolio-item">
                                <a class="portfolio-link"
                                   href="${pageContext.request.contextPath}/imagesAlbum?id=${album.id}">
                                    <div class="portfolio-hover">
                                        <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                                    </div>
                                    <img class="img-fluid"
                                         src="${pageContext.request.contextPath}/img/album.jpg"
                                         alt=""/>
                                </a>
                                <div class="portfolio-caption">
                                    <div class="portfolio-caption-heading">${album.nom}</div>
                                    <div class="portfolio-caption-subheading text-muted">Illustration</div>
                                </div>
                            </div>
                        </div>
                    </core:forEach>
                </core:otherwise>
            </core:choose>
        </div>
    </div>
</section>
<jsp:include page="/include/footer.jsp"/>
</body>
</html>

