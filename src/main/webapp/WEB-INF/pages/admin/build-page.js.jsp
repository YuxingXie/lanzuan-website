<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<c:if test="false"><script></c:if>
(function () {
    ;"use strict";

    ;var app = angular.module('app', ['ang-drag-drop','ngRoute']);

<c:if test="${not empty pageComponentList}">
    ;app.config(function ($routeProvider) {
    <c:forEach var="pageComponent" items="${pageComponentList}">
        ;$routeProvider.when('/${pageComponent.var}', {templateUrl: '${pageComponent.websiteUri}?var=${pageComponent.var}&varU=${pageComponent.varU}', reloadOnSearch: false});
    </c:forEach>
    });
</c:if>

    ;app.controller('AdminController', ["$rootScope", "$scope", "$http", "$location", "$window", function ($rootScope, $scope, $http, $location, $window) {

<c:if test="${not empty pageComponentList}">

    <c:forEach var="pageComponent" items="${pageComponentList}">
       ${pageComponent.jsGetterMethods}
    </c:forEach>

</c:if>
        ;$http.get("/admin/pageComponent/list-data").success(function(data){
            ;$scope.pageComponents=data;
        ;});

        ;$scope.getWebPage=function(){
            ;$scope.webPage={"pageComponents":[

            ]};
            ;if($scope.webPage&&$scope.webPage.pageComponents&&$scope.webPage.pageComponents.length){

            }else{
                ;$scope.webPage={"pageComponents":[
                    {name:"my navbar",var:"navbar",varU:"Navbar",websiteUri : "/statics/page/included/component/navbar/navbar-md-down-fix-bottom.jsp" }
                ]};
            };
        };


        ;$scope.addText = "";


        ;$scope.dropSuccessHandler = function($event,index,array){

        };

        $scope.onDrop = function($event,$data,array,index){

            ;array.splice(index+1,0,$data);
        };

    }])
})()
<c:if test="false"></script></c:if>