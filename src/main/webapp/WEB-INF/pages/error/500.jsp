<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <div class="container margin-top-2em">
    <div class="row">
      <div class="col-xs-12 col-md-3">
        <div class="list-group fixed-left-menu">
          <div class="list-group-item p-a-0 hidden-sm-down">
            <img src="/statics/image/lanzuan/icons/ico.jpg" class="img-ico-larger margin-left-2em">
          </div>
          <div class="list-group-item p-a-0" >

            <ol class="breadcrumb m-a-0">
              当前位置:
              <li class="small-90 fa fa-home"><a href="${path}/"> 首页</a></li>
              <li class="small active">500</li>
            </ol>
          </div>
          <a href="#title" class="list-group-item hidden-sm-down" >该页有点错误</a>

          <div class="list-group-item p-a-0 hidden-sm-down" >
            <img src="/statics/image/lanzuan/con_01.jpg" class="full-width"/>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-md-9">
        <i class="fa fa-bolt fa-2x padding-top-2em padding-bottom-2em padding-left-2em text-warning">出错了，快呼叫攻城狮吧</i>
      </div>
    </div>
  </div>
</div>
<jsp:include page="${path}/statics/page/included/footer.html"></jsp:include>
<script src="/statics/js/jquery-3.1.1.min.js"></script>
<script src="/statics/js/tether.min.js"></script>
<script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
<!--<script src="../plugin/angular/1.4.8/angular.min.js"></script>-->


</body>
</html>
