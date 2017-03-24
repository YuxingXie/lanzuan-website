<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.mode eq 'site'}">
    <div class="row m-l-r-0 ">
        <c:if test="${not empty pageComponent.data.image.uri}">
            <img class="full-width img-responsive" ng-src="${pageComponent.data.image.uri}" style="max-height: 327px;"/>
        </c:if>
    </div>
</c:if>
<c:if test="${param.mode eq 'admin' or param.mode eq 'prev'}">
    <div class="row m-l-r-0 " ng-init="get${pageComponent.varU}()">
        <img class="full-width img-responsive" ng-src="{{${pageComponent.var}.image.uri}}" style="max-height: 327px;"
             ng-if="fullWidthImage &&fullWidthImage.image &&fullWidthImage.image.uri"/>
    </div>
</c:if>
<c:if test="${param.mode eq 'admin'}">
    <div class="row p-a-0 m-a-0">
        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑图文块组组件方案</label>

            <div class="btn-group p-b-10">
                    <%--<label class="btn btn-info cursor-auto">当前方案：{{imageTextBlockGroup.name}}</label>--%>

                <button class="btn btn-primary fa fa-save " type="button" ng-click="save${pageComponent.varU}()">应用该图</button>
                    <%--<button class="btn btn-primary fa fa-copy" type="button" ng-click="newImageTextBlockGroup()"> 方案另存为</button>--%>
                    <%--<a class="btn btn-primary fa fa-gears white-link"--%>
                    <%--ng-href="${path}/admin/image-text-block-group/list-page/${pageComponent.id}"> 应用其它方案</a>--%>
                    <%--<button class="btn btn-primary fa fa-refresh" type="button" ng-click="resetImageTextBlockGroup()"> 重 置</button>--%>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-warning"></i>所有图片都会被拉伸成同样的高度和宽度，为了图片不变形，请保持相同的高宽比例；</li>
                    <li><i class="fa fa-warning"></i>如果没有合适的图标，您可以先<a href="${pageComponent.materialUploadUri}/${pageComponent.id}"
                                                                      style="text-decoration: underline;"><i>上传素材</i></a>
                    </li>
                        <%--<li><i class="fa fa-graduation-cap"></i> font-awesome类可参考--%>
                        <%--<a href="http://fontawesome.io/icons/" target="_blank" class="green-link">http://fontawesome.io/icons/</a>，图标前加"fa-"前缀即可;</li>--%>
                </ul>

            </div>
        </div>




    </div>
    <div class="row solid-silver-border p-a-md hover-bg-color-grey" ng-init="getFullWidthImages()" >

        <div class="row">

            <div class="btn-group col-xs-12 p-t-1-5em ">
                <button type="button" class="btn btn-secondary btn-sm">更换图片</button>
                <button type="button" class="btn btn-secondary dropdown-toggle btn-sm" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu dropdown-full-width">
                    <span ng-repeat="icon in fullWidthImages" class="dropdown-item-inline"
                          ng-click="${pageComponent.var}.image.uri=icon">
                        <img type="text" ng-src="{{icon}}" class="img-320-240px img-responsive"/>
                    </span>
                </div>

            </div>
            <div class="col-xs-2">
                <img ng-src="{{${pageComponent.var}.image.uri}}" class="img-responsive img-ico-larger img-rounded"/>
            </div>
            <div class="col-xs-4">

            </div>
            <div class="col-xs-4">

            </div>
        </div>
    </div>
</c:if>

