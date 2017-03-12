<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<nav class="navbar bg-inverse">
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
      <a class="nav-link white-link" href="${path}/admin/index"><i class="fa fa-home"></i>管理首页 </a>
    </li>
    <li class="nav-item pull-right">
      <button class="nav-link btn btn-primary btn-sm" href="javascript:void(0)"><i class="fa fa-sign-out"></i>退出</button>
    </li>
  </ul>
</nav>