<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row margin-left-right-0 " ng-init="${param.fn}('${param.uri}')">
    <img class="full-width img-responsive" ng-src="{{fullWidthImage.image.templateUri}}" style="max-height: 327px;"
         ng-if="fullWidthImage &&fullWidthImage.image &&fullWidthImage.image.templateUri"/>
</div>