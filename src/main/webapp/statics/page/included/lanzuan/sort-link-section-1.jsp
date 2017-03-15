<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row padding-top-4em padding-bottom-4em margin-left-right-0 md-up-padding-left-right-4em bg-very-light " ng-init="get${param.varU}()">
    <div class="col-xs-6 col-md-2 text-center dash-silver-right-border height-10em padding-left-2em" ng-repeat="sortLink in ${param.var}.sortLinkGroupItems">
        <h6 class="color-blue text-left">{{sortLink.sortName}}</h6>
        <div class="small-90 margin-top-1em">
            <ul class="list-unstyled grey-link">
                <li ng-repeat="link in sortLink.links" class="text-left"><a ng-href="{{link.href}}">{{link.text}}</a></li>
            </ul>
        </div>
    </div>
    <div class="col-xs-6 col-md-4 text-center height-10em  padding-left-2em">
        <h6 class="color-blue text-center">关注我们</h6>
        <div class="row margin-top-1em">
            <div class="col-md-5 hidden-md-down"></div>
            <a href="#" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                <!--<img src="ewm.jpg" class="hidden inline-block" />-->
                <img title="微信" src="/statics/image/lanzuan/icons/foll1.gif" class="full-width"/>
            </a>
            <a href="http://weibo.com/sangfor" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                <img title="微博" src="/statics/image/lanzuan/icons/foll2.gif"  class="full-width"/>
            </a>
            <a href="" target="_blank"  class="col-xs-4 col-md-1 padding-1 m-a-0">
                <img title="社区" src="/statics/image/lanzuan/icons/foll3.gif" class="full-width"/>
            </a>
        </div>
    </div>
</div>