(function () {
    "use strict";
    var app = angular.module('homeApp', []);

    app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.initHome=function(){
            $scope.getNews();
        }
        $scope.getNews=function(){
            $http.get("/json/news.json").success(function (data) {
                $scope.newsList=data;
            });
        }
    }])
})();

