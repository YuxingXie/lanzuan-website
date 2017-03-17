<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>轮播方案列表</title>
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
<jsp:include page="${path}/statics/page/included/admin/navbar.jsp"></jsp:include>
<div class="container-fluid" ng-controller="AdminController" ng-init="getNavbarList()">
    <div class="row">
        <div class="alert alert-info">
            <h5 class="text-center">应用导航条方案</h5>
            <a class="fa fa-reply btn btn-primary btn-sm white-link" href="${path}/admin/page_component/edit/${pageComponentId}">返回编辑页</a>
        </div>
        <div class="alert alert-warning">
            <ul class="list-unstyled">

                <li><i class="fa fa-graduation-cap fa-fw"></i>如果多个方案都为“可用”状态，我们只会应用查到的第一个方案，为了确保使用到正确的方案，请把不用的其它方案设为“禁用”。
                </li>

            </ul>
        </div>
        <div class="col-xs-12">
            <div>
                <div class="row p-a-0 m-a-0 large-180 bg-light-grey">
                    <div class="col-xs-2">导航条名称</div>
                    <div class="col-xs-2">导航条图标</div>
                    <div class="col-xs-3">导航项</div>
                    <div class="col-xs-2">状态</div>
                    <div class="col-xs-2">禁用/启用</div>
                </div>

                    <div class="row p-a-0 m-a-0 solid-silver-border-top solid-silver-border-bottom hover-bg-color-grey" ng-repeat="navbar in navbarList">
                        <div class="col-xs-2">
                            <i  ng-class="{'color-red':!navbar.name}">{{navbar.name?navbar.name:"未命名"}}</i>
                        </div>
                        <div class="col-xs-2">
                            <img ng-src="${path}{{navbar.navbarBrand.value}}" class="img-responsive img-thumbnail img-ico-lg"
                                 ng-if="navbar &&navbar.navbarBrand&&navbar.navbarBrand.type==='image'"/>
                            <span ng-if="navbar &&navbar.navbarBrand&&navbar.navbarBrand.type!=='image'">{{navbar.navbarBrand.value}}</span>
                        </div>
                        <div class="col-xs-3">
                            <ol >
                                <li ng-repeat="navItem in navbar.items">

                                    <span>{{navItem.name}}</span>
                                </li>
                            </ol>


                            <i ng-if="!navbar.items" class="color-red">没有导航项</i>
                        </div>
                        <div class="col-xs-2"> {{navbar.enabled?"已启用":"已禁用"}}</div>
                        <div class="col-xs-3">
                            <span class="fa fa-2x hover-cursor-hand" ng-click="navbarToggle(navbar)"
                                  ng-class="{'fa-toggle-on color-danger':navbar.enabled,'fa-toggle-off color-grey':!navbar.enabled}">
                                <%--{{carousel.enabled?"禁用":"启用"}}--%>
                            </span>
                            <button class="fa fa-trash btn btn-danger btn-sm m-b-15" ng-click="deleteNavbar(navbar)">
                                删除方案
                            </button>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>
<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
<script src="${path}/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
<script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
<script src="${path}/app-js?componentId=${pageComponentId}"></script>

</body>
