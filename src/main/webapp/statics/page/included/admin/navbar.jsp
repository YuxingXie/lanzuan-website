<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<nav class="navbar bg-inverse" ng-init="fixTop=false" ng-class="{'navbar-fixed-top':fixTop}">
  <div class="navbar-brand">
    <c:choose>
      <c:when test="${empty sessionScope.administrator.sex or sessionScope.administrator.sex eq 'male'}">
        <i class="fa fa-male padding-left-2em"></i>
      </c:when>
      <c:otherwise><i class="fa fa-female padding-left-2em"></i></c:otherwise>
    </c:choose>
    欢迎您，${sessionScope.administrator.name}
    <c:choose>
      <c:when test="${empty sessionScope.administrator.sex or sessionScope.administrator.sex eq 'male'}">先生</c:when>
      <c:otherwise>女士</c:otherwise>
    </c:choose>!
  </div>
  <ul class="nav navbar-nav">
    <li class="nav-item">
      <a class="nav-link white-link" href="${path}/"><i class="fa fa-home"></i>官网首页 </a>
    </li>
    <li class="nav-item">
      <a class="nav-link white-link" href="${path}/admin/index"><i class="fa fa-gears"></i>管理首页 </a>
    </li>
    <li class="nav-item">
      <a class="nav-link white-link" href="${path}/admin/file-editor"><i class="fa fa-pencil-square-o"></i>写文章 </a>
    </li>
    <li class="nav-item">
      <a class="nav-link white-link" href="${path}/admin/article/list"><i class="fa fa-file-text"></i>文章列表 </a>
    </li>
    <li class="nav-item pull-right">
      <div class="btn-group btn-group-sm">
        <button class="nav-link btn btn-primary" href="javascript:void(0)">
          <i class="fa" ng-class="{'fa-angle-double-up':!fixTop,'fa-arrows-v':fixTop}" ng-click="fixTop=!fixTop">{{!fixTop?"固定顶部":"取消固定"}}</i>
        </button>
        <button class="nav-link btn btn-primary" href="javascript:void(0)"><i class="fa fa-sign-out"></i>退出</button>
      </div>

    </li>
  </ul>
</nav>
<div ng-class="{'m-b-xl':fixTop}"></div>