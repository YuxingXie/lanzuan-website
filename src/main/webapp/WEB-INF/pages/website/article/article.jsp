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
        <link rel="stylesheet" href="${path}/statics/css/style.css"/>
        <link rel="stylesheet" href="${path}/statics/css/color.css"/>
        <link rel="stylesheet" href="${path}/statics/plugin/animate.css-master/animate.min.css"/>
        <link rel="stylesheet" href="${path}/statics/css/bootstrap.custom.css"/>
        <link rel="stylesheet" href="${path}/statics/css/responsive.css3.css"/>
        <style>
            img {
                max-width: 100%;
            }
        </style>
    </head>
    <body ng-app="app" class="bg-very-light-1">
    <div ng-controller="ArticleController" >

        <div class="container-fluid">
            <div class="row p-a-0 m-a-0">
                <ol class="breadcrumb col-xs-12 m-a-0 navbar-fixed-top hidden-md-up">
                    当前位置:
                    <li class="small-90 fa fa-home"><a href="/"> 首页</a></li>
                    <li class="small active">文章</li>
                </ol>
                <%--<div style="width: 10%"></div>--%>
                <div class="hidden-md-down" style="position:fixed;top:0;left:9%;width: 17%;z-index: 100;">
                    <%--<div class="list-group fixed-left-menu">--%>
                    <div class="card" style="border-top-left-radius: 0;border-top-right-radius: 0">
                        <div class="card-header p-a-0 list">
                            <img src="${path}/statics/image/lanzuan/icons/ico.jpg" class="card-img-top img-responsive">
                        </div>
                        <div class="card-block p-a-0" >
                            <ol class="breadcrumb m-a-0">
                                当前位置:
                                <li class="small-90 fa fa-home"><a href="/"> 首页</a></li>
                                <li class="small active">文章</li>
                            </ol>
                        </div>
                        <a href="#content" class="list-group-item" >${article.title}</a>
                    </div>
                </div>
                <div style="position:absolute;top:50px;left:0;width: 100%;" id="content" >
                  <div class="col-xs-12 p-a-0 m-a-0" >
                      <div class="col-xs-12">

                          <div class="col-xs-3"></div>
                          <div class="col-xs-8 p-r-lg"   >
                              <div class="col-xs-12 text-center p-a-0 m-a-0 ">
                                  <h4>${article.title}</h4>
                                  <div class="pull-left m-b-2em ">
                                      <label class="label label-info p-t-xs p-b-xs">本文已被阅读：${article.readTimes}次</label>
                                      <a href="#" ng-click="praise()">
                                          <i ng-class="{'animated bounceIn text-danger':animate}" class="fa fa-thumbs-o-up">({{praises}})</i></a>
                                  </div>
                                  <div class="pull-right m-b-2em ">

                                      <label class="label label-pill label-default">
                                          <c:if test="${not empty article.author}">作者:${article.author}</c:if>
                                          发布于:<fmt:formatDate value="${article.date}" pattern="yyyy-MM-dd"/>
                                      </label>
                                  </div>

                              </div>
                              <div class="col-xs-12 bg-white ">
                              ${article.content}
                              </div>
                          </div>
                          <div class="col-xs-1"></div>
                      </div>
                      <div class="col-xs-12 p-l-0 p-r-0 m-l-0 m-r-0 m-t-xl">
                              <jsp:include page="${path}/statics/page/included/footer.html"></jsp:include>
                      </div>
                  </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 p-t-50">
    </div>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <%--<script src="${path}/statics/js/jquery-3.1.1.min.js"></script>--%>
    <%--<script src="${path}/statics/js/tether.min.js"></script>--%>
    <%--<script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>--%>
    <script>
        (function () {
            "use strict";
            var app = angular.module('app', []);
            app.controller('ArticleController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
                $scope.animate=false;
                $scope.praises=${empty article.praises?0:article.praises};
                $scope.praise=function(){
                    if($scope.animate) return;
                   $http.get("/article/praise/${article.id}").success(function(data){
                       if(data.success){
                           $scope.animate=true;
                           $scope.praises++;
                       }
                   })
                }
            }])
        })()

    </script>
    </body>
</html>

