<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>蓝钻科技--网站资源</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="yes"/>
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/bootstrap.custom.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/responsive.css3.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="app" ng-controller="AdminController">
<jsp:include page="${path}/statics/page/included/admin/navbar.jsp"></jsp:include>
<div class="alert alert-info">
    <h5 class="text-center">网站资源汇总</h5>

</div>
<div class="container" ng-init="getArticles()">
    <div class="row padding-1">
        <div class="col-xs-4">
            <div class="row">图片资源</div>

            <div class="row solid-silver-border bg-sliver-1 padding-1">


                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>


            </div>
        </div>
        <div class="col-xs-4">
            <div class="row">图片资源</div>

            <div class="row solid-silver-border bg-sliver-1 padding-1">


                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>


            </div>
        </div>
        <div class="col-xs-4">
            <div class="row">图片资源</div>

            <div class="row solid-silver-border bg-sliver-1 padding-1">


                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>
                <div class="col-xs-3 padding-1">
                    <img src="/statics/image/icon/1121138.gif" class="img-responsive"/>
                </div>


            </div>
        </div>
    </div>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="${path}/app-js"></script>

</body>

