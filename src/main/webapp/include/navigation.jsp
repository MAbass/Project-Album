<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="navbar-nav text-uppercase ml-auto">
    <li class="nav-item"><a class="nav-link js-scroll-trigger"
                            href="${pageContext.request.contextPath}/acceuil">Acceuil</a></li>
    <core:choose>
        <core:when test="${sessionUser.role eq 'ROLE_USER'}">
            <li class="nav-item"><a class="nav-link js-scroll-trigger"
                                    href="${pageContext.request.contextPath}/logout">Se deconnecter</a></li>
            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/albums">Album Photo</a>
            </li>
            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/profile?id=${sessionUser.id}">Profile</a>
            </li>
        </core:when>
        <core:when test="${sessionUser.role eq 'ROLE_ADMIN'}">
            <li class="nav-item"><a class="nav-link js-scroll-trigger"
                                    href="${pageContext.request.contextPath}/logout">Se deconnecter</a></li>
            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/albums">Album
                Photo</a>
            </li>
            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/lists-utilisateurs">Utilisateurs</a>
            </li>
            <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/profile?id=${sessionUser.id}">Profile</a>
            </li>
        </core:when>
        <core:otherwise>
            <li class="nav-item"><a class="nav-link js-scroll-trigger"
                                    href="${pageContext.request.contextPath}/login">Se connecter</a></li>
            <li class="nav-item"><a class="nav-link js-scroll-trigger"
                                    href="${pageContext.request.contextPath}/createAccount">Inscription</a>
            </li>
        </core:otherwise>
    </core:choose>
</ul>
