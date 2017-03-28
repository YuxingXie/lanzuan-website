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
        <h5 class="text-center">网站资源列表，需要填写下载地址时可以拷贝这些值</h5>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 large-120 bg-light-grey">
                <div class="col-xs-4">文件名</div>
                <div class="col-xs-4">类别</div>
                <div class="col-xs-4">下载路径(uri)</div>
            </div>
            <div class="col-xs-12 small-90 solid-silver-border-bottom">
                <c:if test="${empty webResourceList}">暂时没有资源</c:if>
                <c:if test="${not empty webResourceList}">
                    <c:forEach var="resource" items="${webResourceList}">
                    <div class=" hover-bg-color-grey p-t p-b">
                        <div class="col-xs-4">
                            ${resource.name}
                        </div>
                        <div class="col-xs-4">
                            ${resource.type}
                        </div>
                        <div class="col-xs-4">
                            /file-download?path=${resource.path}
                        </div>
                    </div>
                    </c:forEach>
                </c:if>

            </div>
        </div>
    </div>
    <script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
</body>
</html>


