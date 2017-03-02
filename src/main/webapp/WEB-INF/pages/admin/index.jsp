<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <link href="/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="/statics/css/color.css" rel="stylesheet" type="text/css">



</head>
<body ng-app="AdminApp" >
<div>

</div>
    <div class="container-fluid" >
        <div class="row">
            <div class="col-xs-10 col-md-offset-2">
                <i class="fa" ng-class="{'fa-male':${empty user.sex or user.sex eq 'male'},'fa-female':${not(empty user.sex or user.sex eq 'male')}}"></i>
                欢迎您，${user.name}
            </div>
        </div>
        <div class="">
            <div id="accordion" role="tablist" aria-multiselectable="true" ng-init="_in=1;collapse1=true;collapse2=true;collapse3=true">
                <div class="card  margin-bottom-0 margin-top-0">
                    <div class="card-header margin-bottom-0" role="tab" id="headingOne">
                        <a href="javascript:void(0)" ng-click="_in=1;collapse1=!collapse1;collapse2=true;collapse3=true;">
                            <h4 class="card-title  margin-bottom-0">布局管理</h4>
                        </a>
                    </div>
                    <div id="collapseOne" class="card-block padding-0 margin-0" ng-class="{'collapse':_in!==1||collapse1,'in':_in===1&&!collapse1}" role="tabpanel" aria-labelledby="headingOne">
                        <div class="list-group">
                            <a class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>导航管理
                            </a>
                            <a class="list-group-item">
                               <span class="label label-default label-pill pull-right">14</span>轮播图管理
                            </a>
                            <a class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>新闻动态管理
                            </a>
                            <a class="list-group-item">
                               <span class="label label-default label-pill pull-right">14</span>企业文化管理
                            </a>
                            <a class="list-group-item">
                               <span class="label label-default label-pill pull-right">14</span>图标管理
                            </a>
                        </div>
                    </div>
                </div>
                <div class="card  margin-bottom-0 margin-top-0">
                    <div class="card-header margin-bottom-0" role="tab" id="headingTwo">
                        <a href="javascript:void(0)" ng-click="_in=2;collapse2=!collapse2;collapse1=true;collapse3=true">
                            <h4 class="card-title margin-bottom-0">内容管理</h4>
                        </a>
                    </div>
                    <div id="collapseTwo" class="card-block padding-0 margin-0 " ng-class="{'collapse':_in!==2||collapse2,'in':_in===2&&!collapse2}" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="list-group">
                            <a class="list-group-item">
                               <span class="label label-default label-pill pull-right">14</span>新闻及企业文化更新
                            </a>
                            <a class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>替换轮播图
                            </a>
                            <a class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>修改导航条
                            </a>
                        </div>
                    </div>
                </div>
                <div class="card  margin-bottom-0 margin-top-0">
                    <div class="card-header margin-bottom-0" role="tab" id="headingThree">
                        <a href="javascript:void(0)" ng-click="_in=3;collapse3=!collapse3;collapse1=true;collapse2=true;">
                            <h4 class="card-title  margin-bottom-0">全局配置</h4>
                        </a>
                    </div>
                    <div id="collapseThree" class="card-block padding-0 margin-0" ng-class="{'collapse':_in!==3||collapse3,'in':_in===3&&!collapse3}" role="tabpanel" >
                        <div class="list-group">
                            <a class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>Cras justo odio
                            </a>
                            <a class="list-group-item">
                               <span class="label label-default label-pill pull-right">14</span>Dapibus ac facilisis in
                            </a>
                            <a class="list-group-item">
                                <span class="label label-default label-pill pull-right">14</span>Morbi leo risus
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
    <%--<script src="/statics/js/jquery-3.1.1.min.js"></script>--%>
    <%--<script src="/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>--%>
    <%--<script src="/statics/js/tether.min.js"></script>--%>
    <script src="/statics/js/admin.js"></script>
</body>

