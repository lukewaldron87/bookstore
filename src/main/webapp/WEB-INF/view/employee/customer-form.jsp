<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<title>Edit Customer</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<script type="text/javascript">
		function deleteCustomer(){
			var deleteConfirmation = confirm('Are you sure you want to delete this customer?');
			if( deleteConfirmation == true){
				window.location.href = "${pageContext.request.contextPath}/employee/customer/delete?userId=<c:out value='${customer.id}'/>";
			}
		}
	</script>

</head>

<body>
<jsp:include page="employee-navbar.jsp"/>

<main role="main" class="container">

	<h2>Edit Production</h2>
	
	<form:form action="saveCustomer" modelAttribute="customer" methoe="POST">

		<!-- associate data with customer id -->
		<form:hidden path="id"/>
		<form:hidden path="username"/>
		<form:hidden path="password"/>
		<form:hidden path="matchingPassword"/>
		<form:hidden path="email"/>
		<form:hidden path="phoneNumber"/>

		<div class="form-group">
			<label for="firstName">First Name:</label>
			<form:input path="firstName" class="form-control" id="firstName"/>
		</div>
		<div class="form-group">
			<label for="lastName">Last Name:</label>
			<form:input path="lastName" class="form-control" id="firstName"/>
		</div>
		<div class="form-group">
			<label for="addressLine1">Address Line 1:</label>
			<form:input path="addressLine1" class="form-control" id="firstName"/>
		</div>
		<div class="form-group">
			<label for="addressLine2">Address Line 2:</label>
			<form:input path="addressLine2" class="form-control" id="firstName"/>
		</div>
		<div class="form-group">
			<label for="city">City:</label>
			<form:input path="city" class="form-control" id="firstName"/>
		</div>
		<div class="form-group">
			<label for="country">Country:</label>
			<form:input path="country" class="form-control" id="firstName"/>
		</div>
		<div class="form-group">
			<label for="postCode">Post Code:</label>
			<form:input path="postCode" class="form-control" id="firstName"/>
		</div>
		<button class="btn btn-primary" type="submit">Save</button>
		<button class="btn btn-primary" type="button"
				onClick="window.location.href='${pageContext.request.contextPath}/employee/customer/list'">
			Cancel
		</button>
		<button class="btn btn-primary" type="button" onClick="deleteCustomer()">Delete</button>


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