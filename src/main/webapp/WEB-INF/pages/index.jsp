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
        <%--<script src="${path}/statics/js/pace.js"></script>--%>
        <%--<link rel="stylesheet" href="${path}/statics/css/pace.css"/>--%>
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


                <%--<c:set var="pageComponent1" value="${webPage.pageComponents[0]}"/>--%>
                <%--<c:set var="pageComponent2" value="${webPage.pageComponents[1]}"/>--%>
                <%--<c:set var="pageComponent3" value="${webPage.pageComponents[2]}"/>--%>
                <%--<c:set var="pageComponent4" value="${webPage.pageComponents[3]}"/>--%>
                <%--<c:set var="pageComponent5" value="${webPage.pageComponents[4]}"/>--%>
                <%--<c:set var="pageComponent6" value="${webPage.pageComponents[5]}"/>--%>
                <%--<c:set var="pageComponent7" value="${webPage.pageComponents[6]}"/>--%>

                <%--<c:import  url="${path}/${pageComponent1.websiteUri}">--%>
                    <%--<c:param name="var" value="${pageComponent1.var}" />--%>
                    <%--<c:param name="varU" value="${pageComponent1.varU}"/>--%>
                <%--</c:import>--%>


                <%--<c:import  url="${path}/${pageComponent2.websiteUri}">--%>
                    <%--<c:param name="var" value="${pageComponent2.var}" />--%>
                    <%--<c:param name="varU" value="${pageComponent2.varU}"/>--%>
                <%--</c:import>--%>


                <%--<c:import  url="${path}/${pageComponent3.websiteUri}">--%>
                    <%--<c:param name="var" value="${pageComponent3.var}" />--%>
                    <%--<c:param name="varU" value="${pageComponent3.varU}"/>--%>
                <%--</c:import>--%>


                <%--<c:import  url="${path}/${pageComponent4.websiteUri}">--%>
                    <%--<c:param name="var" value="${pageComponent4.var}" />--%>
                    <%--<c:param name="varU" value="${pageComponent4.varU}"/>--%>
                <%--</c:import>--%>


                <%--<c:import  url="${path}/${pageComponent5.websiteUri}">--%>
                    <%--<c:param name="var" value="${pageComponent5.var}" />--%>
                    <%--<c:param name="varU" value="${pageComponent5.varU}"/>--%>
                <%--</c:import>--%>


                <%--<c:import  url="${path}/${pageComponent6.websiteUri}">--%>
                    <%--<c:param name="var" value="${pageComponent6.var}" />--%>
                    <%--<c:param name="varU" value="${pageComponent6.varU}"/>--%>
                <%--</c:import>--%>


                <%--<c:import  url="${path}/${pageComponent7.websiteUri}">--%>
                    <%--<c:param name="var" value="${pageComponent7.var}" />--%>
                    <%--<c:param name="varU" value="${pageComponent7.varU}"/>--%>
                <%--</c:import>--%>

            <c:forEach items="${webPage.pageComponents}" var="pageComponent">
                <c:import  url="${path}/${pageComponent.websiteUri}">
                <c:param name="var" value="${pageComponent.var}" />
                <c:param name="varU" value="${pageComponent.varU}"/>
                </c:import>
            </c:forEach>
        </div>

        <div ng-include="'/statics/page/included/footer.jsp'"></div>
        <script src="/statics/js/jquery-3.1.1.min.js"></script>
        <script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.min.js"></script>
        <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>

    ${js}
    </body>
</html>
