<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<html>
<head>
    <title></title>
</head>
<body>
<div >${article.articleSections[0].name}</div>
<div >${article.articleSections[1].name}</div>
author:${article.author}
uploader: ${article.uploader.name}
content:${article.content}
</body>
</html>
