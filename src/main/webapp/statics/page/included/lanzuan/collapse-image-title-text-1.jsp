<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <div class="row  m-x-0 m-t-2-4em m-b-1-6em md-up-p-x-4em" ng-init="get${param.varU}();_active=0">
        <div class="col-xs-12 p-a-0 m-a-0">
                    <span class="col-xs-12 col-md-4 col-lg-3 col-xl-2 p-r-0">
                        <h4 ng-cloak class="md-down-p-l-0 large-120 xl-large-180 lg-large-165 md-large-150 sm-large-135" ng-bind="${param.var}.text"></h4>
                    </span>
                    <span class="col-xs-12 col-md-8 col-lg-9 col-xl-10 text-left m-a-0">
                        <span ng-repeat="block in ${param.var}.items">
                            <span  ng-mouseover="$parent._active=$index" class="btn btn-padding-little bg-none sm-down-btn" ng-class="{'active bg-light-blue color-white':_active===$index}">
                            <span class="sm-down-text-85-percent" ng-bind="block.name"></span>
                            </span>
                            <span ng-if="$index!==${param.var}.items.length-1" class="divider hidden-sm-down"></span>
                        </span>
                </span>
        </div>

    </div>
    <div class="row m-x-0 md-up-p-x-4em lg-m-b-2em" ng-repeat="block in ${param.var}.items"
         ng-class="{'active in animated fadeIn':$parent._active===$index,'collapse':$parent._active!==$index}">
        <div class="col-xs-6 col-md-3 md-down-p-x-0" ng-repeat="imageTextItem in block.imageTextItems">
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



