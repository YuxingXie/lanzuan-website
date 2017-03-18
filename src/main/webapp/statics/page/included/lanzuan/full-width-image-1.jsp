<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row m-l-r-0 ">
    <c:if test="${not empty pageComponent.data.image.uri}">
        <img class="full-width img-responsive" ng-src="${pageComponent.data.image.uri}" style="max-height: 327px;"/>
    </c:if>
</div>