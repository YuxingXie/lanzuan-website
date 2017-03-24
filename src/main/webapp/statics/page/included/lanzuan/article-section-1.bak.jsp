<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row m-l-0 m-r-0 m-t-1em md-up-p-l-r-4em p-t-4em p-b-2em  bg-very-light" >
        <c:if test="${param.mode eq 'site'}">
            <c:forEach varStatus="index" items="${pageComponent.data.items}" var="sortLink">
                <div class="col-xs-12 col-md-4">
                    <div class="row">
                        <h4 class="col-xs-8 large-110 xl-large-180 lg-large-165 md-large-150 sm-large-135">${sortLink.sortName}</h4>
                        <c:if test="${not empty sortLink.image}">
                            <a target="_blank" href="${sortLink.imageHref}"><img src="${sortLink.image}" class="col-xs-12 p-t-1em" /></a>
                        </c:if>
                        <c:if test="${not empty sortLink.links}">
                            <a href="#" class="col-xs-4 green-link">
                                <span class="large-110">More</span>
                            <span class="fa-stack">
                                <i class="fa fa-circle fa-stack-1x"></i><i
                                    class="fa fa-angle-right fa-stack-1x fa-inverse padding-left-2"></i>
                            </span>
                            </a>
                        </c:if>
                    </div>
                    <c:choose>
                        <c:when test="${not empty sortLink.links}">
                            <div class="list-group small-90 p-r-3em">
                                <c:forEach var="link" items="${sortLink.links}">
                                    <a href="${link.href}" class="list-group-item p-l-0 p-r-0 no-border no-background fa fa-ext-dot-blue">
                            <span class="color-grey bg-medium-grey-2 label-pill pull-right" style="border-radius: .2rem;background-color: #efefefef">${link.date}
                            </span>${link.text}
                                    </a>
                                </c:forEach>

                            </div>
                        </c:when>
                        <c:when test="${empty sortLink.links and empty sortLink.image}">
                            <div class="list-group small-90 p-r-3em">
                                <c:if test="${empty sortLink.links}"></c:if>
                                <span class="list-group-item p-l-0 p-r-0 text-center p-t-2em text-primary dash-silver-border">暂无文章</span>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${param.mode eq 'admin' or param.mode eq 'prev'}">
            <div class="row m-l-0 m-r-0 m-t-1em md-up-p-l-r-4em p-t-4em p-b-2em  bg-very-light"
                 ng-init="get${pageComponent.varU}()">

                <div class="col-xs-12 col-md-4" ng-repeat="sortLink in ${pageComponent.var}.items">
                    <div class="row">
                        <h4 class="col-xs-8 large-180">{{sortLink.sortName}}</h4>
                        <a ng-if="sortLink.image" target="_blank" href="{{sortLink.imageHref}}"><img src="{{sortLink.image}}" class="col-xs-12 p-t-1em" /></a>
                        <a href="#" class="col-xs-4 green-link" ng-if="sortLink.links">
                            <span class="large-110">More</span>
                            <span class="fa-stack">
                                <i class="fa fa-circle fa-stack-1x"></i><i
                                    class="fa fa-angle-right fa-stack-1x fa-inverse padding-left-2"></i>
                            </span>
                        </a>

                    </div>
                    <div class="list-group small-90 p-r-3em" ng-if="sortLink.links">
                        <a href="{{link.href}}"
                           class="list-group-item p-l-0 p-r-0 no-border no-background fa fa-ext-dot-blue"
                           ng-repeat="link in sortLink.links">

                <span class="color-grey bg-medium-grey-2 label-pill pull-right"
                      style="border-radius: .2rem;background-color: #efefefef">{{link.date}}</span>{{link.text}}
                        </a>
                    </div>
                    <div class="list-group small-90 p-r-3em" ng-if="!sortLink.links &&!sortLink.image">
            <span class="list-group-item p-l-0 p-r-0 text-center p-t-2em text-primary dash-silver-border"
                  ng-if="!sortLink.links">暂无文章</span>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${param.mode eq 'admin'}">
            <div class="row p-a-0 m-a-0">
                <div class="col-xs-12 m-a-0 p-a-0">
                    <label class="label label-default large-180">编辑文章块</label>
                    <label class="label label-info large-180">{{${pageComponent.varU}.name}}</label>
                    <div class="btn-group p-b-10">
                        <button class="btn btn-primary fa fa-plus" type="button" ng-click="insert${pageComponent.varU}Item()"> 新增文章块</button>
                        <button class="btn btn-danger fa fa-floppy-o" type="button" ng-click="save${pageComponent.varU}()"> 保存</button>
                        <button class="btn btn-primary fa fa-copy" type="button" ng-click="new${pageComponent.varU}()">方案另存为</button>
                        <a class="btn btn-primary fa fa-gears white-link" ng-href="${path}${pageComponent.listOperationUri}/${pageComponent.id}"> 应用方案</a>
                        <button class="btn btn-primary fa fa-refresh" type="button" ng-click="get${pageComponent.varU}('${param.dataUri}')"> 重 置</button>
                        <button class="btn btn-danger fa fa-floppy-o" type="button" ng-if="addArticleSection&&!addArticleSectionSaved" ng-click="save${pageComponent.varU}()">保存</button>
                    </div>
                </div>
                <div class="col-xs-12">
                    <div class="alert alert-warning">
                        <ul class="list-unstyled">
                            <li><i class="fa fa-warning fa-fw"></i>有些文章在不同版块都显示，但在这里（编辑模式下）这些文章只显示一次；</li>
                            <li><i class="fa fa-warning fa-fw"></i>如果超过三个文章块，可能在较大尺寸屏幕下显示混乱，除非它们一样高；</li>
                            <li><i class="fa fa-warning fa-fw"></i>基于性能考虑(文章一般比较大)，此处并不维护文章与链接的关系，如果文章删除，点击文章标题链接会出现找不到页面的404错误。</li>
                            <li><i class="fa fa-warning"></i>如果没有合适的文章封面，您可以先<a href="${pageComponent.materialUploadUri}/${pageComponent.id}"
                                                                                style="text-decoration: underline;"><i>上传文章封面</i></a></li>
                        </ul>

                    </div>
                </div>
                <div class="col-xs-12 col-md-4" ng-if="!${pageComponent.var}">
                    <h5>此组件还没有文章块，可以点击“新增文章块”按钮增加一个文章块</h5>
                </div>
            </div>

            <div class="row" ng-init="getArticleCovers()">
                <div class="col-xs-8">文章操作</div>
                <div class="col-xs-4">块操作</div>
            </div>
            <div class="row p-t-md p-b-md small-90 hover-bg-color-dark-grey"   ng-repeat="sortLink in ${pageComponent.var}.items" ng-if="${pageComponent.var}">
                <div class="col-xs-12" ng-if="!sortLink.links &&!sortLink.image">
                    <span class="">该块无内容</span>
                    <div class="row p-t-10">
                        <div class="btn-group col-xs-12">
                            <button class="btn btn-primary btn-sm" ng-click="newSortLinkAddArticle($index)">添加文章</button>
                            <button type="button"  class="btn btn-primary btn-sm">添加图片</button>
                            <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown"
                                    aria-haspopup="true"
                                    aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <div class="dropdown-menu dropdown-full-width">
                    <span ng-repeat="icon in icons" class="dropdown-item-inline"
                          ng-click="sortLink.image=icon">
                        <img type="text" ng-src="{{icon}}" class="img-ico-lg img-rounded"/>
                    </span>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-xs-12" ng-if="sortLink.image">
                    <div class="btn-group col-xs-12">
                        <button type="button" class="btn btn-primary btn-sm">更换图片</button>
                        <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu dropdown-full-width">
                    <span ng-repeat="icon in icons" class="dropdown-item-inline"
                          ng-click="sortLink.image=icon">
                        <img type="text" ng-src="{{icon}}" class="img-ico-lg img-rounded"/>
                    </span>
                        </div>

                    </div>

                </div>
                <div class="col-xs-8">
                    <img  ng-src="{{sortLink.image}}" class="img-responsive img-ico-xl col-xs-5">
                    <div class="input-group col-xs-7" ng-if="sortLink.image">
                        <span class="input-group-addon">链接</span>
                        <input type="text" ng-model="sortLink.imageHref" class="form-control"/>
                    </div>
                </div>
                <div class="col-xs-4">

                </div>
                <div class="col-xs-8">
                    <div class="col-xs-12 p-b-md" ng-repeat="link in sortLink.links" ng-if="sortLink.links">
                        <div class="col-xs-7">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">标题</span><input class="form-control" ng-model="link.text" type="text">
                            </div>
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">链接</span><input class="form-control" ng-model="link.href" type="text">
                                <span class="input-group-addon"><a class="black-link fa fa-question-circle" target="_blank" href="/admin/article/list"> </a></span>
                            </div>
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">日期</span><input class="form-control" ng-model="link.date" type="text">
                            </div>
                        </div>
                        <div class="col-xs-5 btn-group btn-group-sm">
                            <button class="btn btn-primary  fa fa-trash " ng-if="!sortLink.image" ng-click="sortLink.links.splice($index,1)">删除此文</button>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="btn-group btn-group-sm m-b-10">
                        <button class="btn btn-primary btn-sm  fa fa-angle-up" ng-click="forward${pageComponent.varU}Item($index)" ng-if="$index!==0">整块前移</button>
                        <button class="btn btn-primary btn-sm  fa fa-angle-down" ng-click="backward${pageComponent.varU}Item($index)" ng-if="$index!==${pageComponent.var}.items.length-1">整块后移</button>
                        <button class="btn btn-primary white-link fa fa-trash" ng-click="remove${pageComponent.varU}Item($index)"> 删除该块</button>
                        <button class="btn btn-primary" ng-if="(!sortLink.links&&!sortLink.image)||sortLink.links" ng-click="sortLink.links.splice(0,0,{'text':'一篇文章'})">增加文章</button>
                    </div>
                    <div class="input-group input-group-sm m-b-10">
                        <span class="input-group-addon">分类名称</span>
                        <input type="text" ng-model="sortLink.sortName"  class="form-control">
                    </div>
                </div>
            </div>
        </c:if>
</div>