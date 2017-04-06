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


    </head>
    <body>
        <div>
            <h3>您的浏览器不受支持</h3>
            <h4>检测到您使用的浏览器内核为${browser},您可能无法完整体验本站的内容，建议使用IE9+,webkit、firefox及opera核心浏览器。</h4>
            <h5>IE6、IE7、IE8、IE9以及使用IE6、IE7、IE8、IE9内核的浏览器（如某些版本的360浏览器、百度浏览器）不支持本站的特性，要浏览本站需要下载其它浏览器。</h5>
        </div>
    </body>
</html>
