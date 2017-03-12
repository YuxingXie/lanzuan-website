<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath() %>"/>
<c:if test="${path eq '/'}"><c:set var="path" value=""/></c:if>
<div class="row p-a-0 m-a-0">
    <form ng-submit="" class="col-xs-12 p-a-0 m-a-0" name="form">

        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑导航条</label>
            <div class="btn-group padding-bottom-10">
                <label class="btn btn-info cursor-auto">方案名称：{{navbar.name}}</label>

                <button class="btn btn-primary fa fa-upload " type="button" ng-click="saveNavbar()" > 保存</button>
                <button class="btn btn-primary fa fa-save" type="button" ng-click="newNavbar()" > 方案另存为</button>
                <a class="btn btn-primary fa fa-save white-link" ng-href="${path}/admin/navbar/list-page/${pageComponent.id}"> 应用其它方案</a>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="resetNavbar(form)"> 重 置</button>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-warning"></i> “另存方案”后，如果想应用该方案，可点击“应用其它方案”；</li>
                    <li><i class="fa fa-warning"></i> 修改导航项名称，链接，更换图标以及“前面插入一条”、“删除词条”仅在客户端修改，点击上方的“保存”按钮才会保存修改。;</li>
                    <%--<li><i class="fa fa-graduation-cap"></i> font-awesome类可参考--%>
                    <%--<a href="http://fontawesome.io/icons/" target="_blank" class="green-link">http://fontawesome.io/icons/</a>，图标前加"fa-"前缀即可;</li>--%>
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
                    <span ng-repeat="icon in icons" class="dropdown-item-inline" ng-click="navbar.navbarBrand.value=icon">
                        <img type="text" ng-src="{{icon}}" class="img-ico-lg img-rounded"/>
                    </span>
                </div>
                如果没有合适的图标，您可以先<a href="/admin/icon/upload-input/${pageComponent.id}" style="text-decoration: underline;"><i>上传素材</i></a>
            </div>
        </div>
        <table class="table table-responsive table-hover">
            <tr>
                <th >导航项名称</th>
                <th >链接</th>
                <%--<th >bootstrap类</th>--%>
                <%--<th >font-awesome类</th>--%>
                <th>其它操作</th>
            </tr>
            <tr ng-repeat="navItem in navbar.navItems">
                <td>
                    <input type="text" ng-model="navItem.name" class="form-control">
                </td>
                <td>
                    <input type="text" ng-model="navItem.link" class="form-control" >
                </td>
                <%--<td>--%>
                <%--<input type="text" ng-model="navItem.navItemCass" class="form-control">--%>
                <%--</td>--%>
                <%--<td>--%>
                <%--<input type="text" ng-model="navItem.faClass" class="form-control">--%>
                <%--</td>--%>
                <td>
                    <div class="btn-group">
                        <button class="btn btn-primary btn-sm  fa fa-plus" ng-click="insertNavItemsBefore($index)"> 前面插入一条</button>
                        <button class="btn btn-primary btn-sm  fa fa-trash" ng-click="removeNavItems($index)"> 删掉此条</button>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>