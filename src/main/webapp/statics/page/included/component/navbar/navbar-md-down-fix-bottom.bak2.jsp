<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="row p-a-md navbar-md-down-fix-bottom ">
    <div class="col-md-9 col-md-push-1">
        <div class="card-group"  ng-init="get${param.varU}()">
            <div class="card no-border hidden-md-down p-r-0 m-r-0">
                <div class="card-block p-a-0 m-a-0">
                    <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
                         ng-src="{{${param.var}.navbarBrand.value}}" class="img-responsive">
                    <a ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='text'"class="m-l-5em" ng-bind="${param.var}.navbarBrand.value"></a>
                </div>

            </div>
            <div class="card no-border" ng-repeat="navItem in ${param.var}.items">
                <div class="card-block p-t-sm text-center">
                    <a ng-href="{{navItem.link}}" >
                        <i class="fa {{navItem.faClass}} fa-2x hidden-md-up"></i>
                        <span ng-bind="navItem.name" class="md-down-text-small-80"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>




