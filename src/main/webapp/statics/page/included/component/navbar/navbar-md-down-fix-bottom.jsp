<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<nav class="navbar" ng-init="get${param.varU}();toggleNav=false;showNav=false;">
    <div class="hidden-md-up" ng-click="toggleNav=true;showNav=!showNav;">
        <button class="btn btn-sm btn-primary-outline" type="button">
            &#9776;
        </button>
        <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
             ng-src="{{${param.var}.navbarBrand.value}}" class="img-ico-md m-l-5em">
    </div>
    <div class="hidden-md-down" ng-class="{'block animated fadeIn':toggleNav&&showNav}">
        <div class="navbar-brand col-md-2 hidden-md-down m-r-6-5em m-l-2em">
            <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
                 ng-src="{{${param.var}.navbarBrand.value}}" class="img-nav-brand m-l-5em">
            <a ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='text'"class="m-l-5em" ng-bind="${param.var}.navbarBrand.value"></a>
        </div>
        <ul class="nav navbar-nav p-t-1em m-a-0 p-l-0 p-x-4em">
            <li class="nav-item"></li>
            <li class="nav-item md-p-x-1-5em lg-p-x-2em p-t-10 text-center md-down-block-item md-down-m-l-0 " ng-repeat="navItem in ${param.var}.items">
                <a class="nav-link" ng-href="{{navItem.link}}">
                    <hr class="hidden-md-up p-t-0 p-b-0 m-t-0" />
                    <span ng-bind="navItem.name"></span>
                </a>
            </li>
        </ul>
    </div>
</nav>


