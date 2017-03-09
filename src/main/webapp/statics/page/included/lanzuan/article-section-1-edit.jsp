<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="row p-a-0 m-a-0">

    <form ng-submit="" class="col-xs-12 p-a-0 m-a-0" name="form">

        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑文章分类</label>
            <div class="btn-group padding-bottom-10">
                <button class="btn btn-primary fa fa-plus" type="button" ng-click="newArticleSection()"> 新增文章分类</button>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="resetArticleSections(form)"> 重 置</button>
                <button class="btn btn-danger fa fa-floppy-o" type="button" ng-if="addArticleSection&&!addArticleSectionSaved" ng-click="saveNewArticleSection()">保存新增文章分类</button>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-warning fa-fw"></i>有些文章在不同版块都显示，但在这里（编辑模式下）这些文章只显示一次；</li>
                    <li><i class="fa fa-flash fa-fw color-red"></i> 不是通过本系统文本编辑器生成的文章<b class="color-red">无法在编辑器中正确显示</b>，所以如果点击“编辑此文”按钮后文章显示异常，则无法编辑。</li>
                    <li><i class="fa fa-flash fa-fw color-red"></i> 新增的文章分类<b class="color-red">一定要</b>点击“保存新增文章分类”才会被系统保存。</li>
                </ul>

            </div>
        </div>
        <div class="col-xs-12 col-md-4" ng-if="!articleSections">
            <h5>此组件还没有文章分类，可以点击“新增文章分类”按钮增加一个文章分类</h5>
        </div>

        <table class="table table-responsive table-hover" ng-if="articleSections">
            <tr>
                <th >文章分类名称</th>
                <th class="half-width">文章分类内文章</th>
                <th>文章分类操作</th>
            </tr>
            <tr ng-repeat="articleSection in articleSections">
                <td>
                    {{articleSection.name}}
                </td>
                <td ng-init="showArticles=false">
                    <a href="javascript:void(0)" ng-click="$parent.showArticles=!$parent.showArticles" class="fa" ng-class="{'fa-minus-square':$parent.showArticles,'fa-plus-square':!$parent.showArticles}"
                       ng-if="!articleSection.image &&articleSection.articles&&articleSection.articles.length">
                        文章分类包含{{articleSection.articles.length}}篇文章,点击查看
                    </a>
                    <table class="table table-hover table-responsive" ng-if="showArticles">
                        <tr ng-repeat="article in articleSection.articles">
                            <td>
                                <i>{{article.title}}</i>
                            </td>
                            <td>
                                <span ng-if="!article.duplicated"  class="btn-group">
                                    <span class="btn btn-danger btn-sm  fa fa-warning" ng-if="!article.byEditor">此文无法用编辑器编辑</span>
                                <a ng-if="article.byEditor" class="btn btn-primary btn-sm  fa fa-pencil-square-o white-link" ng-href="/admin/file-editor/${pageComponent.id}/{{article.id}}">编辑此文</a>
                                <button class="btn btn-primary btn-sm  fa fa-trash " ng-click="removeArticle(article)" > 删掉此文</button>
                                </span>
                                <span ng-if="article.duplicated">
                                    这篇文章出现在其它版块，请在首次出现的版块编辑它
                                </span>



                            </td>
                        </tr>
                    </table>
                    <div  ng-if="!articleSection.articles&&!articleSection.image">
                        此版块无内容
                    </div>
                    <span ng-if="!articleSection.articles&&articleSection.image" class="btn-sm  fa fa-warning">

                            这个版块没有文章，只包含一张图片

                    </span>
                    <img ng-if="articleSection.image" ng-src="{{articleSection.image}}" class="img-responsive">
                </td>
                <td>
                    <div class="btn-group" ng-if="!articleSection.image">
                        <a class="btn btn-primary white-link btn-sm fa fa-plus" ng-href="/admin/file-editor/in-section/${pageComponent.id}/{{articleSection.id}}" ng-if="articleSection.id"> 为此文章分类撰文</a>
                        <button class="btn btn-primary white-link btn-sm fa fa-plus" ng-click="removeArticleSection(articleSection,$index)"> 删除此文章分类</button>
                        <div class="input-group padding-left-2">
                            <input type="text" ng-model="articleSection.name" class="">
                            <span class="input-group-btn"><button class="btn btn-primary btn-sm" ng-click="renameArticleSection(articleSection)" ng-if="articleSection.id">重命名</button></span>
                        </div>
                    </div>
                    <div ng-if="!articleSection.articles&&articleSection.image" class="">
                        <button disabled class="btn btn-primary">更换图片</button>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>