<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
    <meta name="apple-mobile-web-app-status-bar-style" content="yes" />
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
</head>
<body >

        <jsp:include page="${path}/admin/admin-navbar"></jsp:include>
        <div class="container" >

        <div class="row p-t-2em m-a-0">
                <div class="col-xs-12 alert alert-info" >添加或修改一条画布(Canvas)</div>

                <form autocomplete="off" method="post" accept-charset="UTF-8" role="form" action="/admin/canvas/update">
                        <c:if test="${not empty canvas}">
                            <input type="hidden" name="id" value="${canvas.id}"/>
                        </c:if>
                        <div class="form-group input-group">
                            <label class="fa fa-user input-group-addon">画布名</label>
                            <input class="form-control" type="text" name="name" <c:if test="${not empty canvas}">value="${canvas.name}"</c:if>/>
                        </div>

                        <div class="form-group input-group">
                            <label class="fa fa-code input-group-addon">画布函数</label>
                            <textarea class="form-control"  name="function" rows="18"><c:choose><c:when test="${not empty canvas}"> ${canvas.function}</c:when><c:otherwise>

      /**
        * id表示画布的id
        * 只需要函数体内容，不需要函数声明
        * 请去掉代码中的控制台输出和模态框弹出
        * 系统不会检测语法是否正确，错误的代码可能导致页面异常，请确保上传的代码正确无误
        * 下面是一个示例
      */
    //  ---------示例开始--------------
    var drawing=document.getElementById(id);
    if(drawing.getContext){
        var context=drawing.getContext("2d");
        context.strokeStyle="#000";
        context.fillStyle="#ff0000";
        context.fillRect(10,10,50,50);
        context.strokeRect(10,10,50,50);
    }
     //----------示例结束  ---------------

</c:otherwise></c:choose>
                                </textarea>
                        </div>

                    <button class="btn btn-lg btn-primary btn-block" type="submit"><i class="fa fa-sign-in"></i> 上传</button>
                </form>
        </div>
    </div>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
    <script src="${path}/statics/plugin/angular/1.4.8/angular-route.min.js"></script>
    <script src="${path}/statics/js/jquery-3.1.1.min.js"></script>
    <script src="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/js/bootstrap.js"></script>
    <%--<script src="${path}/app-js"></script>--%>
</body>

