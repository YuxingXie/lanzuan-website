<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row m-l-0 m-r-0 margin-top-1em md-up-padding-left-right-4em padding-top-4em padding-bottom-2em  bg-very-light"
     ng-init="get${param.varU}()">

    <div class="col-xs-12 col-md-4" ng-repeat="sortLink in ${param.var}.${param.var}Items">
        <div class="row">
            <h4 class="col-xs-8 large-180">{{sortLink.sortName}}</h4>
            <a ng-if="sortLink.image" target="_blank" href="{{sortLink.imageHref}}"><img src="{{sortLink.image}}" class="col-xs-12 padding-top-1em" /></a>
            <a href="#" class="col-xs-4 green-link" ng-if="sortLink.links">
                <span class="large-110">More</span>
                            <span class="fa-stack">
                                <i class="fa fa-circle fa-stack-1x"></i><i
                                    class="fa fa-angle-right fa-stack-1x fa-inverse padding-left-2"></i>
                            </span>
            </a>

        </div>
        <div class="list-group small-90 p-r-3em" ng-if="sortLink.links">
            <a href="{{article.href}}"
               class="list-group-item p-l-0 p-r-0 no-border no-background fa fa-ext-dot-blue"
               ng-repeat="article in sortLink.links">

                <span class="color-grey bg-medium-grey-2 label-pill pull-right"
                      style="border-radius: .2rem;background-color: #efefefef">{{article.date |date:'shortDate'}}</span>{{article.text}}
            </a>
        </div>
        <div class="list-group small-90 p-r-3em" ng-if="!sortLink.links &&!sortLink.image">
            <span class="list-group-item p-l-0 p-r-0 text-center padding-top-2em text-primary dash-silver-border"
                  ng-if="!sortLink.links">暂无文章</span>
        </div>
    </div>
</div>