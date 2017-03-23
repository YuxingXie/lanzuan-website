<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row p-a-md">
    <div class="col-xs-10 col-xs-push-1">
        <div class="card-group navbar-md-down-fix-bottom"  ng-init="get${pageComponent.varU}()">
            <div class="card no-border hidden-md-down p-r-0 m-r-0">
                <div class="card-block p-a-0 m-a-0">
                    <img ng-if="${pageComponent.var}.navbarBrand&&${pageComponent.var}.navbarBrand.type&&${pageComponent.var}.navbarBrand.type==='image' &&${pageComponent.var}.navbarBrand.type&&${pageComponent.var}.navbarBrand.value"
                         ng-src="{{${pageComponent.var}.navbarBrand.value}}" class="img-responsive">
                    <a ng-if="${pageComponent.var}.navbarBrand&&${pageComponent.var}.navbarBrand.type&&${pageComponent.var}.navbarBrand.type==='text'"class="m-l-5em">{{${pageComponent.var}.navbarBrand.value}}</a>
                </div>

            </div>
            <div class="card  no-border" ng-repeat="navItem in ${pageComponent.var}.items">
                <div class="card-block p-t-sm text-right">
                            <a ng-href="{{navItem.link}}" >
                                <i class="fa {{navItem.faClass}} fa-2x hidden-md-up"></i>
                                {{navItem.name}}
                            </a>
                </div>
            </div>
        </div>
    </div>
</div>




