<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row  m-l-r-0 m-t-2-4em m-b-1-6em md-up-p-l-r-4em" ng-init="_active=0">
    <div class="col-xs-12 col-md-9 p-a-0 m-a-0">
        <span class="col-xs-12 col-md-4 col-lg-3 col-xl-2 p-r-0">
            <h4 class="md-down-no-padding-left large-180" >${pageComponent.data.text}</h4>
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
