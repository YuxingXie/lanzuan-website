<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>蓝钻科技--组件方案列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="yes"/>
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="app" class=" m-a-0" ng-controller="AdminController" ng-init="getWebPage()">
<jsp:include page="${path}/admin/admin-navbar"></jsp:include>
<div class="alert alert-info">
    <h5 class="text-center">组装页面</h5>
</div>
<div class="container-fluid">
    <div class="container" >

        <div class="row">
            <div class="col-xs-12 img-thumbnail p-a-0 ">
                <div class="alert alert-info p-r-2">组件预览
                    <div class="btn-group btn-group-sm pull-right" ng-init="showPrev=true">
                        <button class="btn btn-info fa fa-minus" ng-click="showPrev=!showPrev"></button>
                        <button class="btn btn-info fa fa-square-o" ng-click="showPrev=!showPrev"></button>
                        <button class="btn btn-info fa fa-times" ng-click="showPrev=false"></button>
                    </div>
                </div>

                <div ng-view style="height: 400px;" ng-show="showPrev"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6">
                <ul ui-on-Drop="onDrop($event,$data,pageComponents)" class="list-group">
                    <li ui-draggable="true" drag="pageComponent" class="list-group-item"
                        on-drop-success="dropSuccessHandler($event,$index,pageComponents)"
                        ng-repeat="pageComponent in pageComponents track by $index">
                        {{pageComponent.name}}<a ng-href="#/{{pageComponent.var}}">预览</a>
                    </li>
                </ul>
            </div>
            <div class="col-xs-6">
                <ul  class="list-group">
                    <li ui-on-Drop="onDrop($event,$data,webPage.pageComponents,$index)"
                        ui-draggable="false" drag="pageComponent" class="list-group-item dash-silver-border img-nav-brand"
                        on-drop-success="dropSuccessHandler($event,$index,webPage.pageComponents)"
                        ng-repeat="pageComponent in webPage.pageComponents track by $index">
                        {{pageComponent.name}}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>


<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
<script src="${path}/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
<script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
<script src="${path}/statics/js/draganddrop.min.js"></script>
<script src="${path}/admin/pageComponent/build-page-js"></script>

</body>

