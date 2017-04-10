<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<nav class="navbar md-down-fixed-top bg-white" ng-init="get${param.varU}();toggleNav=false;showNav=false;">
    <div class="hidden-md-up rotateIn" ng-click="toggleNav=true;showNav=!showNav;">
        <span class="rounded-2 border-a-s-silver p-x-2 hover-cursor-hand" ng-class="{'animated rotateIn':toggleNav&&showNav}">&#9776;</span>
        <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
             ng-src="{{${param.var}.navbarBrand.value}}" class="img-ico-md m-l-5em">
    </div>
    <div class="hidden-md-down " ng-class="{'block animated bounceInDown':toggleNav&&showNav}">
        <div class="navbar-brand col-md-2 hidden-md-down m-r-6-5em m-l-2em">
            <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
                 ng-src="{{${param.var}.navbarBrand.value}}" class="img-nav-brand m-l-5em">
            <a ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='text'"class="m-l-5em" ng-bind="${param.var}.navbarBrand.value"></a>
        </div>
        <ul class="nav navbar-nav p-t-1em m-a-0 p-l-0 p-r-md">
            <li class="nav-item"></li>
            <li class="nav-item md-p-x-1-5em lg-p-x-2em p-t-10 text-center md-down-block-item md-down-m-l-0 md-down-text-small-80" ng-repeat="navItem in ${param.var}.items">
                <a class="nav-link" ng-href="{{navItem.link}}">
                    <hr class="hidden-md-up p-t-0 p-b-0 m-t-0" />
                    <span ng-bind="navItem.name"></span>
                </a>
            </li>
        </ul>
    </div>
</nav>
<div class="md-down-m-y-46"></div>


