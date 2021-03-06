<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>湖南蓝钻科技</title>
    <link rel="stylesheet" href="/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/statics/plugin/animate.css-master/animate.css"/>
    <link rel="stylesheet" href="/statics/css/animate.css"/>
    <link rel="stylesheet" href="/statics/css/style.css"/>
    <link rel="stylesheet" href="/statics/css/color.css"/>
    <link rel="stylesheet" href="/statics/css/bootstrap.custom.css"/>
    <link rel="stylesheet" href="/statics/css/responsive.css3.css"/>
</head>
<body ng-app="homeApp">
    <div ng-controller="HomeController" >
        <div class="container m-t-2em">
            <div class="row">
                <div class="col-xs-12 col-md-3">
                    <div class="list-group fixed-left-menu">
                        <div class="list-group-item p-a-0 hidden-sm-down">
                            <img src="/statics/image/lanzuan/icons/ico.jpg" class="img-ico-larger m-l-2em">
                        </div>
                        <div class="list-group-item p-a-0" >

                            <ol class="breadcrumb m-a-0">
                                当前位置:
                                <li class="small-90 fa fa-home"><a href="${path}/"> 首页</a></li>
                                <li class="small active">404</li>
                            </ol>
                        </div>
                        <a href="#title" class="list-group-item hidden-sm-down" >页面找不到了</a>

                        <div class="list-group-item p-a-0 hidden-sm-down" >
                            <img src="/statics/image/lanzuan/con_01.jpg" class="full-width"/>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-9">
                    <i class="fa fa-warning fa-5x p-t-2em p-b-2em p-l-2em text-warning">页面找不到了</i>
                </div>
        </div>
        </div>
    </div>
    <footer>
        <div class="row bg-black-2 small-90 p-t-2em p-b-2em md-down-m-b-1em md-down-text-small-70 m-l-0 m-r-0">
                <span class="col-md-offset-2 col-xs-12 col-md-3 color-light-grey-2 text-center">
                    ©2000-2017&nbsp;&nbsp;&nbsp;&nbsp;湖南蓝钻科技有限公司&nbsp;&nbsp;版权所有
               </span>
                <span class="col-xs-12 col-md-2 text-center">
                    <a href="http://www.miibeian.gov.cn/publish/query/indexFirst.action" target="_blank" style="color:#efefef;">粤ICP备08126214号-5</a>
                </span>
            <div class="col-xs-12 col-md-3">
                    <span class="col-xs-6 col-md-5 text-right color-light-grey-2 border-r-s-silver">
                        <a  href="page/building.html" target="_blank" style="color:#efefef;">法律声明</a>
                    </span>
                    <span class="col-xs-6 col-md-5 text-left color-light-grey-2"><a  href="page/article/privacy-protection.html" target="_blank" style="color:#efefef;">隐私保护</a>
                    </span>
            </div>

        <img class="center-block height-2-5em" src="http://szcert.ebs.org.cn/Images/newGovIcon.gif"/>
</div>
    </footer>
    <script src="/statics/js/jquery-3.1.1.min.js"></script>
    <script src="/statics/js/tether.min.js"></script>
    <script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
    <!--<script src="../plugin/angular/1.4.8/angular.min.js"></script>-->


</body>
</html>
