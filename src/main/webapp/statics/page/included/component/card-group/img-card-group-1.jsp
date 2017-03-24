<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="card-group m-l-r-0" ng-init="get${param.varU}()">
        <div class="card" ng-repeat="card in ${param.var}.items">
            <div class="card-block padding-1">
                <a ng-if="card.link" ng-href="{{card.link}}">
                    <img ng-src="{{card.image}}" class="full-width "/>
                    <div class="sticker sticker-bottom p-b-05em large-110">{{card.text}}</div>
                </a>
                <div ng-if="!card.link">
                    <img ng-src="{{card.image}}" class="full-width "/>
                    <div class="sticker sticker-bottom p-b-05em large-110">{{card.text}}</div>
                </div>
            </div>
        </div>

    </div>


