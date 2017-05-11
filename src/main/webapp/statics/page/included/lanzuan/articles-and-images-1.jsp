<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="row m-l-0 m-r-0 m-t-1em md-up-p-x-4em p-t-4em p-b-2em  bg-very-light"
     ng-init="get${param.varU}()">

    <div class="col-xs-12 col-md-6">
        <div class="col-xs-12">
            <div class="large-120 inline xl-large-180 lg-large-165 md-large-150 sm-large-135">
                <span ng-bind="${param.var}.articles.sortName"></span>
            </div>
            <a href="/article/list" class="green-link md-down-pull-right p-r-sm" ng-if="${param.var}.articles.links">
                <span class="large-110">More</span>
            <span class="fa-stack">
                <i class="fa fa-circle fa-stack-1x"></i>
                <i class="fa fa-angle-right fa-stack-1x fa-inverse p-l-2"></i>
            </span>
            </a>
        </div>
        <div class="row">
            <a ng-if="${param.var}.articles.image" target="_blank" href="{{${param.var}.articles.imageHref}}">
                <img ng-src="{{${param.var}.articles.image}}" class="col-xs-12 p-t-1em"/></a>
        </div>
        <ul class="text-primary m-l-10 p-l-0 small-90">
            <li ng-repeat="link in ${param.var}.articles.links" class="m-t-sm md-down-text-small-80">
                <a ng-href="{{link.href}}">
                    <img ng-src="{{link.image}}" class="full-width" ng-if="link.image&&$first"/>
                    <span class="label bg-dark-grey color-grey-3  label-pill pull-right m-l-2em" ng-bind="link.date|date:'yy-MM-dd'"></span>
                    <span ng-bind="link.text"></span>
                </a>

            </li>
        </ul>
        <div class="list-group small-90 p-r-3em" ng-if="!${param.var}.articles.links &&!${param.var}.articles.image">
        <span class="list-group-item p-l-0 p-r-0 text-center p-t-2em text-primary border-a-d-silver" ng-if="!${param.var}.articles.links">暂无文章</span>
        </div>
    </div>
    <div class="col-xs-12 col-md-6" >
        <div class="col-xs-12">
            <div class="large-120 inline xl-large-180 lg-large-165 md-large-150 sm-large-135 p-l-0 p-y-md">
                <span >典型案例</span>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6 md-down-p-x-0  m-t-sm" ng-repeat="imageTextItem in ${param.var}.images.imageTextItems">
                <a ng-href="{{imageTextItem.link}}" class="with-text-img" ng-mouseover="show=true" ng-mouseleave="show=false">
                    <img class="md-down-img-thumbnail full-container img-responsive" ng-class="{'dark-07':show}"
                         ng-src="{{imageTextItem.image}}"/>
                    <div class="img-caption small-90 md-down-text-small-70" ng-class="{'block':show,'hidden':!show}">
                        <div ng-bind="imageTextItem.text" ></div>
                        <button class="btn btn-primary m-t-0 btn-padding-little">了解详情</button>
                    </div>
                </a>
                <p class="img-title" ng-bind="imageTextItem.title"></p>
            </div>
        </div>

    </div>
</div>
