<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/navbar.css" rel="stylesheet">
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="${root}">Luke's Books</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${root}/employee/product/list">Books <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${root}/employee/order/list">Orders <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${root}/employee/customer/list">Customers <span class="sr-only">(current)</span></a>
            </li>
            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item active">
                    <a class="nav-link" href="${root}/employee/list">Employees <span class="sr-only">(current)</span></a>
                </li>
            </security:authorize>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
        </form>
        <ul class="navbar-nav ml-5">
            <li class="nav-item active">
                <a class="nav-link" href="" data-toggle="modal" data-target="#modalLoginForm">Login <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
</nav>

