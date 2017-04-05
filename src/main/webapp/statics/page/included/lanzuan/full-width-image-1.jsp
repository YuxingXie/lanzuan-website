<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <div class="row m-x-0 " ng-init="get${param.varU}()">
        <img class="full-width img-responsive" ng-src="{{${param.var}.image.uri}}" style="max-height: 327px;"
             ng-if="fullWidthImage &&fullWidthImage.image &&fullWidthImage.image.uri"/>
    </div>


