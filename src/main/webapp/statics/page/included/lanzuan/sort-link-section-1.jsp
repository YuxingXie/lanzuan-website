<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <img src="${sortLink.image}" class="img-responsive"/>
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
                <!--<img src="ewm.jpg" class="hidden inline-block" />-->
                <img title="微信" src="/statics/image/lanzuan/icons/foll1.gif" class="full-width"/>
            </a>
            <a href="http://weibo.com/sangfor" target="_blank" class="col-xs-4 col-md-1 padding-1 m-a-0">
                <img title="微博" src="/statics/image/lanzuan/icons/foll2.gif"  class="full-width"/>
            </a>
            <a href="" target="_blank"  class="col-xs-4 col-md-1 padding-1 m-a-0">
                <img title="社区" src="/statics/image/lanzuan/icons/foll3.gif" class="full-width"/>
            </a>
        </div>
    </div>
</div>