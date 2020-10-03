<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel='icon' href='${root}/resources/images/favicon.ico' type='image/x-icon' sizes="16x16" />

	
	<title>Employee Menu</title>

	<!-- Bootstrap core CSS -->
	<link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/resources/css/content-template.css" rel="stylesheet">
</head>

<body>
<jsp:include page="employee-navbar.jsp"/>

<main role="main" class="container">
<div id="container">
	
		<div id="content">
	
	<p>
	Welcome to the Bookstore Employee Menu
	</p>
	<p>
	<input type="button" value="Books"
		   onclick="window.location.href='product/list'; return false;"
		   class="add-button"
	/>
	</p>
	
	<p>
	<input type="button" value="Orders"
		   onclick="window.location.href='order/list'; return false;"
		   class="add-button"
	/>
	</p>
	
	<p>
	<input type="button" value="Customers"
		   onclick="window.location.href='customer/list'; return false;"
		   class="add-button"
	/>
	</p>
	
	<!-- Admin Only -->
	<security:authorize access="hasRole('ADMIN')">
	<p>
		<input type="button" value="Employees"
			   onclick="window.location.href='list'; return false;"
			   class="add-button" />
	</p>
	</security:authorize>

	<hr>
	
	</div>
	
	</div>
	
	<!-- Add a logout button -->
	<p>
	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="Logout" />
	
	</form:form>
	</p>
</main>
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
</body>
</html>