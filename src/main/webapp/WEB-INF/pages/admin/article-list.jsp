<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>本站地址</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="yes"/>
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="app">
<jsp:include page="${path}/statics/page/included/admin/navbar.jsp"></jsp:include>
<div class="container-fluid" ng-controller="AdminController" ng-init="getArticles()">
    <div class="row">
        <div class="alert alert-info">
            <h5 class="text-center">一些文章和页面的链接，需要填写链接地址时可以拷贝这些值</h5>

        </div>
        <div class="alert alert-warning">
            <%--<ul class="list-unstyled">--%>

                <%--<li><i class="fa fa-graduation-cap fa-fw"></i>如果多个方案都为“可用”状态，我们只会应用查到的第一个方案，为了确保使用到正确的方案，请把不用的其它方案设为“禁用”。--%>
                <%--</li>--%>

            <%--</ul>--%>
        </div>
        <div class="col-xs-12">
            <div>
                <div class="row p-a-0 m-a-0 large-180 bg-light-grey">
                    <div class="col-xs-2">类型</div>
                    <div class="col-xs-4">页面名称（或文章标题）</div>
                    <div class="col-xs-6">地址(uri)</div>
                </div>
                <div class="row p-a-0 m-a-0 solid-silver-border-top solid-silver-border-bottom hover-bg-color-grey" >
                    <div class="col-xs-2">
                        本站链接
                    </div>
                    <div class="col-xs-4">
                        首页
                    </div>
                    <div class="col-xs-6">
                       ${path}
                    </div>
                </div>
                <div class="row p-a-0 m-a-0 solid-silver-border-top solid-silver-border-bottom hover-bg-color-grey" ng-repeat="article in articles">
                    <div class="col-xs-2">
                       文章
                    </div>
                    <div class="col-xs-4">
                        {{article.title}}
                    </div>
                    <div class="col-xs-6">
                       /article/{{article.id}}
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
<script src="${path}/statics/js/app.js"></script>

</body>
