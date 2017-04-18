<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="row m-l-0 m-r-0 m-t-1em md-up-p-x-4em p-t-4em p-b-2em  bg-very-light"
     ng-init="get${param.varU}()">
    <div class="col-xs-12 col-md-4" ng-repeat="sortLink in ${param.var}.items">
        <div class="row">
            <h4 class="large-120 col-xs-8 xl-large-180 lg-large-165 md-large-150 sm-large-135">
                <span ng-bind="sortLink.sortName"></span>
            </h4>
            <a ng-if="sortLink.image" target="_blank" href="{{sortLink.imageHref}}"><img ng-src="{{sortLink.image}}"
                                                                                         class="col-xs-12 p-t-1em"/></a>
            <a href="/website/article/list" class="col-xs-4 green-link" ng-if="sortLink.links">
                <span class="large-110">More</span>
                <span class="fa-stack">
                    <i class="fa fa-circle fa-stack-1x"></i><i
                        class="fa fa-angle-right fa-stack-1x fa-inverse padding-left-2"></i>
                </span>
            </a>

        </div>
        <ul class="text-primary m-l-10 p-l-0 small-90">
            <li ng-repeat="link in sortLink.links" class="m-t-sm md-down-text-small-80">
                <a ng-href="{{link.href}}">
                    <span class="label bg-dark-grey color-grey-3  label-pill pull-right m-l-2em" ng-bind="link.date|date:'yy-MM-dd'"></span>
                    <span ng-bind="link.text"></span>
                </a>

            </li>
        </ul>


        <div class="list-group small-90 p-r-3em" ng-if="!sortLink.links &&!sortLink.image">
    <span class="list-group-item p-l-0 p-r-0 text-center p-t-2em text-primary border-a-d-silver"
          ng-if="!sortLink.links">暂无文章</span>
        </div>
    </div>
</div>
