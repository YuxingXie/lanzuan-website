<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.mode eq 'site' and false}">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="4000">
        <ol class="carousel-indicators bottom-0">
            <c:forEach var="item" items="${pageComponent.data.items}" varStatus="index">
                <li data-target="#carousel-example-generic" data-slide-to="${index.index}" ng-class="{'active':${index.index}===0}"></li>
            </c:forEach>
        </ol>
        <div class="carousel-inner " role="listbox">
            <c:forEach var="item" items="${pageComponent.data.items}" varStatus="index">
                <div class="carousel-item" ng-class="{'active':${index.index}===0}">
                    <c:if test="${item.type eq 'image'}">
                        <img class="center-block full-width" src="${item.value}"/>
                    </c:if>
                    <c:if test="${not empty item.carouselCaption}">
                        <div class="carousel-caption">
                            <c:if test="${item.carouselCaption.type eq 'link'}">
                                <a href="${item.carouselCaption.value}"
                                   class="btn btn-primary hidden-md-down white-link">${item.carouselCaption.text} <i class="fa fa-chevron-right right"></i></a>
                            </c:if>
                            <c:if test="${item.carouselCaption.type eq 'text'}">
                             <span>
                               ${item.carouselCaption.text}}
                            </span>
                            </c:if>
                        </div>
                    </c:if>

                </div>
            </c:forEach>
        </div>
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="icon-prev" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="icon-next" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</c:if>
<c:if test="${param.mode eq 'admin'or param.mode eq 'prev' or param.mode eq 'site'}">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="4000" ng-init="get${pageComponent.varU}()">
        <ol class="carousel-indicators bottom-0">
            <li data-target="#carousel-example-generic" data-slide-to="{{$index}}" ng-class="{'active':$index===0}" ng-repeat="carouselItem in ${pageComponent.var}.items"></li>
        </ol>
        <div class="carousel-inner " role="listbox">
            <div class="carousel-item" ng-class="{'active':$index===0}" ng-repeat="carouselItem in ${pageComponent.var}.items">
                <img  class="center-block full-width" ng-src="{{carouselItem.value}}"/>
                <div ng-if="carouselItem.carouselCaption" class="carousel-caption">
                    <a ng-if="carouselItem.carouselCaption.type &&carouselItem.carouselCaption.type==='link'"
                       ng-href="{{carouselItem.carouselCaption.value}}"
                       class="btn btn-primary hidden-md-down white-link">{{carouselItem.carouselCaption.text}} <i class="fa fa-chevron-right right"></i></a>


                <span ng-if="carouselItem.carouselCaption.type &&carouselItem.carouselCaption.type==='text'">
                   {{carouselItem.carouselCaption.text}}
                </span>

                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="icon-prev" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="icon-next" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</c:if>
