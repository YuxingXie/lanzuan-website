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
        .tabs {
            /*width: 650px;*/
            /*float: none;*/
            list-style: none;
            /*position: static;*/
            /*margin: 0 0 0 10px;*/
            text-align: left;
        }
        .tabs li {
            float: left;
            /*display: block;*/
        }

        .tabs label {
            display: block;
            padding: 14px 21px;
            border-radius: 2px 2px 0 0;
            font-size: 20px;
            font-weight: normal;
            text-transform: uppercase;
            background: #3963ad;
            cursor: pointer;
            /*position: relative;*/
            /*top: 4px;*/
            -webkit-transition: all 0.2s ease-in-out;
            -moz-transition: all 0.2s ease-in-out;
            -o-transition: all 0.2s ease-in-out;
            transition: all 0.2s ease-in-out;
        }
        .tabs label:hover {
            background: #3d8bd9;
        }
        .tab-content {
            /*position: absolute;*/
            width: 100%;
            padding: 25px;
            /*background: #539aff;*/
        }

    </style>
</head>
<body ng-app="app" class="bg-very-light-1">
<div ng-controller="ArticleController" >
    <c:import  url="${path}/${pageComponent.websiteUri}">
        <c:param name="var" value="${pageComponent.var}" />
        <c:param name="varU" value="${pageComponent.varU}"/>
    </c:import>
    <ol class="breadcrumb hidden-md-down p-l-xl">
        <li class="small">蓝钻科技</li>
        <li class="small active">新闻中心</li>
    </ol>
    <div class="container-fluid " ng-init="getArticles()">

        <div class="row">

            <div class="col-xs-12 col-md-10 col-md-push-1 bg-white">

                <div class="col-xs-12 col-md-4">
                    <div class="card">
                        <div class="card-header">乔布斯会见谢建</div>
                        <img src="/statics/image/lanzuan/cydl.jpg" class="card-img full-width"/>
                        <div class="card-block">
                            乔布斯会见谢建乔布斯会见谢建,乔布斯会见谢建，乔布斯会见谢建，乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建。
                        </div>
                        <div class="card-footer">
                            <i class="fa fa-thumbs-up"></i>
                            <span class="">2012-05-05</span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="card">
                        <div class="card-header">乔布斯会见谢建</div>
                        <img src="/statics/image/lanzuan/zfdl.jpg" class="card-img full-width"/>
                        <div class="card-block">
                            乔布斯会见谢建乔布斯会见谢建,乔布斯会见谢建，乔布斯会见谢建，乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建。
                        </div>
                        <div class="card-footer">
                            <i class="fa fa-thumbs-up"></i>
                            <span class="">2012-05-05</span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="card">
                        <div class="card-header">乔布斯会见谢建</div>
                        <img src="/statics/image/lanzuan/cydl.jpg" class="card-img full-width"/>
                        <div class="card-block">
                            乔布斯会见谢建乔布斯会见谢建,乔布斯会见谢建，乔布斯会见谢建，乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建。
                        </div>
                        <div class="card-footer">
                            <i class="fa fa-thumbs-up"></i>
                            <span class="">2012-05-05</span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12">
                    <ul class="tabs p-a-0">
                        <li>
                            <label>热点新闻</label>

                        </li>

                        <li>

                            <label>公司通告</label>

                        </li>
                    </ul>

                </div>
                <div class="col-xs-12 border-t-s-silver p-y-md">
                    <div class="col-md-4 col-xs-12">
                        <img src="/statics/image/lanzuan/cydl.jpg" class="img-responsive"/>
                    </div>
                    <div class="col-md-8 col-xs-12">
                        <div class="card m-t-xs">
                            <div class="card-header"><a href="/">乔布斯会见谢建</a></div>
                            <div class="card-block">
                                <div class="card-text">
                                    乔布斯会见谢建乔布斯会见谢建,乔布斯会见谢建，乔布斯会见谢建，乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建。
                                </div>
                            </div>
                            <div class="card-footer">
                                <i class="fa fa-thumbs-up"></i>
                                <span class="">2012-05-05</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 border-t-s-silver p-y-md">
                        <div class="col-md-4 col-xs-12">
                            <img src="/statics/image/lanzuan/cydl.jpg" class="img-responsive"/>
                        </div>
                        <div class="col-md-8 col-xs-12">
                            <div class="card m-t-xs">
                                <div class="card-header">乔布斯会见谢建</div>
                                <div class="card-block">
                                    乔布斯会见谢建乔布斯会见谢建,乔布斯会见谢建，乔布斯会见谢建，乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建乔布斯会见谢建。
                                </div>
                                <div class="card-footer">
                                    <i class="fa fa-thumbs-up"></i>
                                    <span class="">2012-05-05</span>
                                </div>
                            </div>

                        </div>
                    </div>


            </div>


        </div>
    </div>
</div>
<jsp:include page="${path}/statics/page/included/footer.jsp"></jsp:include>
<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>

<script>
    (function () {
        "use strict";
        var app = angular.module('app', []);
        app.filter('to_trusted', ['$sce', function($sce){
            return function(text) {
                return $sce.trustAsHtml(text);
            };
        }]);
        app.controller('ArticleController', ["$scope", "$http",function ( $scope, $http) {
            $scope.getNavBar=function(){
                $http.get('/navbar/home/data').success(function (data) {
                    $scope.navBar=data;
                });
            }
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

        }])
    })()

</script>
</body>
</html>

