<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="2000" ng-init="get${param.varU}()">
        <ol class="carousel-indicators bottom-0">
            <li data-target="#carousel-example-generic" data-slide-to="{{$index}}" ng-class="{'active':$index===0}" ng-repeat="carouselItem in ${param.var}.items"></li>
        </ol>
        <div class="carousel-inner " role="listbox">
            <div class="carousel-item" ng-class="{'active':$index===0}" ng-repeat="carouselItem in ${param.var}.items">
                <a ng-if="carouselItem.imageLink" ng-href="{{carouselItem.imageLink}}" target="_blank"><img  class="center-block full-width" ng-src="{{carouselItem.imageLink}}"/></a>
                <img ng-if="!carouselItem.imageLink"  class="center-block full-width" ng-src="{{carouselItem.imageLink}}"/>
                <div ng-if="carouselItem.carouselCaption" class="carousel-caption">
                    <a ng-if="carouselItem.carouselCaption.type &&carouselItem.carouselCaption.type==='link'"
                       ng-href="{{carouselItem.carouselCaption.href}}"
                       class="btn btn-primary hidden-md-down white-link" ng-bind="carouselItem.carouselCaption.text">
                        <i class="fa fa-chevron-right right"></i>
                    </a>
                    <span ng-if="carouselItem.carouselCaption.type &&carouselItem.carouselCaption.type==='text'" class="p-a-xs bg-info rounded-3">
                       <i class="carouselItem.carouselCaption.text"></i>
                    </span>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="icon-prev" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="icon-next" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


