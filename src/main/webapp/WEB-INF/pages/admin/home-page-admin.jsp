<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
    <meta name="apple-mobile-web-app-status-bar-style" content="yes" />
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/lanzuan.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="app" >
    <div class="" ng-controller="AdminController">
        <jsp:include page="${path}/admin/admin-navbar"></jsp:include>
        <div class="alert alert-info m-t-md">
            <h5 class="text-center">网站首页管理</h5>
        </div>

        <div class="container">
            <div class="alert alert-warning m-t-md small-90">
                <ul class="list-unstyled ">
                    <li class="">
                        首页按上下顺序被分成许多部分(组件)，每一部分都可以单独编辑；
                    </li>
                    <li class="">
                        “直接修改”会使修改立即生效，“安全模式”修改后会生成方案，方案需要手动指定生效。
                    </li>
                </ul>
            </div>
            <div class="row">
                <div class="col-xs-9 m-a-0 p-a-0">
                    <h5>组件效果</h5>
                </div>
                <div class="col-xs-34 m-a-0 p-l-2em" >
                     <h5>管理组件</h5>
                </div>
            </div>
            <c:forEach items="${webPage.pageComponents}" var="pageComponent">
                <div class="row p-t-2em p-b-2em"style="border-top: solid #000 1px">
                    <div class="col-xs-9 m-a-0 p-a-0">
                        <jsp:include page="${path}/${pageComponent.websiteUri}">
                            <jsp:param name="var" value="${pageComponent.var}"/>
                            <jsp:param name="varU" value="${pageComponent.varU}"/>
                        </jsp:include>
                    </div>

                    <div class="col-xs-3 m-a-0 p-l-2em small-90" >
                        <div>这是一个${pageComponent.name}</div>
                        <div>${pageComponent.remark}</div>

                        <div class="btn-group btn-group-sm">
                            <a class="btn btn-primary white-link" href="${path}/admin/page-component/edit/${pageComponent.id}?mode=admin">直接修改</a>
                            <a class="btn btn-primary white-link" href="#" onclick="alert('\n\n\n功能开发中......\n\n')">安全模式</a>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
    <script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
    <script src="${path}/admin/home-page-admin-js?pageId=${webPage.id}"></script>
</body>

