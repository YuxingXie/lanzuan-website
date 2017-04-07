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
<div ng-controller="ArticleController"  ng-init="getArticles()">

    <div class="container-fluid">
        <div class="row p-a-0 m-a-0">

            <div class="col-xs-12 col-md-3 p-a-0 m-a-0" style="position:fixed;top:0;z-index:100;">

                <div class="col-xs-12 col-md-push-2 col-md-10 m-a-0 p-a-0">
                    <div class="card" style="border-top-left-radius: 0;border-top-right-radius: 0">
                        <div class="card-header p-a-0 list hidden-md-down">
                            <img src="${path}/statics/image/lanzuan/icons/ico.jpg" class="card-img-top img-responsive">
                        </div>
                        <div class="card-block p-a-0 m-a-0" >
                            <ol class="breadcrumb m-a-0 p-a-xs">
                                当前位置:
                                <li class="small-90 fa fa-home"><a href="/"> 首页</a></li>
                                <li class="small active">文章中心</li>
                            </ol>


                        </div>



                    </div>
                </div>

            </div>
            <div style="position:absolute;top:50px;left:0;width: 100%;"  >
                <div class="col-xs-12 p-a-0 m-a-0" >
                    <div class="col-xs-12">
                        <div class="col-md-7 col-md-push-4 col-xs-12"   >
                            <div class="col-xs-12 text-center p-a-0 m-a-0 ">
                                <h4>{{article.title}}</h4>
                                <div class="pull-left m-b-2em ">
                                    <label class="label label-info p-t-xs p-b-xs">本文已被阅读：<span ng-bind="article.readTimes"></span>次</label>
                                    <a href="#" ng-click="praise()">
                                        <i ng-class="{'animated bounceIn text-danger':animate}" class="fa fa-thumbs-o-up">({{praises}})</i></a>
                                </div>
                                <div class="pull-right m-b-2em ">

                                    <label class="label label-pill bg-grey-2" ng-if="article.author ||article.date">
                                        <span ng-if="article.author">作者:{{article.author}}</span>
                                        <span ng-if="article.date">发布于:<span ng-bind="article.date"></span></span>
                                    </label>
                                </div>

                            </div>
                            <div class="col-xs-12 bg-white" id="content">

                            </div>
                            <a class="list-group-item hidden-md-down" ng-repeat="article in pagination.data"  href="#" ng-click="showArticle(article)" ng-bind="article.title"></a>
                            <nav class="p-l-md">
                                <ul class="pagination">
                                    <%--<li ng-if="pagination.pages&&pagination.pages.length&&pagination.pages.length>pagination.displayable&&pagination.displayable*pagination.times<=pagination.pages.length">--%>
                                    <li ng-if="pagination.times>1">
                                        <a href="#" aria-label="Previous" ng-click="prevPage()">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>
                                    <li  ng-class="{'active':($index)+(pagination.times-1)*pagination.displayable===pagination.active}" ng-if="$index+1<=pagination.displayable&&($index+1)+(pagination.times-1)*pagination.displayable<=pagination.totalPage" ng-repeat="page in pagination.pages track by $index">
                                        <a href="#" ng-bind="($index+1)+(pagination.times-1)*pagination.displayable" ng-click="toPage(($index+1)+(pagination.times-1)*pagination.displayable)"></a>
                                    </li>
                                    <li ng-if="pagination.pages&&pagination.pages.length&&pagination.pages.length>pagination.displayable&&pagination.displayable*pagination.times<=pagination.pages.length">
                                        <a href="#" aria-label="Next" ng-click="nextPage()">
                                            <span aria-hidden="true">&raquo;</span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
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

<script>
    (function () {
        "use strict";
        var app = angular.module('app', []);
        app.controller('ArticleController', ["$scope", "$http",function ( $scope, $http) {
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
            $scope.getArticles=function(){
                $http.get("/article/list/data").success(function (data) {
                    $scope.articles=data;
                    var size=4;
                    var totalPage=Math.ceil(data.length/size);
            console.log(data.length)
            console.log(totalPage)
                    var pages=new Array(totalPage);
                    $scope.pagination={size:size,total:data.length,totalPage:totalPage,pages:pages,displayable:4,times:1,active:0};
                    $scope.pagination.data=data.slice(0,$scope.pagination.size);

                });
            }

            $scope.toPage=function(index){
                var begin=(index-1)*$scope.pagination.size;
                var end=index*$scope.pagination.size;
                $scope.pagination.data=$scope.articles.slice(begin,end);
                $scope.pagination.active=index-1;
            }
            $scope.nextPage=function(){
                var firstDisplayable=$scope.pagination.times*$scope.pagination.displayable;
                $scope.pagination.times++;
                $scope.pagination.data=$scope.articles.slice($scope.pagination.size*firstDisplayable,$scope.pagination.size*(firstDisplayable+1));
                $scope.pagination.active=firstDisplayable;
            }
            $scope.prevPage=function(){
                var firstDisplayable=($scope.pagination.times-2)*$scope.pagination.displayable;
                $scope.pagination.times--;
                $scope.pagination.data=$scope.articles.slice($scope.pagination.size*firstDisplayable,$scope.pagination.size*(firstDisplayable+1));
                $scope.pagination.active=firstDisplayable;
            }
            $scope.showArticle=function(article){
                $scope.article=article;
                document.getElementById("content").innerHTML=article.content;
            }
        }])
    })()

</script>
</body>
</html>

