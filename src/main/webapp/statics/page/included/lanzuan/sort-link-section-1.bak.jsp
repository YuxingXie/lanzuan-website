<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.mode eq 'site' and false}">
    <div class="row p-t-4em p-b-4em m-l-0 m-r-0 md-up-p-l-r-4em bg-very-light">
        <c:forEach items="${pageComponent.data.items}" var="sortLink">
            <div class="col-xs-6 col-md-2 text-center dash-silver-right-border height-10em padding-left-2em">
                <h6 class="color-blue text-left">${sortLink.sortName}</h6>
                <div class="small-90 m-t-1em">
                    <ul class="list-unstyled grey-link">
                        <c:forEach var="link" items="${sortLink.links}">
                            <c:if test="${not empty sortLink.links and empty sortLink.image}">
                                <li class="text-left"><a ng-href="${link.href}">${link.text}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${not empty sortLink.image and empty sortLink.links}">
                            <img ng-src="${sortLink.image}" class="img-responsive"/>
                        </c:if>
                    </ul>
                </div>
            </div>
        </c:forEach>

        <div class="col-xs-6 col-md-4 text-center height-10em  padding-left-2em">
            <h6 class="color-blue text-center">关注我们</h6>
            <div class="row m-t-1em">
                <div class="col-md-5 hidden-md-down"></div>
                <a href="#" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <!--<img ng-src="ewm.jpg" class="hidden inline-block" />-->
                    <img title="微信" ng-src="/statics/image/lanzuan/icons/foll1.gif" class="full-width"/>
                </a>
                <a href="http://weibo.com/sangfor" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <img title="微博" ng-src="/statics/image/lanzuan/icons/foll2.gif"  class="full-width"/>
                </a>
                <a href="" target="_blank"  class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <img title="社区" ng-src="/statics/image/lanzuan/icons/foll3.gif" class="full-width"/>
                </a>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${param.mode eq 'admin'or param.mode eq 'prev' or param.mode eq 'site'}">
    <div class="row p-t-4em p-b-4em m-l-0 m-r-0 md-up-p-l-r-4em bg-very-light " ng-init="get${pageComponent.varU}()">
        <div class="col-xs-6 col-md-2 text-center dash-silver-right-border height-10em padding-left-2em" ng-repeat="sortLink in ${pageComponent.var}.items">
            <h6 class="color-blue text-left">{{sortLink.sortName}}</h6>
            <div class="small-90 m-t-1em">
                <ul class="list-unstyled grey-link">
                    <li ng-repeat="link in sortLink.links" class="text-left" ng-if="sortLink.links&&!sortLink.image"><a ng-href="{{link.href}}">{{link.text}}</a></li>
                    <img ng-src="{{sortLink.image}}" ng-if="sortLink.image" class="img-responsive"/>
                </ul>
            </div>
        </div>
        <div class="col-xs-6 col-md-4 text-center height-10em  padding-left-2em">
            <h6 class="color-blue text-center">关注我们</h6>
            <div class="row m-t-1em">
                <div class="col-md-5 hidden-md-down"></div>
                <a href="#" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <!--<img ng-src="ewm.jpg" class="hidden inline-block" />-->
                    <img title="微信" ng-src="/statics/image/lanzuan/icons/foll1.gif" class="full-width"/>
                </a>
                <a href="http://weibo.com/sangfor" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <img title="微博" ng-src="/statics/image/lanzuan/icons/foll2.gif"  class="full-width"/>
                </a>
                <a href="" target="_blank"  class="col-xs-4 col-md-1 padding-1 m-a-0">
                    <img title="社区" ng-src="/statics/image/lanzuan/icons/foll3.gif" class="full-width"/>
                </a>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${param.mode eq 'admin' and false}">
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
