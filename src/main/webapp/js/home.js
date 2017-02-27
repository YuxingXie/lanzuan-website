
//
// Here is how to define your module 
// has dependent on mobile-angular-ui
//
(function () {
    "use strict";
    var app = angular.module('homeApp', []);

    app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
        $scope.test=function(){
            $scope.active=1;
        }
    }])
})();

