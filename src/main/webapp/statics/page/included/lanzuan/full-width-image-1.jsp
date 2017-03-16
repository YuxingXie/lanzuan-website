<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row m-l-r-0 " ng-init="get${pageComponent.variableFirstUpper}()">
    <img class="full-width img-responsive" ng-src="{{${pageComponent.jsonVariableName}.image.uri}}" style="max-height: 327px;"
         ng-if="fullWidthImage &&fullWidthImage.image &&fullWidthImage.image.uri"/>
</div>