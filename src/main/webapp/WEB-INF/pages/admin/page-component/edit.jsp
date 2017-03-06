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
</head>
<body ng-app="app" >
    <div class="container-fluid  padding-0 margin-0" ng-controller="AdminController" ng-init="initAdmin()">
        <nav class="row navbar">
            <div class="navbar-brand">
                <c:choose>
                    <c:when test="${empty user.sex or user.sex eq 'male'}">
                        <i class="fa fa-male padding-left-2em"></i>
                    </c:when>
                    <c:otherwise><i class="fa fa-female padding-left-2em"></i></c:otherwise>
                </c:choose>
                欢迎您，${user.name}
                <c:choose>
                    <c:when test="${empty user.sex or user.sex eq 'male'}">先生</c:when>
                    <c:otherwise>女士</c:otherwise>
                </c:choose>!
            </div>
            <ul class="nav navbar-nav">
                <li class="nav-item pull-right">
                    <button class="nav-link btn btn-primary btn-sm" href="javascript:void(0)"><i class="fa fa-sign-out"></i>退出</button>
                </li>
            </ul>
        </nav>
        <div class="row" role="tablist">
            <div class="card  margin-bottom-0 margin-top-0" ng-repeat="menuItem in menuItems">
                <div class="card-header margin-bottom-0" role="tab">
                    <a href="javascript:void(0)" ng-click="menuItem.collapse=!menuItem.collapse">

                        <h5 class="card-title  margin-bottom-0">
                            <i class="{{menuItem.class}} padding-left-10"></i>
                            {{menuItem.name}}
                            <i class="fa pull-right padding-right-30"
                               ng-class="{'fa-angle-down':!menuItem.collapse,'fa-angle-right':menuItem.collapse}"></i>
                        </h5>
                    </a>
                </div>
                <div class="card-block padding-0 margin-0"
                     ng-class="{'collapse':menuItem.collapse,'in':!menuItem.collapse}">
                    <div class="list-group">
                        <a class="list-group-item" ng-repeat="subMenuItem in menuItem.menuItem" ng-href="{{subMenuItem.link}}">
                            <i class="{{subMenuItem.class}} padding-left-20"></i>
                            <span class="label label-default label-pill pull-right margin-right-20">14</span>{{subMenuItem.name}}
                        </a>

                    </div>
                </div>
            </div>

        </div>
        <div class="row padding-top-2em margin-0">
            <div class="text-center">
                <button class="btn btn-primary" ng-click="editable=!editable">显示可编辑项</button>
                点击<button class="btn btn-primary" id="start">开始编辑{{test}}</button>按钮后，右键点击可编辑的元素可弹出一个右键菜单（不是浏览器菜单），请点击菜单项选择相应操作。
            </div>
            <div ng-controller="HomeController">

            <div class="row"style="border-top: solid #000 1px">
                        <div class="col-xs-8 m-a-0 p-a-0">
                            <jsp:include page="${pageComponent.uri}"></jsp:include>
                        </div>

                        <div class="col-xs-4 m-a-0 p-a-0" >
                            <div>导航组件名称：${pageComponent.name}：</div>
                            <div>
                                <div>导航条品牌标示类型：{{navbar.navbarBrand.type=='image'?"图片":"文字"}}</div>
                                <div>导航条品牌标示值：{{navbar.navbarBrand.value}}</div>
                                <div>组件说明：${pageComponent.remark}</div>

                            </div>
                        </div>
                    </div>


            </div>
        </div>
    </div>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="${path}/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
    <script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
    <script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
    <script src="${path}/statics/plugin/bootstrap-contextmenu-master/bootstrap-contextmenu.js"></script>
    <script src="${path}/statics/js/app.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
//            $(document).on("click", $("#start"), function() {
//                $("img").data("target","#img-context-menu");
//                $("img").contextmenu({
//                    onItem: function(context, e) {
//                        console.log(context.attr("class"));
//                    }
//                });
//                $("a").data("target","#a-context-menu");
//                $("a").contextmenu();
//                $(".fa").data("target","#context-menu");
//                $(".fa").contextmenu();
//
//            });
        });
    </script>

</body>
