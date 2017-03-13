<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row m-l-0 m-r-0 margin-top-1em md-up-padding-left-right-4em padding-top-4em padding-bottom-2em  bg-very-light"
     ng-init="${param.fn}('${param.uri}')">
    <div class="col-xs-12 col-md-4" ng-repeat="articleSection in articleSections" ng-if="articleSections">
        <div class="row">
            <h4 class="col-xs-8 large-180">{{articleSection.name}}</h4>
            <img src="{{articleSection.image}}" class="col-xs-12 padding-top-1em" ng-if="articleSection.image"/>
            <a href="#" class="col-xs-4 green-link" ng-if="articleSection.articles">
                <span class="large-110">More</span>
                            <span class="fa-stack">
                                <i class="fa fa-circle fa-stack-1x"></i><i
                                    class="fa fa-angle-right fa-stack-1x fa-inverse padding-left-2"></i>
                            </span>
            </a>

        </div>
        <div class="list-group small-90 p-r-3em" ng-if="articleSection.articles">
            <a href="/article/{{article.id}}"
               class="list-group-item p-l-0 p-r-0 no-border no-background fa fa-ext-dot-blue"
               ng-repeat="article in articleSection.articles">

                <span class="color-grey bg-medium-grey-2 label-pill pull-right"
                      style="border-radius: .2rem;background-color: #efefefef">{{article.date |date:'shortDate'}}</span>{{article.title}}
            </a>
        </div>
        <div class="list-group small-90 p-r-3em" ng-if="!articleSection.articles &&!articleSection.image">
            <span class="list-group-item p-l-0 p-r-0 text-center padding-top-2em text-primary dash-silver-border"
                  ng-if="!articleSection.articles">暂无文章</span>
        </div>
    </div>
</div>