<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<title>Edit Employee</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel='icon' href='${root}/resources/images/favicon.ico' type='image/x-icon' sizes="16x16" />
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	
</head>

<body>

	<h2>Edit Production</h2>
	
	<form:form action="saveEmployee" modelAttribute="employee" methoe="POST">
	
		<!-- associate data with employee id -->
		<form:hidden path="id"/>
		<form:hidden path="password"/>
		<form:hidden path="matchingPassword"/>
		
		
		<table>
			<tbody>
				<tr>
					<td><label>Username:</label></td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td><label>First Name:</label></td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td><label>Last Name:</label></td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td><label>Email:</label></td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td><label>Department:</label></td>
					<td><form:input path="department" /></td>
				</tr>
				<tr>
					<td><label>Title:</label></td>
					<td><form:input path="title" /></td>
				</tr>
				<tr>
					<td><label>Admin:</label></td>
					<td><form:checkbox path="isAdmin"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>
			</tbody>
		</table>
	
	</form:form>
	
	<a href=${pageContext.request.contextPath}/employee/list>Back to List</a>
	
	
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