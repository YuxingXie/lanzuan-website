<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>



<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>湖南蓝钻科技</title>
    <link rel="stylesheet" href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/statics/plugin/animate.css-master/animate.css"/>
    <link rel="stylesheet" href="${path}/statics/css/animate.css"/>
    <link rel="stylesheet" href="${path}/statics/css/style.css"/>
    <link rel="stylesheet" href="${path}/statics/css/color.css"/>
    <link rel="stylesheet" href="${path}/statics/css/bootstrap.custom.css"/>
    <link rel="stylesheet" href="${path}/statics/css/responsive.css3.css"/>
    <style>
        img {
            display: block;
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body ng-app="homeApp" class="img-responsive">
<div ng-controller="HomeController" >
    <div class="container margin-top-2em">
        <div class="row">
            <div class="col-xs-12 col-md-3">
                <div class="list-group fixed-left-menu">
                    <div class="list-group-item p-a-0 hidden-sm-down">
                        <img src="${path}/statics/image/lanzuan/icons/ico.jpg" class="img-ico-larger margin-left-2em">
                    </div>
                    <div class="list-group-item p-a-0" >
                        <ol class="breadcrumb m-a-0">
                            当前位置:
                            <li class="small-90 fa fa-home"><a href="/"> 首页</a></li>
                            <li class="small active">${article.articleSections[0].name}</li>
                        </ol>
                    </div>
                    <a href="#content" class="list-group-item hidden-sm-down" >${article.title}</a>

                    <div class="list-group-item p-a-0 hidden-sm-down" >
                        <img src="${path}/statics/image/lanzuan/con_01.jpg" class="full-width"/>
                    </div>
                </div>'
            </div>
            <div class="col-xs-12 col-md-9 p-a-0" id="content">
                    <div class="col-xs-12 text-center p-a-0 m-a-0">
                        <h4>${article.title}</h4>
                        <label class="pull-right margin-bottom-2em label label-pill label-default">
                        <c:if test="${not empty article.author}">作者${article.author}</c:if>
                            <fmt:formatDate value="${article.date}" pattern="yyyy-MM-dd"/>
                        </label>
                    </div>


                <div class="col-xs-12">${article.content}</div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="${path}/statics/page/included/footer.html"></jsp:include>
<script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${path}/statics/js/tether.min.js"></script>
<script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
</body>
</html>
