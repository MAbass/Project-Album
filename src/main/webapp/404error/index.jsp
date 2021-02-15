<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 03/02/2021
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<link href="${pageContext.request.contextPath}/404error/error.css" rel="stylesheet"/>
<body>
<section class="page_404">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 ">
                <div class="col-sm-10 col-sm-offset-1  text-center">
                    <div class="four_zero_four_bg">
                        <h1 class="text-center ">Interdit</h1>
                    </div>
                    <div class="contant_box_404">
                        <h3 class="h2">
                            Tu n'as pas accees à cette ressource
                        </h3>


                        <a href="${pageContext.request.contextPath}/acceuil" class="link_404">Acceuil</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
