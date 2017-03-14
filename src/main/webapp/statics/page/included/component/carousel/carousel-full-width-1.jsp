<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="4000" ng-init="getCarousel()">
    <ol class="carousel-indicators bottom-0">
        <li data-target="#carousel-example-generic" data-slide-to="{{$index}}" ng-class="{'active':$index===0}" ng-repeat="carouselItem in carousel.carouselItems"></li>
    </ol>
    <div class="carousel-inner " role="listbox">
        <div class="carousel-item" ng-class="{'active':$index===0}" ng-repeat="carouselItem in carousel.carouselItems">
            <img ng-if="carouselItem.type==='image'" class="center-block full-width" ng-src="{{carouselItem.value}}"/>
            <div ng-if="carouselItem.carouselCaption" class="carousel-caption">
                <a ng-if="carouselItem.carouselCaption.type &&carouselItem.carouselCaption.type==='link'"
                   ng-href="{{carouselItem.carouselCaption.value}}"
                   class="btn btn-primary hidden-md-down white-link">{{carouselItem.carouselCaption.text}} <i class="fa fa-chevron-right right"></i></a>


                <span ng-if="carouselItem.carouselCaption.type &&carouselItem.carouselCaption.type==='text'">
                   {{carouselItem.carouselCaption.text}}
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