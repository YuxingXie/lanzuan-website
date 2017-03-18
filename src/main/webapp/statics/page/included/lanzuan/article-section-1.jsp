<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row m-l-0 m-r-0 m-t-1em md-up-p-l-r-4em p-t-4em p-b-2em  bg-very-light" >
    <c:forEach varStatus="index" items="${pageComponent.data.items}" var="sortLink">
        <div class="col-xs-12 col-md-4">
            <div class="row">
                <h4 class="col-xs-8 large-180">${sortLink.sortName}</h4>

                <c:if test="${not empty sortLink.image}">
                    <a target="_blank" href="${sortLink.imageHref}"><img src="${sortLink.image}" class="col-xs-12 p-t-1em" /></a>
                </c:if>
                <c:if test="${not empty sortLink.links}">
                    <a href="#" class="col-xs-4 green-link">
                        <span class="large-110">More</span>
                            <span class="fa-stack">
                                <i class="fa fa-circle fa-stack-1x"></i><i
                                    class="fa fa-angle-right fa-stack-1x fa-inverse padding-left-2"></i>
                            </span>
                    </a>
                </c:if>
            </div>
            <c:choose>
                <c:when test="${not empty sortLink.links}">
                    <div class="list-group small-90 p-r-3em">
                        <c:forEach var="link" items="${sortLink.links}">
                            <a href="${link.href}" class="list-group-item p-l-0 p-r-0 no-border no-background fa fa-ext-dot-blue">
                            <span class="color-grey bg-medium-grey-2 label-pill pull-right" style="border-radius: .2rem;background-color: #efefefef">${link.date}
                            </span>${link.text}
                            </a>
                        </c:forEach>

                    </div>
                </c:when>
                <c:when test="${empty sortLink.links and empty sortLink.image}">
                    <div class="list-group small-90 p-r-3em">
                        <c:if test="${empty sortLink.links}"></c:if>
                        <span class="list-group-item p-l-0 p-r-0 text-center p-t-2em text-primary dash-silver-border">暂无文章</span>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </c:forEach>

</div>