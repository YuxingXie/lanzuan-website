<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card-group margin-left-right-0" ng-init="getCardGroup()">
    <div class="card" ng-repeat="card in cardGroup.items">
        <div class="card-block padding-1">
            <a ng-if="card.link" ng-href="{{card.link}}">
                <img ng-src="{{card.image}}" class="full-width "/>
                <div class="sticker sticker-bottom padding-bottom-05em large-110">{{card.text}}</div>
            </a>
            <div ng-if="!card.link">
                <img ng-src="{{card.image}}" class="full-width "/>
                <div class="sticker sticker-bottom padding-bottom-05em large-110">{{card.text}}</div>
            </div>
        </div>
    </div>

</div>