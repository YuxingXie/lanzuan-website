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
        <link rel="stylesheet" href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.min.css"/>
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
            a:hover,a:link{
                text-decoration: none;
            }
        </style>
    </head>
    <body ng-app="app" class="bg-very-light-1">
    <div ng-controller="ArticleController" >
        <div class="container-fluid">
            <div class="row p-a-0">
                <div class="col-xs-12 col-md-4 p-a-0 m-a-0" style="position:fixed;top:0;z-index:100;">
                    <div class="col-xs-12 col-md-push-3 col-md-8 m-a-0 p-a-0">
                        <div class="card" style="border-top-left-radius: 0;border-top-right-radius: 0">
                            <div class="card-header p-a-0 list hidden-md-down">
                                <img src="${path}/statics/image/lanzuan/icons/ico.jpg" class="card-img-top img-responsive">
                            </div>
                            <div class="card-block p-a-0 m-a-0" >
                                <ol class="breadcrumb m-a-0 p-a-xs">
                                    <li class="small-90 fa fa-home color-grey-2"><a href="/"> 首页</a></li>
                                    <li class="small active"><a href="/website/article/list">文章中心</a></li>
                                </ol>
                            </div>
                            <a href="#content" class="list-group-item hidden-md-down">${article.title}</a>
                        </div>
                    </div>

                </div>
                <div style="position:absolute;top:50px;left:0;width: 100%;" id="content" >
                  <div class="col-xs-12 p-a-0 m-a-0" >
                      <div class="col-xs-12">
                          <div class="col-md-7 col-md-push-4 col-xs-12"   >
                              <div class="col-xs-12 text-center p-a-0 m-a-0 ">
                                  <h4 class="color-grey" style="letter-spacing: 3px">${article.title}</h4>
                                  <div class="pull-right">
                                      <label class="label label-info p-y-xs m-r-10">
                                          阅读:${article.readTimes}次
                                          赞:<span ng-bind="praises"></span>次
                                      </label>

                                  </div>

                              </div>
                              <div class="col-xs-12 bg-white ">
                                ${article.content}
                              </div>
                              <div class="col-xs-12 text-center m-t-md">

                                      <span class="fa-stack fa-2x" ng-class="{'hover-hand':!animate}"  ng-click="praise()">
                                          <i class="fa fa-circle fa-stack-2x" ng-class="{'text-danger':animate,' text-primary':!animate}" ></i>
                                          <i ng-class="{'animated bounceIn':animate}" class="fa fa-thumbs-o-up fa-stack-1x fa-inverse" ></i>
                                      </span>


                              </div>
                          </div>
                      </div>
                      <div class="col-xs-12 p-l-0 p-r-0 m-l-0 m-r-0 m-t-0">
                              <jsp:include page="${path}/statics/page/included/footer.jsp"></jsp:include>
                      </div>
                  </div>
                </div>
            </div>
        </div>
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

