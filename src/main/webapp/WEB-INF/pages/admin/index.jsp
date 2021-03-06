<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
    <meta name="apple-mobile-web-app-status-bar-style" content="yes" />
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="app" >
    <div class="container-fluid  p-a-0 m-a-0" ng-controller="AdminController" ng-init="initAdmin()">
        <jsp:include page="${path}/admin/admin-navbar"></jsp:include>


        <div class="row p-t-2em m-l-2em m-r-2em">
            <div class="col-xs-12 col-md-3">
                <jsp:include page="${path}/statics/page/included/admin/leftMenu.html"></jsp:include>
            </div>
            <div class="col-xs-12 col-md-9">
                <div class="card-group">
                    <div class="card">
                        <div class="card-header">
                            访问统计
                        </div>
                        <div class="card-block">
                            <div>总计访问量：1000</div>
                            <div>今日访问量：1000</div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            趋势图
                        </div>
                        <div class="card-block">
                            <div>building....</div>

                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            饼图
                        </div>
                        <div class="card-block">
                            <div>building....</div>

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
    <script src="${path}/app-js?pageId=${webPage.id}"></script>
</body>

