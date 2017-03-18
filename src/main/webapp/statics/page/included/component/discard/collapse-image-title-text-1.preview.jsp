<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row  m-l-r-0 m-t-2-4em m-b-1-6em md-up-p-l-r-4em " ng-init="get${pageComponent.varU}();_active=0">
    <div class="col-xs-12 col-md-9 p-a-0 m-a-0">
                    <span class="col-xs-12 col-md-4 col-lg-3 col-xl-2 p-r-0">
                        <h4 class="md-down-no-padding-left large-180" >{{${pageComponent.var}.text}}</h4>
                    </span>
                    <span class="col-xs-12 col-md-8 col-lg-9 col-xl-10 text-left m-a-0">

                        <span ng-repeat="block in ${pageComponent.var}.items">
                            <span  ng-mouseover="$parent._active=$index" class="btn btn-padding-little bg-none sm-down-btn" ng-class="{'active bg-light-blue color-white':_active===$index}">
                            {{block.name}}
                            </span>
                            <span ng-if="$index!==${pageComponent.var}.items.length-1" class="divider hidden-sm-down"></span>
                        </span>
                </span>
    </div>

</div>
<div class="row m-l-r-0 md-up-p-l-r-4em m-b-2em" ng-repeat="block in ${pageComponent.var}.items"
     ng-class="{'active in':$parent._active===$index,'collapse':$parent._active!==$index}">
    <div class="col-xs-6 col-md-3 md-down-p-l-r-0" ng-repeat="imageTextItem in block.imageTextItems">
        <a ng-href="{{imageTextItem.link}}" class="with-text-img" ng-mouseover="show=true" ng-mouseleave="show=false">
            <img class="md-down-img-thumbnail full-container img-responsive" ng-class="{'dark-07':show}"
                 ng-src="{{imageTextItem.image}}"/>
            <div class="absolute-center small-90 hidden-md-down" ng-class="{'block':show,'hidden':!show}">
                <div class="text-indent-1em">{{imageTextItem.text}}</div>
                <button class="btn btn-primary m-t-05em btn-padding-little" ng-href="{{imageTextItem.link}}">了解详情</button>
            </div>
        </a>

        <p class="img-title">{{imageTextItem.title}}</p>
    </div>

</div>