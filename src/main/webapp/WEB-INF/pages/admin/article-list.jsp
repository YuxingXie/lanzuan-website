<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>蓝钻科技--文章列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="yes"/>
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/bootstrap.custom.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/responsive.css3.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="app" ng-controller="AdminController">
<jsp:include page="${path}/statics/page/included/admin/navbar.jsp"></jsp:include>
<div class="alert alert-info">
    <h5 class="text-center">一些文章和页面的链接，需要填写链接地址时可以拷贝这些值</h5>

</div>
<div class="container"  ng-init="getArticles()">
    <div class="row">
        <div class="col-xs-12">
            <div class="btn-group p-b-1em p-t-1em">
                <%--<a class="btn btn-primary" href="/admin/file-editor">写新文章</a>--%>
            </div>
        </div>
        <div class="col-xs-12">
            <div>
                <div class="row large-180 bg-light-grey">

                    <div class="col-xs-4">标题</div>
                    <div class="col-xs-4">地址(uri)</div>
                    <div class="col-xs-4">操作</div>
                </div>

                <div class="small-90 row solid-silver-border-bottom hover-bg-color-grey p-t p-b" ng-repeat="article in articles">

                    <div class="col-xs-4">
                        {{article.title}}
                    </div>
                    <div class="col-xs-4">
                       /article/{{article.id}} <a class="blue-link" ng-href="/article/{{article.id}}" target="_blank"><i class="small-90 " style="text-decoration: underline">Go...</i></a>
                    </div>
                    <div class="col-xs-4">
                        <div class="btn-group btn-group-sm" >
                            <a class="btn btn-primary fa fa-edit" ng-href="/admin/article/file-editor/{{article.id}}" ng-if="article.byEditor">修改</a>

                            <a class="btn btn-danger fa fa-trash" ng-click="removeArticle(article.id)">删除</a>
                        </div>
                        <label class="label label-danger label-pill" ng-if="!article.byEditor">该文章不是由编辑器生成，不能被编辑器修改</label>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
<script src="${path}/app-js"></script>

</body>

