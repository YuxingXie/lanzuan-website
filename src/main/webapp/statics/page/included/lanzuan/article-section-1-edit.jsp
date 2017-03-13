<%@ page import="com.lanzuan.common.util.StringUtils" %>
<%@ page import="com.lanzuan.common.util.ReflectUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row p-a-0 m-a-0">

    <form ng-submit="" class="col-xs-12 p-a-0 m-a-0" name="form">

        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑文章块</label>
            <div class="btn-group padding-bottom-10">
                <button class="btn btn-primary fa fa-plus" type="button" ng-click="newArticleSection(${param.var})"> 新增文章块</button>
                <%
                    String varFirstUpperCase= ReflectUtil.firstUpperCase(request.getParameter("var"));
                %>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="reset<%=varFirstUpperCase%>('${param.uri}')"> 重 置</button>
                <button class="btn btn-danger fa fa-floppy-o" type="button" ng-if="addArticleSection&&!addArticleSectionSaved" ng-click="saveArticleSections(${param.var})">保存</button>
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

        <table class="table table-responsive table-hover" ng-if="${param.var}">
            <tr>
                <th >文章块名称</th>
                <th class="half-width">文章块内文章</th>
                <th>文章块操作</th>
            </tr>
            <tr ng-repeat="articleSection in ${param.var}">
                <td>
                    {{articleSection.name}}
                </td>
                <td ng-init="showArticles=false">
                    <a href="javascript:void(0)" ng-click="$parent.showArticles=!$parent.showArticles" class="fa" ng-class="{'fa-minus-square':$parent.showArticles,'fa-plus-square':!$parent.showArticles}"
                       ng-if="!articleSection.image &&articleSection.articles&&articleSection.articles.length">
                        文章块包含{{articleSection.articles.length}}篇文章,点击查看
                    </a>
                    <table class="table table-hover table-responsive" ng-if="showArticles">
                        <tr ng-repeat="article in articleSection.articles">
                            <td>
                                <i>{{article.title}}</i>
                            </td>
                            <td>
                                <span ng-if="!article.duplicated"  class="btn-group">
                                    <span class="btn btn-danger btn-sm  fa fa-warning" ng-if="!article.byEditor">此文无法用编辑器编辑</span>
                                <a ng-if="article.byEditor" class="btn btn-primary btn-sm  fa fa-pencil-square-o white-link" ng-href="/admin/file-editor/${pageComponent.id}/{{articleSection.id}}/{{article.id}}">编辑此文</a>
                                <button class="btn btn-primary btn-sm  fa fa-trash " ng-click="removeArticle(article)" > 删掉此文</button>
                                </span>
                                <span ng-if="article.duplicated">
                                    这篇文章出现在其它版块，请在首次出现的版块编辑它
                                </span>



                            </td>
                        </tr>
                    </table>
                    <div  ng-if="!articleSection.articles&&!articleSection.image">
                        此版块无内容,可以将该文章块块变为只显示图片的区域
                        <a class="btn btn-primary btn-sm btn-padding-little white-link"
                           ng-href="${path}/admin/article_section/image/input/${pageComponent.id}/{{articleSection.id}}">增加图片</a>
                    </div>

                    <img ng-if="articleSection.image" ng-src="{{articleSection.image}}" class="img-responsive img-ico-xl">
                </td>
                <td>
                    <div class="btn-group" ng-if="!articleSection.image">
                        <a class="btn btn-primary white-link btn-sm fa fa-plus" ng-href="/admin/file-editor/in-section/${pageComponent.id}/{{articleSection.id}}" ng-if="articleSection.id"> 为此文章块撰文</a>
                        <button class="btn btn-primary white-link btn-sm fa fa-plus" ng-click="removeArticleSection(articleSection,$index)"> 删除此文章块</button>
                        <div class="input-group padding-left-2">
                            <input type="text" ng-model="articleSection.name" class="">
                            <span class="input-group-btn"><button class="btn btn-primary btn-sm" ng-click="renameArticleSection(articleSection)" ng-if="articleSection.id">重命名</button></span>
                        </div>


                    </div>
                    <span class="text-danger" ng-if="!articleSection.id">未保存*</span>{{articleSection.id}}
                    <div ng-if="!articleSection.articles&&articleSection.image" class="btn-group btn-group-sm">
                        <a class="btn btn-primary white-link" ng-href="${path}/admin/article_section/image/input/${pageComponent.id}/{{articleSection.id}}">更换图片</a>
                        <button class="btn btn-primary white-link fa fa-plus" ng-click="removeArticleSection(articleSection,$index)"> 删除该块</button>
                        <div class="input-group padding-left-2">
                            <input type="text" ng-model="articleSection.name" class="">
                            <span class="input-group-btn"><button class="btn btn-primary btn-sm" ng-click="renameArticleSection(articleSection)" ng-if="articleSection.id">重命名</button></span>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>