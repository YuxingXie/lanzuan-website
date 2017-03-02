(function () {
    "use strict";
    var app = angular.module('AdminApp', []);

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

