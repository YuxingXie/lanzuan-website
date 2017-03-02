<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
    <meta name="apple-mobile-web-app-status-bar-style" content="yes" />
    <link href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/color.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="AdminApp" >
    <div class="container-fluid  padding-0 margin-0" ng-controller="AdminController" ng-init="initAdmin()">
        <nav class="row navbar">
            <div class="navbar-brand">
                <c:choose>
                    <c:when test="${empty user.sex or user.sex eq 'male'}">
                        <i class="fa fa-male"></i>
                    </c:when>
                    <c:otherwise><i class="fa fa-female"></i></c:otherwise>
                </c:choose>
                欢迎您，${user.name}
                <c:choose>
                    <c:when test="${empty user.sex or user.sex eq 'male'}">先生</c:when>
                    <c:otherwise>女士</c:otherwise>
                </c:choose>!
            </div>
            <ul class="nav navbar-nav">
                <li class="nav-item pull-right">
                    <button class="nav-link btn btn-primary btn-sm" href="javascript:void(0)"><i class="fa fa-sign-out"></i>退出</button>
                </li>
            </ul>
        </nav>
        <div class="row" role="tablist" ng-init="_in=1;collapse=true">
            <div class="card  margin-bottom-0 margin-top-0" ng-repeat="menuItem in menuItems">
                <div class="card-header margin-bottom-0" role="tab">
                    <a href="javascript:void(0)" ng-click="_in=$index;collapse=!collapse;">

                        <h5 class="card-title  margin-bottom-0">
                            <i class="{{menuItem.class}} padding-left-10"></i>
                            {{menuItem.name}}
                            <i class="fa pull-right padding-right-30" ng-class="{'fa-angle-down':_in===$index&&!collapse,'fa-angle-right':_in!==$index||collapse}"></i>
                        </h5>
                    </a>
                </div>
                <div class="card-block padding-0 margin-0" ng-class="{'collapse':_in!==$index||collapse,'in':_in===$index&&!collapse}" role="tabpanel" aria-labelledby="headingOne">
                    <div class="list-group">
                        <a class="list-group-item" ng-repeat="subMenuItem in menuItem.menuItem" ng-href="{{subMenuItem.link}}">
                            <i class="{{subMenuItem.class}} padding-left-20"></i>
                            <span class="label label-default label-pill pull-right margin-right-20">14</span>{{subMenuItem.name}}
                        </a>

                    </div>
                </div>
            </div>

        </div>
    </div>
    <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
    <script src="/statics/js/admin.js"></script>
</body>

