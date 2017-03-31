<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>蓝钻科技文本编辑</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="yes"/>
    <link href="${path}/statics/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/plugin/bootstrap-4.0.0-alpha/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/style.css" rel="stylesheet" type="text/css">
    <link href="${path}/statics/css/color.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" charset="utf-8"
            src="${path}/statics/plugin/ueditor1_4_3_3/ueditor.config.js"></script>
    <%--<script type="text/javascript" charset="utf-8" src="${path}/statics/plugin/ueditor1_4_3_3/ueditor.all.min.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="${path}/statics/plugin/ueditor1_4_3_3/ueditor.all.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8"
            src="${path}/statics/plugin/ueditor1_4_3_3/lang/zh-cn/zh-cn.js"></script>

</head>
<body ng-app="app" ng-controller="AdminController">
<jsp:include page="${path}/admin/admin-navbar"></jsp:include>
<div class="container">
    <h1 class="p-t-md p-b-md">文本编辑器</h1>

    <div class="row">
        <script id="editor" type="text/plain"></script>
    </div>
    <div class="row">
        <div class="col-xs-12 p-t-2em">
            <form id="form" autocomplete="off" method="post" accept-charset="UTF-8" role="form" action="/admin/article/save">
                <fieldset>
                    <div class="form-group input-group">
                        <label class="fa fa-user input-group-addon">标题</label>
                        <input class="form-control" name="title" type="text" value="${article.title}">
                        <input type="hidden" name="content" id="content" />
                        <input type="hidden" name="id" id="id" value="${article.id}"/>
                    </div>
                    <div class="form-group input-group">
                        <label class="fa fa-user input-group-addon">作者</label>
                        <input class="form-control" name="author" type="text" value="${article.author}">
                    </div>
                    <button class="btn btn-lg btn-primary btn-block " onclick="uploadF()" type="button"><i class="fa fa-floppy-o"></i> 保存 文 档</button>
                </fieldset>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">


    var ue = UE.getEditor('editor');
    <c:if test="${not empty article}">
    UE.getEditor('editor').ready(function() {
        this.setContent('${article.content}')
    })
    </c:if>
    uploadF=function(){
        var art=ue.getContent();
        var hasContents=ue.hasContents();
        if(!hasContents){
            alert("文章没有内容！");
            return;
        }
        document.getElementById("content").value=art;
        document.getElementById("form").submit();
    }

    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {

        UE.getEditor('editor').setContent('${article.content}', isAppendTo);

    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }

    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData() {
        alert(UE.getEditor('editor').execCommand("getlocaldata"));
    }

    function clearLocalData() {
        UE.getEditor('editor').execCommand("clearlocaldata");
        alert("已清空草稿箱")
    }

</script>
<script src="${path}/statics/plugin/angular/1.4.8/angular.min.js"></script>
<script src="${path}/app-js"></script>
</body>
</html>