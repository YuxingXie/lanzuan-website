<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row p-a-0 m-a-0">
        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑文章块</label>
            <label class="label label-info large-180">{{${param.varU}.name}}</label>
            <div class="btn-group padding-bottom-10">
                <button class="btn btn-primary fa fa-plus" type="button" ng-click="insert${param.varU}Item()"> 新增文章块</button>
                <button class="btn btn-primary fa fa-floppy-o" type="button" ng-click="save${param.varU}()"> 保存修改</button>
                <button class="btn btn-primary fa fa-copy" type="button" ng-click="new${param.varU}()">方案另存为</button>
                <a class="btn btn-primary fa fa-gears white-link" ng-href="${path}/admin/sort-link-group/list-page/${pageComponent.id}"> 应用方案</a>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="get${param.varU}('${param.dataUri}')"> 重 置</button>
                <button class="btn btn-danger fa fa-floppy-o" type="button" ng-if="addArticleSection&&!addArticleSectionSaved" ng-click="save${param.varU}()">保存</button>
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
</div>

<div class="row" ng-init="getIcons()">
        <div class="col-xs-8">文章操作</div>
        <div class="col-xs-4">块操作</div>
    </div>
<div class="row p-t-md p-b-md small-90 hover-bg-color-dark-grey"   ng-repeat="sortLink in ${param.var}.${param.var}Items" ng-if="${param.var}">
    <div class="col-xs-12" ng-if="!sortLink.links &&!sortLink.image">
        <span class="">该块无内容</span>
        <div class="row padding-top-10">
            <div class="btn-group col-xs-12">
                <button class="btn btn-primary btn-sm" ng-click="sortLink.links[0].text='文章标题...'">添加文章</button>
                <button type="button" class="btn btn-primary btn-sm">添加图片</button>
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
           </div>
           <div class="col-xs-5 btn-group btn-group-sm">

               <button class="btn btn-primary  fa fa-trash " ng-if="!sortLink.image" ng-click="sortLink.links.splice($index,1)">删除文章</button>


           </div>

       </div>


    </div>
    <div class="col-xs-4">
        <div class="btn-group btn-group-sm">

            <button class="btn btn-primary white-link fa fa-trash" ng-click="remove${param.varU}Item($index)"> 删除该块</button>
            <button class="btn btn-primary" ng-if="(!sortLink.links&&!sortLink.image)||sortLink.links" ng-click="sortLink.links.splice(0,0,{'text':'一篇文章'})">增加文章</button>
            <button class="btn btn-primary white-link fa fa-angle-up" ng-click="forward${param.varU}Item($index)" ng-if="$index!==0">前移</button>
            <button class="btn btn-primary white-link fa fa-angle-down" ng-click="backward${param.varU}Item($index)" ng-if="${param.var}.${param.var}Items.length-1!==$index">后移</button>
        </div>
        <div class="input-group input-group-sm margin-top-10">
            <span class="input-group-addon">重命名</span>
            <input type="text" ng-model="sortLink.sortName" class="form-control">
        </div>






    </div>
</div>
