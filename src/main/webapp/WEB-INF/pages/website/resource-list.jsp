<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>湖南蓝钻科技</title>
    <link rel="stylesheet" href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/statics/css/style.css"/>
    <link rel="stylesheet" href="${path}/statics/css/color.css"/>
    <link rel="stylesheet" href="${path}/statics/plugin/animate.css-master/animate.min.css"/>
    <link rel="stylesheet" href="${path}/statics/css/bootstrap.custom.css"/>
    <link rel="stylesheet" href="${path}/statics/css/responsive.css3.css"/>
    <style>
        img {
            max-width: 100%;
        }
    </style>
</head>
<body ng-app="app" class="bg-very-light-1">
<div ng-controller="ArticleController" >

    <div class="container-fluid">
        <div class="row p-a-0 m-a-0">
            <div class="col-xs-12 col-md-3 p-a-0 m-a-0" style="position:fixed;top:0;z-index:100;">
                <div class="col-xs-12 col-md-push-2 col-md-10 m-a-0 p-a-0">
                    <div class="card" style="border-top-left-radius: 0;border-top-right-radius: 0">
                        <div class="card-header p-a-0 list hidden-md-down">
                            <img src="${path}/statics/image/lanzuan/icons/ico.jpg" class="card-img-top img-responsive">
                        </div>
                        <div class="card-block p-a-0 m-a-0" >
                            <ol class="breadcrumb m-a-0 p-a-xs">
                                当前位置:
                                <li class="small-90 fa fa-home"><a href="/"> 首页</a></li>
                                <li class="small active">资源下载</li>
                            </ol>
                        </div>
                        <a href="#content" class="list-group-item hidden-md-down">${article.title}</a>
                    </div>
                </div>
            </div>
            <div style="position:absolute;top:50px;left:0;width: 100%;" id="content" >
                <div class="col-xs-12 p-a-0 m-a-0" >
                    <div class="col-xs-12">
                        <div class="col-md-8 col-md-push-3 col-xs-12"   >
                            <div class="col-xs-12 text-center p-a-0 m-a-0 ">
                                <h4>资源下载</h4>
                            </div>
                            <div class="col-xs-12 bg-white ">
                                <c:if test="${empty webResourceList}">暂时没有资源</c:if>
                                <c:if test="${not empty webResourceList}">
                                    <div class="col-xs-12 large-120 bg-light-grey">
                                        <div class="col-xs-4">文件名</div>
                                        <div class="col-xs-4">类别</div>
                                        <div class="col-xs-4">下载路径(uri)</div>
                                    </div>
                                    <c:forEach var="resource" items="${webResourceList}">
                                        <div class="col-xs-12 hover-bg-grey p-t p-b">
                                            <div class="col-xs-4">
                                                    ${resource.name}
                                            </div>
                                            <div class="col-xs-4">
                                                    ${resource.type}
                                            </div>
                                            <div class="col-xs-4">
                                                <a href="/file-download?path=${resource.path}">点击下载</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 p-l-0 p-r-0 m-l-0 m-r-0 m-t-xl">
                        <jsp:include page="${path}/statics/page/included/footer.html"></jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-xs-12 p-t-50">
</div>
<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>

</body>
</html>


