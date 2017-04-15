<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <div class="row p-t-4em p-b-4em m-l-0 m-r-0 bg-very-light " ng-init="get${param.varU}();toggle=-1;startToggle=false;prevToggle=-1;prevDisplay=true;">
        <div class="col-md-2 text-center md-down-text-left md-dash-silver-right-border md-height-10em padding-left-2em" ng-repeat="sortLink in ${param.var}.items">
            <div class="md-down-hover-cursor-hand"  ng-click="$parent.prevToggle=$parent.toggle;$parent.toggle=$index;$parent.prevDisplay=$parent.prevToggle===$parent.toggle?(!$parent.prevDisplay):false;$parent.startToggle=true;">
                <span class="pull-right hidden-md-up p-r m-t-05em color-grey">+</span>
                <h6 class="color-blue text-left p-l-2em p-t-05em md-down-solid-silver-border-top"
                    ng-class="{'md-down-p-b-05em md-down-solid-silver-border-bottom':$last&&!($parent.startToggle&&$parent.toggle===$index&&$parent.prevToggle!=$parent.toggle||($parent.startToggle&&$parent.toggle===$index&&$parent.prevToggle===$parent.toggle&&!$parent.prevDisplay))}"
                    ng-bind="sortLink.sortName"></h6>
            </div>

            <ul class="list-unstyled grey-link small-90 md-down-text-small-80 md-down-m-y-0 md-down-p-x-10 hidden-md-down"
                ng-class="{'block animated slideInLeft':$parent.startToggle&&$parent.toggle===$index&&$parent.prevToggle!=$parent.toggle||($parent.startToggle&&$parent.toggle===$index&&$parent.prevToggle===$parent.toggle&&!$parent.prevDisplay)}" >
                <li ng-repeat="link in sortLink.links" class="text-left p-l-2em md-down-p-y-5 " ng-if="sortLink.links&&!sortLink.image">
                    <a ng-href="{{link.href}}" ng-bind="link.text"></a>
                </li>
                <img ng-src="{{sortLink.image}}" ng-if="sortLink.image" class="img-responsive"/>
            </ul>

        </div>
        <div class="col-xs-12 col-md-4 md-down-m-y-20 p-l-2em p-t-md "  ng-init="showPic=0">
            <h6 class="color-blue text-center">关注我们</h6>
            <div class="col-xs-12 text-center">
                <div class="dropdown-menu-top" ng-class="{'block':showPic==1||showPic==2||showPic==3}">
                    <img src="/statics/image/lanzuan/home/ewm.jpg" class="img-responsive img-ico-lg animated zoomIn" ng-if="showPic==1"/>
                    <img src="/statics/image/lanzuan/home/ewm.jpg" class="img-responsive img-ico-lg animated zoomIn" ng-if="showPic==2"/>
                    <img src="/statics/image/lanzuan/home/ewm.jpg" class="img-responsive img-ico-lg animated zoomIn" ng-if="showPic==3"/>
                </div>
                <img title="微信" ng-src="/statics/image/lanzuan/icons/foll1.gif" class="inline-block hover-cursor-hand img-responsive" ng-mouseover="showPic=1" ng-mouseleave="showPic=0"/>
                <img title="微博" ng-src="/statics/image/lanzuan/icons/foll2.gif" class="inline-block hover-cursor-hand img-responsive" ng-mouseover="showPic=2" ng-mouseleave="showPic=0"/>
                <img title="社区" ng-src="/statics/image/lanzuan/icons/foll3.gif" class="inline-block hover-cursor-hand img-responsive" ng-mouseover="showPic=3" ng-mouseleave="showPic=0"/>
            </div>



        </div>
    </div>