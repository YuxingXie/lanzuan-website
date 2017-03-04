<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
    <head lang="en">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>湖南蓝钻科技-default</title>
        <link rel="stylesheet" href="/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="/statics/plugin/animate.css-master/animate.css"/>
        <link rel="stylesheet" href="/statics/css/animate.css"/>
        <link rel="stylesheet" href="/statics/css/style.css"/>
        <link rel="stylesheet" href="/statics/css/color.css"/>
        <link rel="stylesheet" href="/statics/css/bootstrap.custom.css"/>
        <link rel="stylesheet" href="/statics/css/responsive.css3.css"/>
        <link rel="stylesheet" href="/statics/css/lanzuan.css"/>
    </head>
    <body ng-app="app">
        <div ng-controller="HomeController">
            <div ng-include="'/statics/page/included/component/navbar/navbar-md-down-fix-bottom.html'"></div>
            <div ng-include="'/statics/page/included/component/carousel/carousel-full-width-1.html'"></div>
            <div ng-include="'/statics/page/included/component/card-group/img-card-group-1.html'"></div>
            <div ng-include="'/statics/page/included/lanzuan/collapse-image-title-text-1.html'"></div>
            <div ng-include="'/statics/page/included/lanzuan/article-section-1.html'"></div>
            <div ng-include="'/statics/page/included/lanzuan/full-width-image-1.html'"></div>
            <div ng-include="'/statics/page/included/lanzuan/sort-link-section-1.html'"></div>
        </div>
        <div ng-include="'/statics/page/included/footer.html'"></div>
        <script src="/statics/js/jquery-3.1.1.min.js"></script>
        <script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
        <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>
        <script src="/statics/js/app.js"></script>
    </body>
</html>
