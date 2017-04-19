<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<nav class="navbar md-down-fixed-top bg-white" ng-init="get${param.varU}();toggleNav=false;showNav=false;">
    <div class="hidden-md-up rotateIn" ng-click="toggleNav=true;showNav=!showNav;">
        <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
             ng-src="{{${param.var}.navbarBrand.value}}" class="img-ico-md text-center">
        <span class="fa fa-navicon p-x-2 hover-hand pull-right p-t-5" ng-class="{'animated rotateIn':toggleNav&&showNav}"></span>
    </div>
    <div class="hidden-md-down" ng-class="{'block animated bounceInDown':toggleNav&&showNav}">
        <div class="navbar-brand hidden-md-down">
            <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
                 ng-src="{{${param.var}.navbarBrand.value}}" class="img-nav-brand p-x-2em">
            <a ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='text'"class="m-l-5em" ng-bind="${param.var}.navbarBrand.value"></a>
        </div>
        <div class="nav navbar-nav p-t m-a-0 p-l-0 p-r-md">
            <div class="nav-item" ng-init="navMenu={toggleIndex:-1,prevOpen:false,prevIndex:0}"></div>
            <div class="nav-item dropdown md-p-x-1-5em lg-p-x-2em md-p-t-10 text-center md-down-block-item md-down-m-l-0 md-down-text-small-90" ng-repeat="navItem in ${param.var}.items" >
                <a class="nav-link" ng-href="{{navItem.link}}" ng-if="!navItem.navItemType||navItem.navItemType==='link'">
                    <hr class="hidden-md-up p-t-0 p-b-0 m-t-0" />
                    <span ng-bind="navItem.name"></span>
                </a>
                <a class="nav-link" href="#" ng-if="navItem.navItemType==='menu'"
                      ng-click="navMenu.prevIndex=navMenu.toggleIndex;navMenu.toggleIndex=$index;navMenu.prevOpen=navMenu.prevIndex===navMenu.toggleIndex?!navMenu.prevOpen:false">
                    <hr class="hidden-md-up p-t-0 p-b-0 m-t-0" />
                    <span class="fa pull-right hidden-md-up" ng-class="{'fa-minus ':navMenu.toggleIndex===$index&&(navMenu.toggleIndex!=navMenu.prevIndex||(!navMenu.prevOpen)),'fa-plus ':!(navMenu.toggleIndex===$index&&(navMenu.toggleIndex!=navMenu.prevIndex||(!navMenu.prevOpen)))}"></span>
                    <span ng-bind="navItem.name"></span>
                    <ul class="nav-item-dropdown md-down-text-small-80" ng-class="{'block':navMenu.toggleIndex===$parent.$index&&(navMenu.toggleIndex!=navMenu.prevIndex||(!navMenu.prevOpen))}">
                        <li ng-repeat="menuItem in navItem.menuItems" class="md-down-p-y-5">
                            <span ng-bind="menuItem.text" class="animated fadeIn"></span>
                        </li>
                    </ul>
                </a>
            </div>
        </div>
    </div>

</nav>
<div class="md-down-m-y-46"></div>


