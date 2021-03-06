<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <!--<base href="/demo/" />-->
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
    <meta name="apple-mobile-web-app-status-bar-style" content="yes" />
    <link href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/style.css" rel="stylesheet" type="text/css">
    <!--<link href="../../css/admin.css" rel="stylesheet" type="text/css">-->
    <link href="/statics/css/color.css" rel="stylesheet" type="text/css">
    <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="/statics/plugin/angular/1.4.8/angular-route.min.js"></script>


</head>
<body ng-app >
    <nav class="navbar">
        <div class="navbar-brand p-l-2em p-r-2em"> <i class="fa fa-hand-o-right fa-3x"></i></div>
        <ul class="nav navbar-nav p-t-1em">
            <li class="nav-item active">
                <a href="/" class="nav-link"><i class="fa fa-home">官方网站</i></a>
            </li>
        </ul>
    </nav>
    <div class="container-fluid" >

        <div class="row p-t-2em p-b-2em" style="background-repeat: no-repeat; background-size: 100% 100%;-moz-background-size:100% 100%;background-image: url('/statics/image/lanzuan/home/cloud.jpg');">
            <div class="col-xs-10 col-sm-6 col-md-4 col-xs-offset-1 col-sm-offset-3 col-md-offset-4">
                <div class="card">
                    <div class="card-img-top">
                        <img src="/statics/image/lanzuan/icons/ico.jpg" class="img-responsive"/>
                    </div>
                    <div class="card-header">
                        <h3 class="card-title text-primary text-center">官方网站管理系统</h3>
                    </div>
                    <div class="card-block bg-light-grey">

                        <form autocomplete="off" method="post" accept-charset="UTF-8" role="form" action="/admin/sign_up">
                            ${form}
                            <button class="btn btn-primary btn-block" type="submit"><i class="fa fa-sign-in"></i> 登 录</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

