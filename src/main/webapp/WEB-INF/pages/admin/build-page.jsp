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
    <link rel="stylesheet" href="${path}/statics/css/pace.css"/>
    <link rel="stylesheet" href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/statics/plugin/animate.css-master/animate.css"/>
    <link rel="stylesheet" href="${path}/statics/css/animate.css"/>
    <link rel="stylesheet" href="${path}/statics/css/style.css"/>
    <link rel="stylesheet" href="${path}/statics/css/color.css"/>
    <link rel="stylesheet" href="${path}/statics/css/bootstrap.custom.css"/>
    <link rel="stylesheet" href="${path}/statics/css/responsive.css3.css"/>
    <%--<link rel="stylesheet" href="${path}/statics/css/lanzuan.css"/>--%>
</head>
<body ng-app="app" class=" m-a-0" ng-controller="AdminController" ng-init="getWebPage()">
<jsp:include page="${path}/admin/admin-navbar"></jsp:include>
<div class="alert alert-info">
    <h5 class="text-center">组装页面</h5>
</div>
<div class="container-fluid p-a-md">

        <div class="row">
            <div class="col-xs-12 img-thumbnail p-a-0 ">
                <div class="alert alert-info p-r-2">组装效果预览<small class="text-danger"> (预览区域并不代表真正的设备大小,与实际效果会稍有出入)</small>
                    <div class="btn-group btn-group-sm pull-right" ng-init="showPrev=true">
                        <button class="btn btn-info fa fa-minus" ng-click="showPrev=!showPrev"></button>
                        <button class="btn btn-info fa fa-square-o" ng-click="showPrev=!showPrev"></button>
                        <button class="btn btn-info fa fa-times" ng-click="showPrev=false"></button>
                    </div>
                </div>

                <div style="height: 400px;overflow: scroll" ng-show="showPrev">
                    <div  ng-repeat="pageComponent in webPage.pageComponents track by $index">
                        <%--<span ng-init="src=pageComponent.websiteUri+'?var='+pageComponent.var+'&varU='+pageComponent.varU"></span>--%>
                        {{src}}
                        <div ng-include src="pageComponent.websiteUri+'?var='+pageComponent.var+'&varU='+pageComponent.varU"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row p-t-lg p-b-lg">
            <div class="col-xs-12">
                <h5>将左边的组件拖拽到右侧组装成一个页面</h5>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 solid-silver-border">
                <div ng-view style="height: 300px;overflow-y: scroll;" ></div>

            </div>
            <div class="col-xs-6">组件列表</div>
            <div class="col-xs-6">拖拽到下方</div>

        </div>
        <div class="row">
            <div class="col-xs-6">
                <ul ui-on-Drop="onDrop($event,$data,pageComponents)" class="list-group">
                    <li ui-draggable="true" drag="pageComponent" class="list-group-item" style="cursor: move"
                        on-drop-success="dropSuccessHandler($event,$index,pageComponents)"
                        ng-repeat="pageComponent in pageComponents track by $index">
                        {{pageComponent.name}}<a ng-href="#/{{pageComponent.var}}" >预览</a>
                    </li>
                </ul>
            </div>
            <div class="col-xs-6">
                <ul  class="list-group">
                    <li ui-on-Drop="onDrop($event,$data,webPage.pageComponents,$index)"
                        ui-draggable="false" drag="pageComponent" class="list-group-item dash-silver-border img-nav-brand"
                        on-drop-success="dropSuccessHandler($event,$index,webPage.pageComponents)"
                        ng-repeat="pageComponent in webPage.pageComponents track by $index">
                        {{pageComponent.var}}
                    </li>
                </ul>
            </div>

        </div>

</div>


<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
<script src="${path}/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
<script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
<script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
<script src="${path}/statics/js/tether.min.js"></script>
<script src="${path}/statics/js/draganddrop.min.js"></script>
<script src="${path}/admin/pageComponent/build-page-js"></script>

</body>

