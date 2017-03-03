(function () {
    "use strict";
    var app = angular.module('app', []);

    app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.getNews=function(){
            $http.get("/statics/json/news.json").success(function (data) {
                $scope.newsList=data;
            });
        }
        $scope.getNavbar=function(){
            $http.get("/statics/json/navbar.json").success(function (data) {
                $scope.navbar=data;
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

