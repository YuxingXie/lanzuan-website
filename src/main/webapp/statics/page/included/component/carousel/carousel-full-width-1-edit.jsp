<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<div class="row p-a-0 m-a-0">



        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑轮播图</label>
            <label class="label label-info large-180">方案名称：{{carousel.name}}</label>

            <div class="btn-group padding-bottom-10">
                <button class="btn btn-primary fa fa-floppy-o" type="button" ng-click="saveCarousel()">全部保存</button>
                <button class="btn btn-primary fa fa-plus" type="button" ng-click="insertCarouselItem()">插入一条</button>
                <button class="btn btn-primary fa fa-copy" type="button" ng-click="newCarousel()"> 方案另存</button>
                <%--<button class="btn btn-primary fa fa-gears" type="button" ng-click="useCarousel()"> 组装方案</button>--%>
                <a class="btn btn-primary white-link fa fa-download" ng-href="${path}/admin/carousel/list_page/${pageComponent.id}"> 应用方案</a>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="resetCarousel()"> 重 置</button>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-graduation-cap fa-fw"></i>轮播图不但可以轮播“图”，还可以轮播任何内容；</li>
                    <li><i class="fa fa-graduation-cap fa-fw"></i>点击“插入一条”,“前移”，“后移”按钮后，顶端的预览效果会可能出现异常；</li>
                    <li><i class="fa fa-warning fa-fw"></i>新插入的条目要先保存才可以更换图片；</li>
                    <li><i class="fa fa-warning fa-fw"></i>图片高宽比例不一致导致的，会导致页面跳动，请确保图片使用相同高宽比例。</li>


                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-md-4" ng-if="!carousel">
            <h5>此轮播图组件还没有图片</h5>
        </div>

        <div class="col-xs-12" ng-if="carousel">
            <div class="row">
                <div class="col-xs-6">内容</div>
                <div class="col-xs-3">编辑标题</div>
                <div class="col-xs-3">其它操作</div>
            </div>
            <div class="row" ng-if="!carousel||!carousel.carouselItems||!carousel.carouselItems.length">
                <div class="col-xs-12"><h5>该轮播方案没有任何轮播内容，点击上方 “插入一条”按钮添加轮播内容</h5></div>
            </div>
            <div ng-init="getIcons()" class="row" ng-repeat="carouselItem in carousel.carouselItems track by $index">
                <div class="row">
                    <div class="btn-group col-xs-12">
                        <button type="button" class="btn btn-secondary btn-sm">更换图片</button>
                        <button type="button" class="btn btn-secondary dropdown-toggle btn-sm" data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu dropdown-full-width">
                    <span ng-repeat="icon in icons" class="dropdown-item-inline"
                          ng-click="carouselItem.value=icon">
                        <img type="text" ng-src="{{icon}}" class="img-ico-lg img-rounded"/>
                    </span>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <img ng-if="carouselItem.type==='image'" class="center-block full-width"
                             ng-src="{{carouselItem.value}}"/>

                    </div>
                    <div class="col-xs-3">
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
                            <input class="form-control" style="width: 24em" type="text"
                                   ng-model="carouselItem.carouselCaption.value">
                        <span class="input-group-addon">
                            <a class="black-link fa fa-question-circle" target="_blank" href="/admin/article/list"> </a>
                        </span>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon" style="width: 4em">文字</span>
                            <input class="form-control" style="width: 24em" type="text"
                                   ng-model="carouselItem.carouselCaption.text">
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <div class="btn-group btn-group-sm">
                            <button class="btn btn-primary white-link fa fa-plus"
                                    ng-click="removeCarouselItem($index)"> 删除此条
                            </button>
                            <a class="btn btn-primary white-link " ng-if="carouselItem.id"
                               ng-href="${path}${param.muu}/${pageComponent.id}/{{carouselItem.id}}">应用本地图片</a>
                            <button class="btn btn-primary white-link fa fa-arrow-up"
                                    ng-click="forwardCarouselItem($index)" ng-if="$index!==0"> 前移
                            </button>
                            <button class="btn btn-primary white-link fa fa-arrow-down"
                                    ng-click="backwardCarouselItem($index)"
                                    ng-if="$index!==carousel.carouselItems.length-1"> 后移
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</div>