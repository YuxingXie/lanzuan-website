<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card-group m-l-r-0">
    <c:forEach items="${pageComponent.data.items}" var="card">
        <div class="card">
            <div class="card-block padding-1">
                <c:if test="${not empty card.link}">
                    <a href="${card.link}">
                        <img src="${card.image}" class="full-width "/>
                        <div class="sticker sticker-bottom p-b-05em large-110">${card.text}</div>
                    </a>
                </c:if>
            </div>
        </div>
    </c:forEach>

</div>