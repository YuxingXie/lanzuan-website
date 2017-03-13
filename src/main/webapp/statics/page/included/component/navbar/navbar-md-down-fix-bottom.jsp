<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-md-down-fix-bottom padding-top-1-5em padding-bottom-1em" role="navigation"
     ng-init="${param.fn}('${param.uri}')">
    <div class="navbar-brand col-md-2 hidden-md-down margin-right-6-5em margin-left-2em">
        <img ng-if="navbar.navbarBrand&&navbar.navbarBrand.type&&navbar.navbarBrand.type==='image'"
             ng-src="{{navbar.navbarBrand.value}}" class="img-responsive img-ico-larger margin-left-5em">
        <a ng-if="navbar.navbarBrand&&navbar.navbarBrand.type&&navbar.navbarBrand.type==='text'"class="margin-left-5em">{{navbar.navbarBrand.value}}</a>
    </div>
    <ul class="nav navbar-nav padding-top-1em m-a-0 p-l-0 padding-left-right-4em">
        <li class="nav-item col-xs-3 col-md-2 col-lg-1 m-a-0 p-l-0 p-r-0 padding-top-10 text-center" ng-repeat="navItem in navbar.navItems">
            <a class="nav-link" ng-href="{{navItem.link}}">
                <i class="fa {{navItem.faClass}} fa-2x hidden-md-up"></i>
                {{navItem.name}}
            </a>
        </li>
    </ul>
</nav>
