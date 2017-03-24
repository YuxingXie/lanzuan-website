<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="row m-l-0 m-r-0 m-t-1em md-up-p-l-r-4em p-t-4em p-b-2em  bg-very-light" >
                <div class="row m-l-0 m-r-0 m-t-1em md-up-p-l-r-4em p-t-4em p-b-2em  bg-very-light"
                 ng-init="get${param.varU}()">

                <div class="col-xs-12 col-md-4" ng-repeat="sortLink in ${param.var}.items">
                    <div class="row">
                        <h4 class="col-xs-8 large-180">{{sortLink.sortName}}</h4>
                        <a ng-if="sortLink.image" target="_blank" href="{{sortLink.imageHref}}"><img ng-src="{{sortLink.image}}" class="col-xs-12 p-t-1em" /></a>
                        <a href="#" class="col-xs-4 green-link" ng-if="sortLink.links">
                            <span class="large-110">More</span>
                            <span class="fa-stack">
                                <i class="fa fa-circle fa-stack-1x"></i><i
                                    class="fa fa-angle-right fa-stack-1x fa-inverse padding-left-2"></i>
                            </span>
                        </a>

                    </div>
                    <div class="list-group small-90 p-r-3em" ng-if="sortLink.links">
                        <a href="{{link.href}}"
                           class="list-group-item p-l-0 p-r-0 no-border no-background fa fa-ext-dot-blue"
                           ng-repeat="link in sortLink.links">

                <span class="label label-default label-pill pull-right">
                    {{link.date|date:'shortDate'}}</span>{{link.text}}
                        </a>
                    </div>
                    <div class="list-group small-90 p-r-3em" ng-if="!sortLink.links &&!sortLink.image">
            <span class="list-group-item p-l-0 p-r-0 text-center p-t-2em text-primary dash-silver-border"
                  ng-if="!sortLink.links">暂无文章</span>
                    </div>
                </div>
            </div>
</div>