<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.mode eq 'site'}">
    <nav class="navbar navbar-md-down-fix-bottom p-t-1-5em p-b-1em" role="navigation">
        <div class="navbar-brand col-md-2 hidden-md-down m-r-6-5em m-l-2em">

            <c:if test="${not empty pageComponent.data.navbarBrand and pageComponent.data.navbarBrand.type eq 'image'}">
                <img src="${pageComponent.data.navbarBrand.value}" class="img-responsive img-ico-larger m-l-5em">
            </c:if>

        </div>
        <c:if test="${not empty pageComponent.data.navbarBrand and pageComponent.data.navbarBrand.type eq 'text'}">
            <a class="m-l-5em">{${pageComponent.data.navbarBrand.value}</a>
        </c:if>
        <ul class="nav navbar-nav p-t-1em m-a-0 p-l-0 p-l-r-4em">
            <c:forEach var="item" items="${pageComponent.data.items}">
                <li class="nav-item md-p-l-r-1-5em lg-p-l-r-2em p-t-10 text-center">
                    <a class="nav-link" ng-href="${item.link}">
                        <i class="fa ${item.faClass} fa-2x hidden-md-up"></i>
                            ${item.name}
                    </a>
                </li>
            </c:forEach>

        </ul>
    </nav>
</c:if>
<c:if test="${param.mode eq 'admin'or param.mode eq 'prev'}">
    <nav class="navbar navbar-md-down-fix-bottom p-t-1-5em p-b-1em" role="navigation"
         ng-init="get${pageComponent.varU}()">
        <div class="navbar-brand col-md-2 hidden-md-down m-r-6-5em m-l-2em">
            <img ng-if="${pageComponent.var}.navbarBrand&&${pageComponent.var}.navbarBrand.type&&${pageComponent.var}.navbarBrand.type==='image'"
                 ng-src="{{${pageComponent.var}.navbarBrand.value}}" class="img-responsive img-ico-larger m-l-5em">
            <a ng-if="${pageComponent.var}.navbarBrand&&${pageComponent.var}.navbarBrand.type&&${pageComponent.var}.navbarBrand.type==='text'"class="m-l-5em">{{${pageComponent.var}.navbarBrand.value}}</a>
        </div>
        <ul class="nav navbar-nav p-t-1em m-a-0 p-l-0 p-l-r-4em">
            <li class="nav-item md-p-l-r-1-5em lg-p-l-r-2em p-t-10 text-center" ng-repeat="navItem in ${pageComponent.var}.items">
                <a class="nav-link" ng-href="{{navItem.link}}">
                    <i class="fa {{navItem.faClass}} fa-2x hidden-md-up"></i>
                    {{navItem.name}}
                </a>
            </li>
            <li class="nav-item"></li>
        </ul>
    </nav>
</c:if>
<c:if test="${param.mode eq 'admin'}">
    <div class="row p-a-0 m-a-0" ng-init="getIcons()">

        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑导航条</label>
            <div class="btn-group p-b-10">
                <label class="btn btn-info cursor-auto">当前方案：{{${pageComponent.var}.name}}</label>

                <button class="btn btn-danger fa fa-save " type="button" ng-click="save${pageComponent.varU}()" >保存</button>
                <button class="btn btn-primary fa fa-copy" type="button" ng-click="new${pageComponent.varU}()" > 方案另存为</button>
                <a class="btn btn-primary fa fa-download white-link" ng-href="${path}${pageComponent.listOperationUri}${pageComponent.id}"> 应用其它方案</a>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="reset${pageComponent.varU}()"> 重 置</button>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-warning"></i> 如果没有合适的图标，您可以先<a href="${pageComponent.materialUploadUri}/${pageComponent.id}"
                                                                       style="text-decoration: underline;"><i>上传素材</i></a>；</li>
                    <li><i class="fa fa-warning"></i> “另存方案”后，如果想应用该方案，可点击“应用其它方案”；</li>
                    <li><i class="fa fa-warning"></i> 修改导航项名称，链接，更换图标以及“前面插入一条”、“删除词条”仅在客户端修改，点击上方的“保存”按钮才会保存修改。</li>
                </ul>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="btn-group full-width">
                <button type="button" class="btn btn-secondary btn-sm">更换图标</button>
                <button type="button" class="btn btn-secondary dropdown-toggle btn-sm" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu dropdown-full-width">
                <span ng-repeat="icon in icons" class="dropdown-item-inline" ng-click="${pageComponent.var}.navbarBrand.value=icon">
                    <img type="text" ng-src="{{icon}}" class="img-ico-lg img-rounded"/>
                </span>
                </div>

            </div>
        </div>
        <table class="table table-responsive table-hover">
            <tr>
                <th >导航项名称</th>
                <th >链接</th>
                <th>其它操作</th>
            </tr>
            <tr ng-if="!${pageComponent.var}"><td colspan="3">没有方案可用或者方案已被禁用，请点击上方“应用其它方案”查看。</td></tr>
            <tr ng-repeat="navItem in ${pageComponent.var}.items">
                <td>
                    <input type="text" ng-model="navItem.name" class="form-control">
                </td>
                <td>
                    <input type="text" ng-model="navItem.link" class="form-control" >
                </td>

                <td>
                    <div class="btn-group">
                        <button class="btn btn-primary btn-sm  fa fa-plus" ng-click="insert${pageComponent.varU}ItemBefore($index)"> 前面插入一条</button>
                        <button class="btn btn-primary btn-sm  fa fa-trash" ng-click="remove${pageComponent.varU}Item($index)"> 删掉此条</button>
                    </div>
                </td>
            </tr>
        </table>

    </div>
</c:if>

