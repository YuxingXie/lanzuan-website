<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
