<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="utf-8" />
  <title>HTML5 image crop tool | Script Tutorials</title>
  <link href="/statics/plugin/image-crop/css/main.css" rel="stylesheet" type="text/css" />
  <script src="/statics/js/jquery-3.1.1.min.js"></script>
  <script src="/statics/plugin/image-crop/js/script.js"></script>
</head>
<body>
<header>
  <h2>HTML5 image crop tool</h2>
  <a href="http://www.script-tutorials.com/html5-image-crop-tool/" class="stuts">Back to original tutorial on <span>Script Tutorials</span></a>
</header>
<div class="container">
  <div class="contr">
    <button onclick="getResults()">Crop</button>
  </div>
  <canvas id="panel" width="779" height="519"></canvas>
  <div id="results">
    <h2>Please make selection for cropping and click 'Crop' button.</h2>
    <img id="crop_result" />
  </div>
</div>
</body>
</html>