<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <div class="row p-t-4em p-b-4em m-l-0 m-r-0 bg-very-light " ng-init="get${param.varU}();toggle=-1;startToggle=false;">
        <div class="col-md-2 text-center md-down-text-left md-dash-silver-right-border md-height-10em padding-left-2em" ng-repeat="sortLink in ${param.var}.items">
            <div class="md-down-hover-cursor-hand"  ng-click="$parent.toggle=$index;$parent.startToggle=true;">
                <i class="pull-right hidden-md-up p-r">+</i>
                <h6 class="color-blue text-left p-l-2em md-down-bg-primary p-t-05em p-b-05em rounded-2"
                    ng-bind="sortLink.sortName"></h6>
            </div>

            <ul class="list-unstyled grey-link small-90 md-down-text-small-80 md-down-m-y-0 hidden-md-down" ng-class="{'block':$parent.startToggle&&$parent.toggle===$index}" >
                <li ng-repeat="link in sortLink.links" class="text-left p-l-2em md-down-p-y-5" ng-if="sortLink.links&&!sortLink.image">
                    <a ng-href="{{link.href}}" ng-bind="link.text"></a>
                </li>
                <img ng-src="{{sortLink.image}}" ng-if="sortLink.image" class="img-responsive"/>
            </ul>

        </div>
        <div class="col-xs-6 col-md-4 text-center height-10em  padding-left-2em">
            <h6 class="color-blue text-center">关注我们</h6>
            <div class="row m-t-1em">
                <div class="col-md-5 hidden-md-down"></div>
                <a href="#" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <!--<img ng-src="ewm.jpg" class="hidden inline-block" />-->
                    <img title="微信" ng-src="/statics/image/lanzuan/icons/foll1.gif" class="full-width"/>
                </a>
                <a href="http://weibo.com/sangfor" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <img title="微博" ng-src="/statics/image/lanzuan/icons/foll2.gif"  class="full-width"/>
                </a>
                <a href="" target="_blank"  class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <img title="社区" ng-src="/statics/image/lanzuan/icons/foll3.gif" class="full-width"/>
                </a>
            </div>
        </div>
    </div>
