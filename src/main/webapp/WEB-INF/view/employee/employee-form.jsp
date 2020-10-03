<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<title>Add Employee</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel='icon' href='${root}/resources/images/favicon.ico' type='image/x-icon' sizes="16x16" />
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	
</head>

<body>
<jsp:include page="employee-navbar.jsp"/>

<main role="main" class="container">

	<h2>Add Employee</h2>
	
	<form:form action="saveEmployee" modelAttribute="employee" methoe="POST">
	
		<!-- associate data with employee id -->
		<form:hidden path="id"/>
		
		<div class="form-group">
			<label for="username">Username:</label>
			<form:input path="username" class="form-control" id="username"/>
		</div>
		<div class="form-group">
			<label for="password">Password:</label>
			<form:errors path="password" cssClass="error" />
			<form:password path="password" placeholder="password (*)"
						   class="form-control" id="password" autocomplete="new-password"/>
		</div>
		<div class="form-group">
			<label for="matchingPassword">Matching Password:</label>
			<form:errors path="matchingPassword" cssClass="error" />
			<form:password path="matchingPassword" placeholder="confirm password (*)"
						   class="form-control" id="matchingPassword"/>
		</div>
		<div class="form-firstName">
			<label for="firstName">First Name:</label>
			<form:input path="firstName" class="form-control" id="firstName"/>
		</div>
		<div class="form-group">
			<label for="lastName">Last Name:</label>
			<form:input path="lastName" class="form-control" id="lastName"/>
		</div>
		<div class="form-group">
			<label for="email">Email:</label>
			<form:input path="email" class="form-control" id="email"/>
		</div>
		<div class="form-group">
			<label for="department">Department:</label>
			<form:input path="department" class="form-control" id="department"/>
		</div>
		<div class="form-group">
			<label for="title">Title:</label>
			<form:input path="title" class="form-control" id="title"/>
		</div>
		<div class="form-check">
			<form:checkbox path="isAdmin" class="form-check-input" id="isAdmin"/>
			<label for="isAdmin">Admin</label>
		</div>
		<button class="btn btn-primary" type="submit">Save</button>
		<button class="btn btn-primary" type="button"
				onClick="window.location.href='${pageContext.request.contextPath}/employee/list'">
			Cancel
		</button>
	
	</form:form>
</main>
	
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>
</html>