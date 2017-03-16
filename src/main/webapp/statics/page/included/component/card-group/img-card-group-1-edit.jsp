<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<div class="row p-a-0 m-a-0">
    <div class="col-xs-12 m-a-0 p-a-0">
        <label class="label label-default large-180">编辑图文卡片组</label>

        <div class="btn-group padding-bottom-10">
            <label class="btn btn-info cursor-auto">当前方案：{{cardGroup.name}}</label>

            <button class="btn btn-primary fa fa-upload " type="button" ng-click="saveCardGroup()"> 保存</button>
            <button class="btn btn-primary fa fa-save" type="button" ng-click="newCardGroup()"> 方案另存为</button>
            <a class="btn btn-primary fa fa-save white-link"
               ng-href="${path}/admin/card-group/list-page/${pageComponent.id}"> 应用其它方案</a>
            <button class="btn btn-primary fa fa-refresh" type="button" ng-click="resetCardGroup()"> 重 置</button>
        </div>
    </div>
    <div class="col-xs-12">
        <div class="alert alert-warning">
            <ul class="list-unstyled">
                <li><i class="fa fa-warning"></i>所有图片都会被拉伸成同样的高度和宽度，为了图片不变形，请保持相同的高宽比例；</li>
                <li><i class="fa fa-warning"></i>如果没有合适的图标，您可以先<a href="${param.muu}/${pageComponent.id}"
                                                                  style="text-decoration: underline;"><i>上传素材</i></a></li>
                <li><i class="fa fa-warning"></i> 修改卡片名称，链接，更换图片以及“前面插入一条”、“删除此条”仅在客户端修改，点击上方的“保存”按钮才会保存修改。;</li>
                <%--<li><i class="fa fa-graduation-cap"></i> font-awesome类可参考--%>
                <%--<a href="http://fontawesome.io/icons/" target="_blank" class="green-link">http://fontawesome.io/icons/</a>，图标前加"fa-"前缀即可;</li>--%>

            </ul>

        </div>
    </div>

    <div class="row" ng-init="getCardGroupImage()">
        <div class="row">
            <div class="col-xs-2">图片</div>
            <div class="col-xs-4">链接</div>
            <div class="col-xs-2">卡片文字</div>
            <div class="col-xs-4">其它操作</div>
        </div>
        <div class="row" ng-repeat="card in cardGroup.items">
            <div class="row padding-top-10">
                <div class="btn-group col-xs-12">
                    <button type="button" class="btn btn-secondary btn-sm">更换图片</button>
                    <button type="button" class="btn btn-secondary dropdown-toggle btn-sm" data-toggle="dropdown"
                            aria-haspopup="true"
                            aria-expanded="false">
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <div class="dropdown-menu dropdown-full-width">
                    <span ng-repeat="icon in icons" class="dropdown-item-inline"
                          ng-click="card.image=icon">
                        <img type="text" ng-src="{{icon}}" class="img-ico-lg img-rounded"/>
                    </span>
                    </div>

                </div>
            </div>
            <div class="row solid-silver-border-bottom">
                <div class="col-xs-2">
                    <img ng-src="{{card.image}}" class="img-responsive img-ico-larger img-rounded"/>
                </div>
                <div class="col-xs-4">
                    <input type="text" ng-model="card.link" class="form-control">
                </div>
                <div class="col-xs-2">
                    <input type="text" ng-model="card.text" class="form-control">
                </div>

                <div class="col-xs-4">
                    <div class="btn-group">
                        <button class="btn btn-primary btn-sm  fa fa-plus" ng-click="insert${param.varU}ItemBefore($index)"> 前面插入一条
                        </button>
                        <button class="btn btn-primary btn-sm  fa fa-trash" ng-click="remove${param.varU}Item($index)"> 删掉此条</button>
                        <button class="btn btn-primary btn-sm  fa fa-angle-up" ng-click="forward${param.varU}Item($index)" ng-if="$index!==0">前移</button>
                        <button class="btn btn-primary btn-sm  fa fa-angle-down" ng-click="backward${param.varU}Item($index)" ng-if="$index!==${param.var}.items.length-1">后移</button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>