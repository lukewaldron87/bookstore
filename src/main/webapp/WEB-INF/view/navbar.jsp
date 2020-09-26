<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <a class="nav-link" href="#">Author <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Genre</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="#">Action/Adventure</a>
                    <a class="dropdown-item" href="#">Biography</a>
                    <a class="dropdown-item" href="#">Classics</a>
                    <a class="dropdown-item" href="#">Cookbook</a>
                    <a class="dropdown-item" href="#">Comic Book</a>
                    <a class="dropdown-item" href="#">Thriller</a>
                    <a class="dropdown-item" href="#">Fantasy</a>
                    <a class="dropdown-item" href="#">History</a>
                    <a class="dropdown-item" href="#">Poetry</a>
                    <a class="dropdown-item" href="#">Romance</a>
                    <a class="dropdown-item" href="#">Sci-Fi</a>
                    <a class="dropdown-item" href="#">Self-Help</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <ul class="navbar-nav ml-5">
            <li class="nav-item active">
                <a class="nav-link" href="" data-toggle="modal" data-target="#modalLoginForm">Login <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
</nav>

<!-- Login Modal if error returned do not use fade and show the error dialogue-->
<div
        <c:choose>
            <c:when test="${param.error ne null}">
                class="modal"
            </c:when>
            <c:otherwise>
                class="modal fade"
            </c:otherwise>
        </c:choose>

        id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Sign in</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!-- Login Form -->
            <form:form action="${root}/authenticateUser"
                       method="POST" class="form-horizontal">
                <div class="modal-body mx-3">
                    <c:if test="${param.error ne null}" >
                        <div class="alert alert-danger" role="alert">
                            Incorrect username or password!
                        </div>
                    </c:if>
                    <div class="md-form mb-4">
                        <i class="fas fa-envelope prefix grey-text"></i>
                        <label data-error="wrong" data-success="right" for="defaultForm-email">Username</label>
                        <input type="text" name="username" id="defaultForm-email" class="form-control validate">
                    </div>

                    <div class="md-form mb-4">
                        <i class="fas fa-lock prefix grey-text"></i>
                        <label data-error="wrong" data-success="right" for="defaultForm-pass">Password</label>
                        <input type="password" name="password"  id="defaultForm-pass" class="form-control validate">
                    </div>

                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="submit" class="btn btn-default">Login</button>
                </div>
            </form:form>
            <a href="${root}/register/showRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
        </div>
    </div>
</div>

<!-- Check if error param exists. If it does call the login modal with error flags -->
<script type="text/javascript">
    function loadLoginModal() {
        <c:if test="${param.error ne null}" >
        $("#modalLoginForm").modal('show');
        </c:if>
    }
</script>