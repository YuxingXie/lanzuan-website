<%@ page import="com.lanzuan.common.util.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<c:set var="word" value="hello"/>
<c:if test="false">
    <script></c:if>
(function () {
"use strict";
var app = angular.module('app', []);
app.controller('AdminController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
<c:if test="${not empty page}">
<c:forEach var="component" items="${page.pageComponents}">
    $scope.get${component.varU}=function(){
        $http.get('${component.dataUri}').success(function (data) {
            $scope.${component.var}=data;
        });
    }
</c:forEach>
</c:if>
}])
})()
<c:if test="false"></script></c:if>