<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div>--%>
<%--<c:forEach var="item" items="${pageComponent.data.items}" varStatus="index">${item.value}</c:forEach>--%>
<%--</div>--%>
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