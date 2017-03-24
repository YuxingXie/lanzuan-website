<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <nav class="navbar navbar-md-down-fix-bottom p-t-1-5em p-b-1em" role="navigation"
         ng-init="get${param.varU}()">
        <div class="navbar-brand col-md-2 hidden-md-down m-r-6-5em m-l-2em">
            <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
                 ng-src="{{${param.var}.navbarBrand.value}}" class="img-nav-brand m-l-5em">
            <a ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='text'"class="m-l-5em">{{${param.var}.navbarBrand.value}}</a>
        </div>
        <ul class="nav navbar-nav p-t-1em m-a-0 p-l-0 p-l-r-4em">
            <li class="nav-item md-p-l-r-1-5em lg-p-l-r-2em p-t-10 text-center" ng-repeat="navItem in ${param.var}.items">
                <a class="nav-link" ng-href="{{navItem.link}}">
                    <i class="fa {{navItem.faClass}} fa-2x hidden-md-up"></i>
                    {{navItem.name}}
                </a>
            </li>
        </ul>
    </nav>


