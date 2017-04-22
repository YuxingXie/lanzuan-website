<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<nav class="navbar md-down-fixed-top bg-white md-down-p-x-0" ng-init="get${param.varU}();toggleNav=false;showNav=false;">
    <div class="hidden-md-up rotateIn" ng-click="toggleNav=true;showNav=!showNav;">
        <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
             ng-src="{{${param.var}.navbarBrand.value}}" class="img-ico-md text-center md-down-nav-brand">
        <span class="fa fa-navicon p-r-sm hover-hand pull-right p-t-5" ng-class="{'animated rotateIn':toggleNav&&showNav}"></span>
    </div>
    <div class="hidden-md-down" ng-class="{'block animated bounceInDown':toggleNav&&showNav}">
        <div class="navbar-brand hidden-md-down">
            <img ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='image' &&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.value"
                 ng-src="{{${param.var}.navbarBrand.value}}" class="img-nav-brand p-x-2em">
            <a ng-if="${param.var}.navbarBrand&&${param.var}.navbarBrand.type&&${param.var}.navbarBrand.type==='text'"class="m-l-5em" ng-bind="${param.var}.navbarBrand.value"></a>
        </div>
        <div class="nav navbar-nav p-t m-a-0 p-l-0 md-p-r-md " ng-init="navMenu={toggleIndex:-1,prevOpen:false,prevIndex:0}">
            <div class="nav-item" ></div>
            <div class="nav-item md-down-bg-inverse md-dropdown p-r-xl m-l-0 md-down-p-r-0 md-p-t-10 text-left md-down-block-item md-down-text-small-90"
                 ng-repeat="navItem in ${param.var}.items">
                <a class="nav-link md-down-white-link p-b-0 md-down-border-b-s-silver" ng-href="{{navItem.link}}" ng-if="!navItem.navItemType||navItem.navItemType==='link'">
                    <div ng-bind="navItem.name" class=" md-down-p-x-10 md-down-p-y-5"></div>
                </a>
                <div class="nav-link hover-hand p-b-0 md-down-border-b-s-silver" href="#" ng-if="navItem.navItemType==='menu'"
                      ng-mouseover="navMenu.prevIndex=navMenu.toggleIndex;navMenu.toggleIndex=$index;navMenu.prevOpen=false"
                      ng-mouseleave="navMenu={toggleIndex:-1,prevOpen:false,prevIndex:0}"
                        ng-click="navMenu.prevOpen=navMenu.prevIndex===navMenu.toggleIndex?!navMenu.prevOpen:false">
                    <span class="fa pull-right hidden-md-up p-r" ng-class="{'fa-minus ':navMenu.toggleIndex===$index&&(navMenu.toggleIndex!=navMenu.prevIndex||(!navMenu.prevOpen)),'fa-plus ':!(navMenu.toggleIndex===$index&&(navMenu.toggleIndex!=navMenu.prevIndex||(!navMenu.prevOpen)))}"></span>
                    <div class="p-b-0 m-b-0 p-x-10 md-down-p-y-5" ng-bind="navItem.name" ></div>
                    <div class="nav-item-dropdown p-t-0 m-t-0 md-p-x-10" ng-class="{'block':navMenu.toggleIndex===$parent.$index&&(navMenu.toggleIndex!=navMenu.prevIndex||(!navMenu.prevOpen))}">
                        <div ng-repeat="menuItem in navItem.menuItems" class=" md-down-p-l-10 bg-white md-down-border-b-s-silver">
                            <a ng-bind="menuItem.text" class="animated fadeIn md-down-text-small-90" ng-href="{{menuItem.href}}"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</nav>
<div class="md-down-m-y-46"></div>


