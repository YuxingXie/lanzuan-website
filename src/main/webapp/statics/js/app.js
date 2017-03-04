(function () {
    "use strict";
    var app = angular.module('app', []);

    app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.getArticleSection=function(){
            $http.get("/statics/json/articleSection.json").success(function (data) {
                $scope.articleSection=data;
            });
        }
        $scope.getSortLinkSection=function(){
            $http.get("/statics/json/sortLinkSection.json").success(function (data) {
                $scope.sortLinkSection=data;
            });
        }

        $scope.getNavbar=function(){
            $http.get("/statics/json/navbar.json").success(function (data) {
                $scope.navbar=data;
            });
        }
        $scope.getCarousel=function(){
            $http.get("/statics/json/carousel.json").success(function (data) {
                $scope.carousel=data;
            });
        }
        $scope.getCardGroup=function(){
            $http.get("/statics/json/img-card-group.json").success(function (data) {
                $scope.cardGroup=data;
            });
        }
        $scope.getCollapseImageTitleText=function(){
            $http.get("/statics/json/collapse-img-title-text-block.json").success(function (data) {
                $scope.collapseBlock=data;
                $scope._active=0;
                //console.log(JSON.stringify(data))
            });
        }
    }])
    app.controller('AdminController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.initAdmin=function(){

            $scope.getMenu();
        }
        $scope.getMenu=function(){
            $http.get("/statics/json/menu.json").success(function (data) {
                $scope.menuItems=data;
            });
        }

    }])

})();

