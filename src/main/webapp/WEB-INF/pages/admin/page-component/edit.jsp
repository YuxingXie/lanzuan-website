<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="yes"/>
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="app">
<div class="container-fluid  p-a-0 m-a-0" ng-controller="AdminController" ng-init="initAdmin()">
    <nav class="row navbar">
        <div class="navbar-brand">
            <c:choose>
                <c:when test="${empty user.sex or user.sex eq 'male'}">
                    <i class="fa fa-male padding-left-2em"></i>
                </c:when>
                <c:otherwise><i class="fa fa-female padding-left-2em"></i></c:otherwise>
            </c:choose>
            欢迎您，${user.name}
            <c:choose>
                <c:when test="${empty user.sex or user.sex eq 'male'}">先生</c:when>
                <c:otherwise>女士</c:otherwise>
            </c:choose>!
        </div>
        <ul class="nav navbar-nav">
            <li class="nav-item pull-right">
                <button class="nav-link btn btn-primary btn-sm" href="javascript:void(0)"><i class="fa fa-sign-out"></i>退出
                </button>
            </li>
        </ul>
    </nav>

    <div class="row padding-top-2em p-a-0 m-a-0">
        <div ng-controller="HomeController">
            <div class="col-xs-12 m-a-0 p-a-0">
                <div class="col-xs-12 margin-left-2em margin-right-2em">
                    <div>组件名称：${pageComponent.name}</div>
                    <div>组件说明：${pageComponent.remark}</div>
                </div>
            </div>
            <div class="col-xs-12 m-a-0 p-a-0">
                <label class="label label-default large-180">组件预览</label>
                <div style="border: groove #000 5px">
                    <jsp:include page="${pageComponent.uri}"></jsp:include>
                </div>
                <div class="padding-top-2em">
                    <jsp:include page="${pageComponent.editUri}"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
<script src="${path}/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
<script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
<script src="${path}/statics/plugin/bootstrap-contextmenu-master/bootstrap-contextmenu.js"></script>
<script src="${path}/statics/js/app.js"></script>

</body>

