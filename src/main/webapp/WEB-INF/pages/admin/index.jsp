<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <!--<base href="/demo/" />-->
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
    <meta name="apple-mobile-web-app-status-bar-style" content="yes" />
    <link href="../../../statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../../../statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="../../../statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="../../../statics/css/color.css" rel="stylesheet" type="text/css">



</head>
<body ng-app="AdminApp" >

    <div class="container-fluid" >
        <div class="fixed-left-menu">
            <div id="accordion" role="tablist" aria-multiselectable="true">
                <div class="card  margin-bottom-0 margin-top-0">
                    <div class="card-header margin-bottom-0" role="tab" id="headingOne">
                        <h4 class="card-title  margin-bottom-0">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                布局管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="card-block padding-0 margin-0 collapse" role="tabpanel" aria-labelledby="headingOne">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>
                                导航管理
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">2</span>
                                轮播图管理
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">1</span>
                                新闻动态管理
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">1</span>
                                企业文化管理
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">1</span>
                                图标管理
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="card  margin-bottom-0 margin-top-0">
                    <div class="card-header margin-bottom-0" role="tab" id="headingTwo">
                        <h4 class="card-title  margin-bottom-0">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseOne">
                                内容管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="card-block padding-0 margin-0 collapse" role="tabpanel" aria-labelledby="headingOne">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>
                                新闻及企业文化更新
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">2</span>
                                替换轮播图
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">1</span>
                                修改导航条
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="card  margin-bottom-0 margin-top-0">
                    <div class="card-header margin-bottom-0" role="tab" id="headingThree">
                        <h4 class="card-title  margin-bottom-0">
                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseOne">
                                全局配置
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="card-block padding-0 margin-0 collapse" role="tabpanel" aria-labelledby="headingOne">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>
                                Cras justo odio
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">2</span>
                                Dapibus ac facilisis in
                            </li>
                            <li class="list-group-item">
                                <span class="label label-default label-pill pull-right">1</span>
                                Morbi leo risus
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
    <script src="/statics/js/jquery-3.1.1.min.js"></script>
    <script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
    <script src="/statics/js/tether.min.js"></script>
</body>