<c:if test="${param.mode eq 'admin' and false}">
    <div class="row p-a-0 m-a-0">
        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑轮播图</label>
            <label class="label label-info large-180">当前方案：{{${pageComponent.var}.name}}</label>

            <div class="btn-group p-b-10">
                <button class="btn btn-danger fa fa-floppy-o" type="button" ng-click="save${pageComponent.varU}()">保存</button>
                <button class="btn btn-primary fa fa-plus" type="button" ng-click="insert${pageComponent.varU}Item()">插入一条</button>
                <button class="btn btn-primary fa fa-copy" type="button" ng-click="new${pageComponent.varU}()"> 方案另存</button>

                <a class="btn btn-primary white-link fa fa-download" ng-href="${path}${pageComponent.listOperationUri}${pageComponent.id}"> 应用方案</a>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="reset${pageComponent.varU}()"> 重 置</button>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-graduation-cap fa-fw"></i>如果没有合适的图标，您可以先<a href="${pageComponent.materialUploadUri}/${pageComponent.id}"
                                                                                   style="text-decoration: underline;"><i>上传素材</i></a></li>
                    <li><i class="fa fa-graduation-cap fa-fw"></i>轮播图不但可以轮播“图”，还可以轮播任何内容；</li>
                    <li><i class="fa fa-graduation-cap fa-fw"></i>点击“插入一条”,“前移”，“后移”按钮后，顶端的预览效果会可能出现异常；</li>
                    <li><i class="fa fa-warning fa-fw"></i>图片高宽比例不一致导致的，会导致页面跳动，请确保图片使用相同高宽比例。</li>


                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-md-4" ng-if="!${pageComponent.var}">
            <h5>此轮播图组件还没有图片</h5>
        </div>

        <div class="col-xs-12" ng-if="${pageComponent.var}">
            <div class="row solid-silver-border-bottom">
                <div class="col-xs-4">内容</div>
                <div class="col-xs-4">编辑标题</div>
                <div class="col-xs-4">其它操作</div>
            </div>
            <div class="row" ng-if="!${pageComponent.var}||!${pageComponent.var}.items||!${pageComponent.var}.items.length">
                <div class="col-xs-12"><h5>该轮播方案没有任何轮播内容，点击上方 “插入一条”按钮添加轮播内容</h5></div>
            </div>
            <div ng-init="getCarouselImages()" class="row" ng-repeat="carouselItem in ${pageComponent.var}.items track by $index">
                <div class="row p-t-10">
                    <div class="btn-group col-xs-12">
                        <button type="button" class="btn btn-secondary btn-sm">更换图片</button>
                        <button type="button" class="btn btn-secondary dropdown-toggle btn-sm" data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu bg-light-grey">
                            <span ng-repeat="icon in carouselImages" class="dropdown-item-inline"
                                  ng-click="carouselItem.type='image';carouselItem.value=icon">
                                <img type="text" ng-src="{{icon}}" class="img-ico-larger img-rounded"/>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row p-b-10 solid-silver-border-bottom">
                    <div class="col-xs-4">
                        <img ng-if="carouselItem.type==='image'" class="center-block full-width"
                             ng-src="{{carouselItem.value}}"/>

                    </div>
                    <div class="col-xs-4">
                        <div ng-if="carouselItem.carouselCaption">
                            标题效果：
                            <a ng-if="carouselItem.carouselCaption.type &&carouselItem.carouselCaption.type==='link'"
                               ng-href="#"
                               class="btn btn-primary white-link">{{carouselItem.carouselCaption.text}} <i
                                    class="fa fa-chevron-right right"></i></a>
                        </div>
                        <div ng-if="carouselItem.carouselCaption.type==='text'">文字效果请在上方预览</div>
                        <div class="input-group">
                            <span class="input-group-addon" style="width: 4em">类型</span>
                            <select ng-model="carouselItem.carouselCaption.type" class="form-control">
                                <option value="link">链接</option>
                                <option value="text">文字</option>
                            </select>
                        </div>
                        <div class="input-group" ng-if="carouselItem.carouselCaption.type==='link'">
                            <span class="input-group-addon" style="width: 4em">URI</span>
                            <input class="form-control"  type="text"
                                   ng-model="carouselItem.carouselCaption.value">
                        <span class="input-group-addon">
                            <a class="black-link fa fa-question-circle" target="_blank" href="/admin/article/list"> </a>
                        </span>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" style="width: 4em">文字</span>
                            <input class="form-control" type="text"
                                   ng-model="carouselItem.carouselCaption.text">
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="btn-group btn-group-sm">
                            <button class="btn white-link fa fa-trash " ng-class="{'btn-primary':carouselItem.id,'btn-primary':!carouselItem.id}"
                                    ng-click="remove${pageComponent.varU}Item($index)"> 删除此条
                            </button>

                            <button class="btn btn-primary white-link fa fa-arrow-up"
                                    ng-click="forward${pageComponent.varU}Item($index)" ng-if="$index!==0"> 前移
                            </button>
                            <button class="btn btn-primary white-link fa fa-arrow-down"
                                    ng-click="backward${pageComponent.varU}Item($index)"
                                    ng-if="$index!==${pageComponent.var}.items.length-1"> 后移
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</c:if>
