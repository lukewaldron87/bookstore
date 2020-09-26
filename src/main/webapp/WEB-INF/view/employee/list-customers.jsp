<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<title>List Customers</title>
<meta charset="utf-8">
<meta name="viewport" ontent="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">


</head>

<body>

	<h2>Customers</h2>

	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email Address</th>
		</tr>


		<!-- loop and print the customers -->
		<c:forEach var="tempCustomer" items="${customers}">

			<c:url var="updateLink" value="/employee/customer/showFormForUpdate">
				<c:param name="userId" value="${tempCustomer.id}" />
			</c:url>

			<c:url var="deleteLink" value="/employee/customer/delete">
				<c:param name="userId" value="${tempCustomer.id}" />
			</c:url>

			<tr>
				<td>${tempCustomer.firstName}</td>
				<td>${tempCustomer.lastName}</td>
				<td>${tempCustomer.email}</td>

				<!-- cange to link in name for edit -->
				<td><a href="${updateLink}">Update</a> <a href="${deleteLink}"
					onclick="return confirm('Are you sure you want to delete this customer?');">
						Delete </a></td>

			</tr>



		</c:forEach>
	</table>

	<!--<input type="button" value="Add New Customer"
		onclick="window.location.href='showFormForAdd'; return false;"
		class="add-button" />

	<br> -->


	<a href=${pageContext.request.contextPath}/employee/showEmployeeMenu>Back
		to Menu</a>


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