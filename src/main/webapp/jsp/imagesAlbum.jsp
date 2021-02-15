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
    <link href="${pageContext.request.contextPath}/css/modal.css" rel="stylesheet"/>

</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color: #0b2e13">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#open-modal">${album.nom}</a>
        <div id="open-modal" class="modal-window">
            <div>
                <a href="${pageContext.request.contextPath}/imagesAlbum?id=${album.id}" title="Close"
                   class="modal-close">close &times;</a>
                <h1><span style="color: #17a2b8">Nom:</span>${album.nom}</h1>
                <h5><span style="color: #17a2b8">Status:</span>${album.status}</h5>
            </div>
        </div>
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
<div class="container" style="margin-top: 100px;">
    <core:if test="${album.user.id eq sessionUser.id || sessionUser.role eq 'ROLE_ADMIN'}">
        <div class="row information">
            <a href="${pageContext.request.contextPath}/modify-album?id=${album.id}">Modifier l'album</a>
            <a href="${pageContext.request.contextPath}/ajouter-image?id=${param.id}">Ajouter une image</a>
            <a href="${pageContext.request.contextPath}/supprimer-album?id=${album.id}">Supprimer l'album</a>
            <core:if test="${album.status eq 'PRIVEE'}">
                <a href="${pageContext.request.contextPath}/privileges?id=${album.id}">Donner des priviléges</a>
            </core:if>
        </div>
    </core:if>
    <div class="row">
        <core:choose>
            <core:when test="${empty imageList}">
                <h3>Il y'a pas de photo pour l'instant</h3>
            </core:when>
            <core:otherwise>
                <core:forEach items="${imageList}" var="image">
                    <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                        <div class="nom">
                            <a href="#open-modal-titre" style="font-size: 20px;text-transform: uppercase;color: #1e7e34"> ${image.titre}</a>
                            <div id="open-modal-titre" class="modal-window">
                                <div>
                                    <a href="${pageContext.request.contextPath}/imagesAlbum?id=${album.id}" title="Close"
                                       class="modal-close">close &times;</a>
                                    <h1><span style="color: #17a2b8">Titre:</span>${image.titre}</h1>
                                    <h5><span style="color: #17a2b8">Type:</span>${image.photoType}</h5>
                                    <h5><span style="color: #17a2b8">Hauteur:</span>${image.hauteur}</h5>
                                    <h5><span style="color: #17a2b8">Longueur:</span>${image.longueur}</h5>
                                    <h5><span style="color: #17a2b8">Mots cles:</span>${image.motscles}</h5>
                                    <h5><span style="color: #17a2b8">Description:</span>${image.description}</h5>
                                </div>
                            </div>
                        </div>
                        <a class="thumbnail" href="#" data-image-id="" data-toggle="modal" data-title=""
                           data-image="${pageContext.request.contextPath}/images/image-${image.id}-album${album.id}.png"
                           data-target="#image-gallery">
                            <img class="img-thumbnail"
                                 src="${pageContext.request.contextPath}/images/image-${image.id}-album${album.id}.png"
                                 alt="Another alt text">
                        </a>
                        <core:if test="${album.user.id eq sessionUser.id || sessionUser.role eq 'ROLE_ADMIN'}">
                            <div class="foot">
                                <a href="${pageContext.request.contextPath}/modifier-image?idImage=${image.id}&idAlbum=${param.id}"
                                   class="modif">Modifier</a>
                                <a href="${pageContext.request.contextPath}/supprimer-image?idImage=${image.id}&idAlbum=${param.id}"
                                   class="supp">Supprimer</a>
                            </div>
                        </core:if>
                    </div>
                </core:forEach>
            </core:otherwise>
        </core:choose>


    </div>

    <div class="modal fade" id="image-gallery" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="image-gallery-title"></h4>
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                            class="sr-only">Close</span>
                    </button>
                </div>
                <div class="modal-body">
                    <img id="image-gallery-image" class="img-responsive col-md-12" src="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary float-left" id="show-previous-image"><i
                            class="fa fa-arrow-left"></i>
                    </button>

                    <button type="button" id="show-next-image" class="btn btn-secondary float-right"><i
                            class="fa fa-arrow-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/include/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/app.js"></script>

</body>
</html>

