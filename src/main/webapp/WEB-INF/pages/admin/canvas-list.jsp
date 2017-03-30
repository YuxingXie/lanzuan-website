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

</head>
<body  class="bg-very-light-1">
<jsp:include page="${path}/admin/admin-navbar"></jsp:include>
    <div class="alert alert-info">
        <h5 class="text-center">Canvas(画布)资源列表，Canvas可以作为特殊的图片资源，某些需要特殊图片的地方可以拷贝这些值</h5>
    </div>
    <div class="container-fluid">
        <div class="row p-b-md">
            <a href="/admin/canvas/input" class="btn btn-primary fa fa-pencil"> 绘制画布</a>
            <c:if test="${not empty msg}"><label class="label label-info"></label></c:if>
        </div>
        <div class="row">
            <div class="col-xs-12 large-120 bg-light-grey">
                <div class="col-xs-1">文件名</div>
                <div class="col-xs-1">创建人</div>
                <div class="col-xs-8">效果</div>
                <div class="col-xs-2">操作</div>
            </div>
        </div>
        <c:forEach var="canvas" items="${canvasList}">
            <div class="row m-t-sm hover-bg-color-grey">
                <div class="col-xs-1">
                    ${canvas.name}
                </div>
                <div class="col-xs-1">
                        ${canvas.creator.loginName}
                </div>
                <div class="col-xs-8 ">
                    <canvas width="300" height="200" id="${canvas.id}" class="solid-silver-border">
                        您使用的浏览器不支持Canvas
                    </canvas>

                </div>
                <div class="col-xs-2" id="canvasOut">
                    <div class="btn-group btn-group-sm">
                        <a class="btn btn-primary fa fa-edit" href="/admin/canvas/input?id=${canvas.id}">修改</a>
                        <a class="btn btn-danger fa fa-trash" href="/admin/canvas/delete/${canvas.id}">删除</a>
                    </div>

                </div>
            </div>
        </c:forEach>



    </div>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="${path}/statics/js/canvas.js"></script>
    <script>
            <c:forEach var="canvas" items="${canvasList}">
            (function(id){
                    ${canvas.function}
            })('${canvas.id}');
            </c:forEach>


    </script>
</body>
</html>


