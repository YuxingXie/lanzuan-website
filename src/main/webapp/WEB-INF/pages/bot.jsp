<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
    <head lang="en">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>湖南蓝钻科技</title>
        <script src="${path}/statics/js/pace.js"></script>
        <link rel="stylesheet" href="${path}/statics/css/pace.css"/>
        <link rel="stylesheet" href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="${path}/statics/plugin/animate.css-master/animate.css"/>
        <link rel="stylesheet" href="${path}/statics/css/animate.css"/>
        <link rel="stylesheet" href="${path}/statics/css/style.css"/>
        <link rel="stylesheet" href="${path}/statics/css/color.css"/>
        <link rel="stylesheet" href="${path}/statics/css/bootstrap.custom.css"/>
        <link rel="stylesheet" href="${path}/statics/css/responsive.css3.css"/>
        <link rel="stylesheet" href="${path}/statics/css/lanzuan.css"/>

    </head>
    <body ng-app="homeApp" ng-cloak="">
    <div ng-controller="HomeController" >
        <div class="navbar navbar-sm-down-fix-bottom" role="navigation">
            <img src="/statics/image/lanzuan/icons/ico.jpg" class="img-ico-larger navbar-brand ">
            <ul class="nav navbar-nav p-t-1em">
                <li class="nav-item active  hidden-sm-down">
                    <a class="nav-link active " href="#">
                        <i class="fa fa-home hidden-sm-down"></i> 首页
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/statics/page/business/b1.html" >智慧城市</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link " href="/statics/page/business/b2.html">三农服务</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link " href="/statics/page/business/b3.html">软件开发</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link " href="/statics/page/about-us.html">关于我们</a>
                </li>
            </ul>
        </div>
        <div id="carousel-example-generic" class="carousel slide hidden-sm-down " data-ride="carousel" data-interval="4000">
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            </ol>
            <div class="carousel-inner " role="listbox">
                <div class="carousel-item active ">
                    <img class="center-block full-width" src="/statics/image/lanzuan/home/carousel1.jpg"/>
                    <div class="carousel-caption m-b-40 p-l-2em" style="left: 5%">
                        <button class="btn btn-primary">了解更多 <i class="fa fa-chevron-right right"></i></button>
                    </div>
                </div>
                <div class="carousel-item ">
                    <img class="center-block full-height" src="/statics/image/lanzuan/home/carousel2.jpg"/>
                    <div class="carousel-caption-top">
                    </div>
                </div>
                <div class="carousel-item ">
                    <img class="center-block full-height" src="/statics/image/lanzuan/home/carousel3.jpg"/>
                    <div class="carousel-caption-top">
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="/statics/image/lanzuan/home/carousel4.jpg" class="center-block full-width"/>
                    <div class="carousel-caption-top">
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
        <div class="card-group m-x-0">
            <div class="card">
                <div class="card-block padding-1  ">
                    <a href="/statics/page/business/b1.html">
                        <img src="/statics/image/lanzuan/bg/bg2.jpg" class="full-width "/>
                        <div class="sticker sticker-bottom m-b-40">
                            <img src="/statics/image/lanzuan/icons/ico2.png" class="img-ico-lg hidden-md-down"/>
                        </div>
                        <div class="sticker sticker-bottom p-b-05em large-110">智慧城市</div>
                    </a>

                </div>
            </div>
            <div class="card">
                <div class="card-block padding-1  ">
                    <a href="/statics/page/business/b2.html">
                        <img src="/statics/image/lanzuan/bg/bg2.jpg" class="full-width "/>
                        <div class="sticker sticker-bottom m-b-40">
                            <img src="/statics/image/lanzuan/icons/ico2.png" class="img-ico-lg hidden-md-down"/>
                        </div>
                        <div class="sticker sticker-bottom p-b-05em large-110">三农服务</div>
                    </a>
                </div>
            </div>
            <div class="card">
                <div class="card-block padding-1">
                    <a href="/statics/page/business/b3.html" alt="软件开发">
                        <img src="/statics/image/lanzuan/bg/bg2.jpg" class="full-width "/>
                        <div class="sticker sticker-bottom m-b-40">
                            <img src="/statics/image/lanzuan/icons/ico2.png" class="img-ico-lg hidden-md-down"/>
                        </div>
                        <div class="sticker sticker-bottom p-b-05em large-110">软件开发</div>
                    </a>
                </div>
            </div>

        </div>

        <div class="row  m-x-0 m-t-1em p-l-2em" ng-init="active=1">
            <span class="col-xs-4 col-md-2 large-180 p-b-1em sm-down-text-120-percent ">典型应用</span>
            <div class=" sm-down-btn-group col-xs-8 col-md-10 p-t-05em">
                <a ng-mouseover="active=1" ng-class="{'large-110 label label-pill  label-primary sm-down-btn':true,'active label-danger':active===1}">
                    智慧城市
                </a>
                <span class="divider hidden-sm-down"></span>
                <a ng-mouseover="active=2" ng-class="{'label label-pill large-110 label-primary sm-down-btn':true,'active label-danger':active===2}" >
                    生态农业
                </a>
                <span class="divider hidden-sm-down"></span>
                <a ng-mouseover="active=3" ng-class="{'label label-pill large-110 label-primary sm-down-btn':true,'active label-danger':active===3}">
                    软件开发
                </a>
            </div>
        </div>
        <div class="row m-x-0">
            <div ng-class="{'hidden':false,'active in':active===1,'collapse':active!==1}">
                <div class="row m-x-0">
                    <div class="col-xs-12 col-md-3 m-b-10 ">
                        <a ng-init='show1=false' href="/statics/page/business/b1.html" class="with-text-img" ng-mouseover="show1=true" ng-mouseleave="show1=false">
                            <img ng-class="{'full-container img-responsive':true,'dark-07':show1}"
                                 src="/statics/image/lanzuan/280-180/zfdl.jpg"/>

                            <div ng-class="{'absolute-center small-90':true,'block':show1,'hidden':!show1}">
                                <div class="text-indent-1em">宁乡县政府WIFI覆盖应用</div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a>

                        <p class="text-center">宁乡县政府</p>
                    </div>
                    <div class="col-xs-12 col-md-3 m-b-10 ">
                        <a ng-init='show2=false' href="/statics/page/business/b1.html" class="with-text-img" ng-mouseover="show2=true" ng-mouseleave="show2=false">
                            <img ng-class="{'full-container':true,'dark-07':show2}" src="/statics/image/lanzuan/280-180/cydl.jpg"/>
                            <div ng-class="{'absolute-center small-90':true,'block':show2,'hidden':!show2}">
                                <div class="text-indent-1em">金州开发区创业大楼WIFI覆盖应用</div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a>

                        <p class="text-center">金州开发区创业大楼</p>
                    </div>
                    <div class="col-xs-12 col-md-3 m-b-10 ">
                        <a ng-init='show3=false' href="/statics/page/business/b1.html" class="with-text-img" ng-mouseover="show3=true"
                           ng-mouseleave="show3=false">
                            <img ng-class="{'full-container':true,'dark-07':show3}"
                                 src="/statics/image/lanzuan/280-180/smzj.jpg"/>

                            <div ng-class="{'absolute-center small-90':true,'block':show3,'hidden':!show3}">
                                <div class="text-indent-1em">宁乡县市民之家WIFI应用</div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a>

                        <p class="text-center">宁乡县市民之家</p>
                    </div>
                    <div class="col-xs-12 col-md-3 m-b-10 ">
                        <a ng-init='show4=false' href="/statics/page/business/b1.html" class="with-text-img" ng-mouseover="show4=true" ng-mouseleave="show4=false">
                            <img ng-class="{'full-width':true,'dark-07':show4}"
                                 src="/statics/image/lanzuan/280-180/nxxgaj.jpg"/>
                            <div ng-class="{'absolute-center small-90':true,'block':show4,'hidden':!show4}">
                                <div class="text-indent-1em">宁乡县公安局WIFI覆盖应用</div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a>
                        <p class="text-center">宁乡县公安局</p>
                    </div>
                </div>
            </div>
            <div ng-class="{'hidden':false,'active in':active===2,'collapse':active!==2}">
                <div class="row m-x-0">
                    <div class="col-xs-12 col-md-3 m-b-10">
                        <a ng-init='show1=false' href="/statics/page/business/b2.html" class="with-text-img" ng-mouseover="show1=true" ng-mouseleave="show1=false">
                            <img ng-class="{'full-container':true,'dark-07':show1}"
                                 src="/statics/image/lanzuan/280-180/putao.jpg">
                            <div ng-class="{'absolute-center small-90':true,'block':show1,'hidden':!show1}">
                                <div class="text-indent-1em"></div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a><p class="text-center">有机肥基地</p>
                    </div>
                    <div class="col-xs-12 col-md-3 m-b-10">
                        <a ng-init='show2=false' href="/statics/page/business/b2.html" class="with-text-img" ng-mouseover="show2=true" ng-mouseleave="show2=false">
                            <img ng-class="{'full-container':true,'dark-07':show2}"
                                 src="/statics/image/lanzuan/280-180/putao2.jpg"/>
                            <div ng-class="{'absolute-center small-90':true,'block':show2,'hidden':!show2}">
                                <div class="text-indent-1em"></div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a><p class="text-center">有机肥农业成果</p>
                    </div>
                    <div class="col-xs-12 col-md-3 m-b-10">
                        <a ng-init='show3=false' href="/statics/page/business/b2.html" class="with-text-img" ng-mouseover="show3=true" ng-mouseleave="show3=false">
                            <img ng-class="{'full-container':true,'dark-07':show3}"
                                 src="/statics/image/lanzuan/280-180/putaoyuan5.jpg"/>
                            <div ng-class="{'absolute-center small-90':true,'block':show3,'hidden':!show3}">
                                <div class="text-indent-1em"></div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a><p class="text-center">有机肥农业成果</p>
                    </div>
                </div>
            </div>
            <div ng-class="{'hidden':false,'active in':active===3,'collapse':active!==3}">
                <div class="row m-x-0">
                    <div class="col-xs-12 col-md-3 m-b-10">
                        <a ng-init='show1=false' href="/statics/page/business/b3.html" class="with-text-img" ng-mouseover="show1=true" ng-mouseleave="show1=false">
                            <img ng-class="{'full-container':true,'dark-07':show1}"
                                 src="/statics/image/lanzuan/280-180/app1.jpg"/>
                            <div ng-class="{'absolute-center small-90':true,'block':show1,'hidden':!show1}">
                                <div class="text-indent-1em"></div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a><p class="text-center">宁乡县公安局公众号</p>
                    </div>
                    <div class="col-xs-12 col-md-3 m-b-10">
                        <a ng-init='show2=false' href="/statics/page/business/b3.html" class="with-text-img" ng-mouseover="show2=true" ng-mouseleave="show2=false">
                            <img ng-class="{'full-container':true,'dark-07':show2}"
                                 src="/statics/image/lanzuan/280-180/app2.jpg"/>
                            <div ng-class="{'absolute-center small-90':true,'block':show2,'hidden':!show2}">
                                <div class="text-indent-1em"></div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a><p class="text-center">宁乡县公安局交警大队公众号</p>
                    </div>
                    <div class="col-xs-12 col-md-3 m-b-10">
                        <a ng-init='show3=false' href="/statics/page/business/b3.html" class="with-text-img" ng-mouseover="show3=true" ng-mouseleave="show3=false">
                            <img ng-class="{'full-container':true,'dark-07':show3}"
                                 src="/statics/image/lanzuan/280-180/smzj.jpg"/>
                            <div ng-class="{'absolute-center small-90':true,'block':show3,'hidden':!show3}">
                                <div class="text-indent-1em"></div>
                                <button class="btn btn-primary m-t-05em">了解详情</button>
                            </div>
                        </a><p class="text-center">警民互动平台</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row m-x-0 m-t p-l-2em small-90">
            <div class="col-xs-12 col-md-4">
                <div class="row">
                    <div class="col-xs-8"><h4 class="p-b-1em text-center ">新闻动态</h4></div>
                    <div class="col-xs-4">
                        <a href="#" class="green-link"><i class="large-110">More</i>
                        <span class="fa-stack">
                            <i class="fa fa-circle fa-stack-1x"></i><i class="fa fa-angle-right fa-stack-1x fa-inverse"></i>
                        </span>
                        </a>

                    </div>
                </div>
                <div class="row">
                    <a href="/statics/page/news/001.html" class="col-xs-8"><i class="fa fa-file-text-o">&nbsp;</i>携手并进·共赢未来 ——2016蓝钻新媒体云服务商交流大会成功召开</a>
                    <label class="col-xs-4">2016-04-01</label>
                </div>

            </div>
            <div class="col-xs-12 col-md-4">
                <div class="row">
                    <div class="col-xs-8"><h4 class="p-b-05em text-center ">企业文化</h4></div>
                    <div class="col-xs-4">
                        <a href="#" class="green-link"><i class="large-110">More</i>
                        <span class="fa-stack">
                            <i class="fa fa fa-circle fa-stack-1x"></i><i class="fa fa-angle-right fa-stack-1x fa-inverse"></i>
                        </span>
                        </a>

                    </div>
                </div>
                <div class="row">
                    <a href="/statics/page/article/001.html" class="col-xs-8"><i class="fa fa-file-text-o">&nbsp;</i>“倾家荡产捐助”与“扎克伯格式慈善”</a>
                    <label class="col-xs-4">2012-02-25</label>
                </div>

            </div>
            <div class="col-xs-12 col-md-3">
                <div class="lg-m-r-5em">
                    <h4 class="list-group-item-heading text-center p-t-05em">活动专题</h4>
                    <img src="/statics/image/lanzuan/home/huodongzhuanti.png" class="full-width height-10em">
                </div>

            </div>
            <div class="col-md-1 hidden-md-down"></div>
        </div>
        <div class="row m-x-0 p-t-1em">
            <img class="full-width img-responsive" style="height: 376px" src="/statics/image/lanzuan/home/cloud.jpg"/>
        </div>
        <div class="row m-t-1em m-x-0">
            <div class="col-xs-6 col-md-2 text-center dash-sliver-right-border">
                <h5>关于我们</h5>
                <div class="small-90">
                    <ul class="list-unstyled">
                        <li class=""><a href="/statics/page/building.html">公司概况</a></li>
                        <li class=""><a href="/statics/page/building.html">荣誉奖项</a></li>
                        <li class=""><a href="/statics/page/building.html">新闻报道</a></li>
                        <li class=""><a href="/statics/page/building.html">活动专题</a></li>
                        <li class=""><a  href="/statics/page/building.html">人才招聘</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-6 col-md-2 text-center dash-sliver-right-border">
                <h5>售后支持</h5>
                <div class="small-90">

                    <ul class="list-unstyled">
                        <li class=" "><a href="/statics/page/building.html">在线咨询（售后）</a></li>
                        <li class=" "> <a href="/statics/page/building.html">资料库</a></li>
                        <li class=" "><a  href="/statics/page/building.html">软件下载</a></li>
                        <li class=" "> <a href="/statics/page/building.html" >建议反馈</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-6 col-md-2 text-center dash-sliver-right-border">
                <h5>如何购买</h5>
                <div class="small-90">
                    <ul class="list-unstyled">
                        <li class=" "><a href="/statics/page/building.html">在线咨询（售前）</a></li>
                        <li class=" "><a href="/statics/page/building.html">申请试用</a></li>
                        <li class=" "><a href="/statics/page/building.html">联系我们</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-6 col-md-2 text-center dash-sliver-right-border">
                <h5>合作伙伴</h5>
                <div class="small-90">
                    <ul class="list-unstyled">
                        <li class=" "><a href="/statics/page/building.html">渠道政策</a></li>
                        <li class=" "><a href="/statics/page/building.html">合作申请</a></li>
                        <li class=" "><a href="/statics/page/building.html">渠道公告</a></li>
                        <li class=" "><a href="/statics/page/building.html">SIPC俱乐部</a></li>
                        <li class=" "><a href="/statics/page/building.html">培训公告</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-xs-6 col-md-4 text-center">
                <div class="">
                    <h5>关注我们</h5>
                    <div class="row">

                        <a href="#" target="_blank"><img title="微信" src="/statics/image/lanzuan/icons/foll1.gif" class="img-responsive inline-block"/></a>

                        <a href="http://weibo.com/sangfor" target="_blank">
                            <img title="微博" src="/statics/image/lanzuan/icons/foll2.gif"  class="img-responsive inline-block"/></a>
                        <a href="" target="_blank">
                            <img title="社区" src="/statics/image/lanzuan/icons/foll3.gif" class="img-responsive inline-block"/></a>

                    </div>
                </div>
            </div>
        </div>

    </div>

        <jsp:include page="/statics/page/included/footer.jsp"></jsp:include>
        <script src="/statics/js/jquery-3.1.1.min.js"></script>
        <script src="/statics/js/tether.min.js"></script>
        <script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.min.js"></script>
        <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>
        <script>
            (function () {
                "use strict";
                var app = angular.module('homeApp', []);

                app.controller('HomeController', ["$rootScope", "$scope", "$http", "$location","$window",function ($rootScope, $scope, $http, $location, $window) {
                    $scope.test=function(){
                        $scope.active=1;
                    }
                }])
            })();
        </script>
    </body>
</html>
