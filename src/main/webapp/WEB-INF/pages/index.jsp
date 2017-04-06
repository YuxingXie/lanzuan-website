<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
    <head lang="en">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>湖南蓝钻科技</title>
        <script src="${path}/statics/js/pace.js"></script>
        <link rel="stylesheet" href="${path}/statics/css/pace.css"/>
        <link rel="stylesheet" href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="${path}/statics/plugin/animate.css-master/animate.css"/>
        <link rel="stylesheet" href="${path}/statics/css/animate.css"/>
        <link rel="stylesheet" href="${path}/statics/css/style.css"/>
        <link rel="stylesheet" href="${path}/statics/css/color.css"/>
        <link rel="stylesheet" href="${path}/statics/css/bootstrap.custom.css"/>
        <link rel="stylesheet" href="${path}/statics/css/responsive.css3.css"/>
        <link rel="stylesheet" href="${path}/statics/css/lanzuan.css"/>

    </head>
    <body ng-app="app" ng-cloak="">
        <div ng-controller="HomeController">
            <c:forEach items="${webPage.pageComponents}" var="pageComponent">
                <c:import  url="${path}/${pageComponent.websiteUri}">
                    <c:param name="var" value="${pageComponent.var}" />
                    <c:param name="varU" value="${pageComponent.varU}"/>
                </c:import>
            </c:forEach>
        </div>

        <c:if test="${browser eq 'ie7' or browser eq 'ie8' or browser eq 'ie9'}">
            <script>alert("您使用的浏览器核心较低，可能导致网站显示不正常。")</script>
        </c:if>

        <div ng-include="'/statics/page/included/footer.jsp'"></div>
        <script src="/statics/js/jquery-3.1.1.min.js"></script>
        <script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.min.js"></script>
        <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>

    ${js}
    </body>
</html>
