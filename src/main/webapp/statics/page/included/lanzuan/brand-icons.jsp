<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="row m-l-0 m-r-0 m-t-1em md-up-p-x-4em p-t-4em p-b-2em"  ng-init="get${param.varU}()">
    <div class="col-xs-12">
        <div class="large-120 inline xl-large-180 lg-large-165 md-large-150 sm-large-135">
            <span>合作伙伴</span>
        </div>

    </div>
    <div class="col-xs-12 m-t-sm">
        <div class="col-xs-4 col-md-2 p-t-sm text-center" ng-repeat="brandIcon in ${param.var}.items">
            <img ng-src="{{brandIcon.iconUri}}" class="brand-ico" />
            <p ng-bind="brandIcon.brandName"></p>
        </div>

    </div>

</div>