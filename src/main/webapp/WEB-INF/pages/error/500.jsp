<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>湖南蓝钻科技</title>
  <link rel="stylesheet" href="/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
  <link rel="stylesheet" href="/statics/css/style.css"/>
  <link rel="stylesheet" href="/statics/css/color.css"/>
  <link rel="stylesheet" href="/statics/css/bootstrap.custom.css"/>
  <link rel="stylesheet" href="/statics/css/responsive.css3.css"/>
</head>
<body ng-app>
<div>
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
        <c:choose>
          <c:when test="${not empty reportSuccess and reportSuccess eq true}">
              <div class="alert alert-info p-t-lg p-b-lg m-t-xl m-b-xl"><h4>感谢您的参与，我们会努力改进!</h4></div>
          </c:when>
          <c:otherwise>
            <i class="fa fa-bolt fa-2x p-t-2em p-b-2em padding-left-2em text-warning">出错了，快呼叫攻城狮吧</i>
            <div ng-init="showErr=false">
              <button class="btn btn-info btn-sm m-a-md" ng-click="showErr=!showErr"><i class="fa fa-question"></i>
                好奇 <i class="fa" ng-class="{'fa-angle-double-right':!showErr,'fa-angle-double-down':showErr}"> </i></button>
            </div>

            <div class="solid-silver-border rounded-3 p-a-md m-b-lg" ng-class="{'hidden':!showErr,'block':showErr}">
              <form action="/error/report" autocomplete="off" method="post" accept-charset="UTF-8" role="form">
                <fieldset>
                  <div class="form-group input-group input-group-sm">
                    <label class="input-group-addon">错误信息</label>
                    <textarea readonly name="exception" class="form-control" cols="600" rows="4"><%=exception%></textarea>
                  </div>
                  <div class="form-group input-group input-group-sm">
                    <span class="input-group-addon">请求路径</span>
                    <input readonly type="text" name="uri" class="form-control" value="<%= request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI) %>"/>
                  </div>
                  <div class="form-group input-group input-group-sm">
                    <label class="input-group-addon">我叫</label>
                    <input class="form-control" placeholder="热心用户" name="reporterName" type="text">
                  </div>
                </fieldset>
                <button class="btn btn-primary btn-block">报告给我们，帮助我们改进</button>
              </form>

            </div>
          </c:otherwise>
        </c:choose>

      </div>

    </div>
  </div>
</div>
<jsp:include page="${path}/statics/page/included/footer.html"></jsp:include>
<script src="/statics/js/jquery-3.1.1.min.js"></script>
<script src="/statics/js/tether.min.js"></script>
<script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.min.js"></script>
<<script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>


</body>
</html>
