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
    <script async="" src="/statics/plugin/picturefill-master/dist/plugins/intrinsic-dimension/pf.intrinsic.min.js"></script>
    <script async="" src="/statics/plugin/picturefill-master/dist/picturefill.min.js"></script>
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
<body ng-app>
    <picture  ng-init="smSrc='/statics/image/icon/1489542403439.jpg';mdSrc='/statics/image/icon/1489631134133.jpg';defaultSrc='/statics/image/icon/1490414985456.jpg'">
        <source srcset="{{smSrc}}" media="(min-width: 48em)" class="img-responsive">
        <source srcset="{{mdSrc}}" media="(min-width: 72em)" class="img-responsive">
        <img srcset="{{defaultSrc}}" alt="image description" class="img-responsive">
    </picture>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
</body>

