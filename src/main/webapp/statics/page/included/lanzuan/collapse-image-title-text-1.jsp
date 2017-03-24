<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${param.mode eq 'site'}">
    <div class="row  m-l-r-0 m-t-2-4em m-b-1-6em md-up-p-l-r-4em" ng-init="_active=0">
        <div class="col-xs-12 col-md-9 p-a-0 m-a-0">
        <span class="col-xs-12 col-md-4 col-lg-3 col-xl-2 p-r-0">
            <h4 class="md-down-no-padding-left large-110  xl-large-180 lg-large-165 md-large-150 sm-large-135" >${pageComponent.data.text}</h4>
        </span>
        <span class="col-xs-12 col-md-8 col-lg-9 col-xl-10 text-left m-a-0">

            <c:forEach var="block" items="${pageComponent.data.items}" varStatus="index">

                <span  ng-mouseover="_active=${index.index}" class="btn btn-padding-little bg-none sm-down-btn" ng-class="{'active bg-light-blue color-white':_active===${index.index}}">
                        ${block.name}
                </span>

                <c:if test="${pageComponent.data.items.size()!=index.index+1}">
                    <span class="divider hidden-sm-down"></span>
                </c:if>
            </c:forEach>
        </span>
        </div>
    </div>
    <c:forEach var="block" items="${pageComponent.data.items}" varStatus="index">
        <div class="row m-l-r-0 md-up-p-l-r-4em m-b-2em" ng-class="{'active in':_active===${index.index},'collapse':_active!==${index.index}}">
            <c:forEach var="imageTextItem" items="${block.imageTextItems}" varStatus="index_in">
                <div class="col-xs-6 col-md-3 md-down-p-l-r-0" ng-if="true">
                    <a href="${imageTextItem.link}" class="with-text-img" ng-mouseover="show=true" ng-mouseleave="show=false">
                        <img class="md-down-img-thumbnail full-container img-responsive" ng-class="{'dark-07':show}" src="${imageTextItem.image}"/>
                        <div class="absolute-center small-90 hidden-md-down" ng-class="{'block':show,'hidden':!show}">
                            <div class="text-indent-1em">${imageTextItem.text}</div>
                            <button class="btn btn-primary m-t-05em btn-padding-little" href="${imageTextItem.link}">了解详情</button>
                        </div>
                    </a>
                    <p class="img-title">${imageTextItem.title}</p>
                </div>
            </c:forEach>


        </div>
    </c:forEach>
</c:if>
<c:if test="${param.mode eq 'admin' or param.mode eq 'prev'}">
    <div class="row  m-l-r-0 m-t-2-4em m-b-1-6em md-up-p-l-r-4em " ng-init="get${pageComponent.varU}();_active=0">
        <div class="col-xs-12 col-md-9 p-a-0 m-a-0">
                    <span class="col-xs-12 col-md-4 col-lg-3 col-xl-2 p-r-0">
                        <h4 class="md-down-no-padding-left large-180" >{{${pageComponent.var}.text}}</h4>
                    </span>
                    <span class="col-xs-12 col-md-8 col-lg-9 col-xl-10 text-left m-a-0">

                        <span ng-repeat="block in ${pageComponent.var}.items">
                            <span  ng-mouseover="$parent._active=$index" class="btn btn-padding-little bg-none sm-down-btn" ng-class="{'active bg-light-blue color-white':_active===$index}">
                            {{block.name}}
                            </span>
                            <span ng-if="$index!==${pageComponent.var}.items.length-1" class="divider hidden-sm-down"></span>
                        </span>
                </span>
        </div>

    </div>
    <div class="row m-l-r-0 md-up-p-l-r-4em m-b-2em" ng-repeat="block in ${pageComponent.var}.items"
         ng-class="{'active in':$parent._active===$index,'collapse':$parent._active!==$index}">
        <div class="col-xs-6 col-md-3 md-down-p-l-r-0" ng-repeat="imageTextItem in block.imageTextItems">
            <a ng-href="{{imageTextItem.link}}" class="with-text-img" ng-mouseover="show=true" ng-mouseleave="show=false">
                <img class="md-down-img-thumbnail full-container img-responsive" ng-class="{'dark-07':show}"
                     ng-src="{{imageTextItem.image}}"/>
                <div class="absolute-center small-90 hidden-md-down" ng-class="{'block':show,'hidden':!show}">
                    <div class="text-indent-1em">{{imageTextItem.text}}</div>
                    <button class="btn btn-primary m-t-05em btn-padding-little" ng-href="{{imageTextItem.link}}">了解详情</button>
                </div>
            </a>

            <p class="img-title">{{imageTextItem.title}}</p>
        </div>

    </div>
