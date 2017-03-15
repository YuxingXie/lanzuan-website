<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row p-a-0 m-a-0">
        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑文章块${param.varU} ${param.var}</label>
            <div class="btn-group padding-bottom-10">
                <button class="btn btn-primary fa fa-plus" type="button" ng-click="newArticleSection()"> 新增文章块</button>

                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="get${param.varU}('${param.dataUri}')"> 重 置</button>
                <button class="btn btn-danger fa fa-floppy-o" type="button" ng-if="addArticleSection&&!addArticleSectionSaved" ng-click="save${param.varU}()">保存</button>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-warning fa-fw"></i>有些文章在不同版块都显示，但在这里（编辑模式下）这些文章只显示一次；</li>
                    <li><i class="fa fa-warning fa-fw"></i>如果超过三个文章块，可能在较大尺寸屏幕下显示混乱，除非它们一样高；</li>
                    <li><i class="fa fa-flash fa-fw color-red"></i> 不是通过本系统文本编辑器生成的文章<b class="color-red">无法在编辑器中正确显示</b>，点击“编辑此文”无法编辑；</li>
                    <li><i class="fa fa-flash fa-fw color-red"></i> 新增的文章块<b class="color-red">一定要</b>点击“保存新增文章块”才会被系统保存。</li>

                </ul>

            </div>
        </div>
        <div class="col-xs-12 col-md-4" ng-if="!${param.var}">
            <h5>此组件还没有文章块，可以点击“新增文章块”按钮增加一个文章块</h5>
        </div>
</div>

<div class="row">
        <div class="col-xs-2">文章块标题</div>
        <div class="col-xs-5">文章块内文章</div>
        <div class="col-xs-5">文章块操作</div>
    </div>
<div class="row p-t-md p-b-md small-90" ng-class-even="'bg-dark-grey'"  ng-repeat="articleSection in ${param.var}.${param.var}Items" ng-if="${param.var}">
    <div class="col-xs-2">
        {{articleSection.sortName}}
    </div>
    <div class="col-xs-5">

        <table class="table table-hover table-responsive">
            <tr ng-repeat="article in articleSection.links">
                <td>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">标题</span><input class="form-control" ng-model="article.text" type="text">
                    </div>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">链接</span><input class="form-control" ng-model="article.href" type="text">
                        <span class="input-group-addon"><a class="black-link fa fa-question-circle" target="_blank" href="/admin/article/list"> </a></span>
                    </div>



                </td>
                <td>
                    <span class="btn-group">
                        <a ng-if="article.byEditor" class="btn btn-primary btn-sm  fa fa-pencil-square-o white-link" ng-href="/admin/file-editor/${pageComponent.id}/{{articleSection.id}}/{{article.id}}">编辑</a>
                        <button class="btn btn-primary btn-sm  fa fa-trash " ng-click="removeArticle(article)" >删除</button>
                    </span>

                </td>
            </tr>
        </table>
        <div  ng-if="!articleSection.links&&!articleSection.image">
            此版块无内容,可以将该文章块块变为只显示图片的区域
            <a class="btn btn-primary btn-sm btn-padding-little white-link"
               ng-href="${path}${param.muu}/${pageComponent.id}/{{articleSection.id}}">增加图片</a>
        </div>

        <img ng-if="articleSection.image" ng-src="{{articleSection.image}}" class="img-responsive img-ico-xl">
    </div>
    <div class="col-xs-5">
        <div class="btn-group" ng-if="!articleSection.image">
            <a class="btn btn-primary white-link btn-sm fa fa-plus" ng-href="/admin/file-editor/in-section/${pageComponent.id}/{{articleSection.id}}" ng-if="articleSection.id"> 为此文章块撰文</a>
            <button class="btn btn-primary white-link btn-sm fa fa-trash" ng-click="removeArticleSection(articleSection,$index)"> 删除该块</button>
        </div>
        <div class="input-group input-group-sm margin-top-10">
            <input type="text" ng-model="articleSection.name" class="form-control">
            <span ng-if="articleSection.id" class="input-group-btn"><button class="btn btn-primary" ng-click="renameArticleSection(articleSection)">重命名</button></span>
            <span class="text-danger input-group-addon" ng-if="!articleSection.id">未保存*</span>
        </div>


        <div ng-if="!articleSection.articles&&articleSection.image" class="btn-group btn-group-sm">
            <a class="btn btn-primary white-link" ng-href="${path}/admin/article_section/image/input/${pageComponent.id}/{{articleSection.id}}">更换图片</a>
            <button class="btn btn-primary white-link fa fa-plus" ng-click="removeArticleSection(articleSection,$index)"> 删除该块</button>
            <div class="input-group input-group-sm">
                <input type="text" ng-model="articleSection.name" class="form-control">
                <span class="input-group-btn"><button class="btn btn-primary" ng-click="renameArticleSection(articleSection)" ng-if="articleSection.id">重命名</button></span>
            </div>
        </div>
    </div>
</div>
