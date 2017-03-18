<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-md-down-fix-bottom p-t-1-5em p-b-1em" role="navigation"
     ng-init="get${pageComponent.varU}()">
    <div class="navbar-brand col-md-2 hidden-md-down m-r-6-5em m-l-2em">
        <img ng-if="${pageComponent.var}.navbarBrand&&${pageComponent.var}.navbarBrand.type&&${pageComponent.var}.navbarBrand.type==='image'"
             ng-src="{{${pageComponent.var}.navbarBrand.value}}" class="img-responsive img-ico-larger m-l-5em">
        <a ng-if="${pageComponent.var}.navbarBrand&&${pageComponent.var}.navbarBrand.type&&${pageComponent.var}.navbarBrand.type==='text'"class="m-l-5em">{{${pageComponent.var}.navbarBrand.value}}</a>
    </div>
    <ul class="nav navbar-nav p-t-1em m-a-0 p-l-0 p-l-r-4em">
        <li class="nav-item md-p-l-r-1-5em lg-p-l-r-2em p-t-10 text-center" ng-repeat="navItem in ${pageComponent.var}.items">
            <a class="nav-link" ng-href="{{navItem.link}}">
                <i class="fa {{navItem.faClass}} fa-2x hidden-md-up"></i>
                {{navItem.name}}
            </a>
        </li>
        <li class="nav-item"></li>
    </ul>
</nav>