</c:if>
<c:if test="${param.mode eq 'admin' and false}">
    <div class="row p-a-0 m-a-0">
        <div class="col-xs-12 m-a-0 p-a-0">
            <label class="label label-default large-180">编辑图文块组组件方案</label>


            <div class="btn-group p-b-10">
                <label class="btn btn-info cursor-auto">当前方案：{{imageTextBlockGroup.name}}</label>

                <button class="btn btn-danger fa fa-save " type="button" ng-click="save${pageComponent.varU}()"> 保存</button>
                <button class="btn btn-primary fa fa-plus " type="button" ng-click="insert${pageComponent.varU}Item()">插入一块</button>
                <button class="btn btn-primary fa fa-copy" type="button" ng-click="new${pageComponent.varU}()"> 方案另存为</button>
                <a class="btn btn-primary fa fa-gears white-link"
                   ng-href="${path}${pageComponent.listOperationUri}${pageComponent.id}"> 应用方案</a>
                <button class="btn btn-primary fa fa-refresh" type="button" ng-click="reset${pageComponent.varU}()"> 重 置</button>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="alert alert-warning">
                <ul class="list-unstyled">
                    <li><i class="fa fa-warning"></i>所有图片都会被拉伸成同样的高度和宽度，为了图片不变形，请保持相同的高宽比例；</li>
                    <li><i class="fa fa-warning"></i>如果没有合适的图标，您可以先<a href="${pageComponent.materialUploadUri}/${pageComponent.id}"
                                                                      style="text-decoration: underline;"><i>上传素材</i></a>
                    </li>
                    <li><i class="fa fa-warning"></i> 修改卡片名称，链接，更换图片以及“前面插入一条”、“删除此条”仅在客户端修改，点击上方的“保存”按钮才会保存修改。;</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row p-b-md">
        <div class="col-xs-12 m-a-0 p-a-0">
            <div class="input-group">
                <span class="input-group-addon bg-info"> 大 标 题 </span>
                <input type="text" ng-model="${pageComponent.var}.text" class="form-control"/>
            </div>
        </div>
    </div>
    <div class="row solid-silver-border p-a-md hover-bg-color-grey" ng-init="getImageTextBlockGroupImages()" ng-repeat="imageTextBlock in ${pageComponent.var}.items track by $index">
        <div class="row">
            <div class="col-xs-4">
                <div class="input-group">
                    <label class="input-group-addon">图文块名称</label>
                    <input type="text" ng-model="imageTextBlock.name" class="form-control">
                </div>
            </div>
            <div class="col-xs-8">
                <div class="btn-group">
                    <button class="fa fa-trash btn  btn-primary" ng-click="remove${pageComponent.varU}Item($index)" >删除整块</button>
                    <button class="fa fa-caret-up btn btn-primary" ng-click="forward${pageComponent.varU}Item($index)"ng-if="$index!==0">整块前移</button>
                    <button class="fa fa-caret-down btn btn-primary" ng-click="backward${pageComponent.varU}Item($index)" ng-if="$index!==${pageComponent.var}.items.length-1">整块后移</button>

                </div>

            </div>





        </div>
        <div class="row" ng-repeat="imageTextItem in imageTextBlock.imageTextItems">

            <div class="btn-group col-xs-12 p-t-1-5em ">
                <button type="button" class="btn btn-secondary btn-sm">更换图片</button>
                <button type="button" class="btn btn-secondary dropdown-toggle btn-sm" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    <span class="sr-only">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu dropdown-full-width">
                    <span ng-repeat="icon in icons" class="dropdown-item-inline"
                          ng-click="imageTextItem.image=icon">
                        <img type="text" ng-src="{{icon}}" class="img-ico-lg img-rounded"/>
                    </span>
                </div>

            </div>
            <div class="col-xs-2">
                <img ng-src="{{imageTextItem.image}}" class="img-responsive img-ico-larger img-rounded"/>
            </div>
            <div class="col-xs-4">
                <div class="input-group input-group-sm m-b-10">
                    <span class="input-group-addon">文字</span><input type="text" class="form-control" ng-model="imageTextItem.text"/>
                </div>
                <div class="input-group input-group-sm m-b-10">
                    <span class="input-group-addon">链接</span><input type="text" class="form-control" ng-model="imageTextItem.link"/>
                </div>
                <div class="input-group input-group-sm">
                    <span class="input-group-addon">标题</span><input type="text" class="form-control" ng-model="imageTextItem.title"/>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="btn-group">
                    <button class="btn btn-primary btn-sm  fa fa-plus" ng-click="insertBlockItemBefore(imageTextBlock,$index)"> 前面插入一条</button>
                    <button class="btn btn-primary btn-sm  fa fa-trash" ng-click="removeBlockItem(imageTextBlock,$index)"> 删掉此条</button>
                    <button class="btn btn-primary btn-sm  fa fa-angle-up" ng-click="forwardBlockItem(imageTextBlock,$index)" ng-if="$index!==0">前移</button>
                    <button class="btn btn-primary btn-sm  fa fa-angle-down" ng-click="backwardBlockItem(imageTextBlock,$index)" ng-if="$index!==imageTextBlock.imageTextItems.length-1">后移</button>
                </div>
            </div>
        </div>
    </div>
</c:if>

