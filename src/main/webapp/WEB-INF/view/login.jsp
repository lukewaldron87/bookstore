<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<title>Login</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	
</head>

<body>

	<h2>Login</h2>
	
	<!-- Login Form -->
	<form:form action="${pageContext.request.contextPath}/authenticateUser" 
		  method="POST" class="form-horizontal">
	
		<!-- User name -->
		<input type="text" name="username" placeholder="username" class="form-control" >
		
		<!-- Password -->
		<input type="password" name="password" placeholder="password" class="form-control" >
		
		<!-- <div>
			<a href="${pageContext.request.contextPath}/register/showRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
		</div> -->
	
		<button type="submit" class="btn btn-success">Login</button>
	</form:form>
	
	<a href="${pageContext.request.contextPath}/register/showRegistrationForm" class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
	
</body>
</html>