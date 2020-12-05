<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<head>
	
	<title>Customer Registration</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel='icon' href='${root}/resources/images/favicon.ico' type='image/x-icon' sizes="16x16" />

	<!-- Bootstrap core CSS -->
	<link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${root}/resources/css/form.css" rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark static-top">
	<a class="navbar-brand" href="#">Luke's Books</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
</nav>

<main role="main" class="container">
    <!-- Registration Form -->
	<form:form
		action="${root}/register/processRegistrationForm"
		modelAttribute="customer" class="form-horizontal">

		<!-- Place for messages: error, alert etc ... -->
		<div class="form-group">
			<div class="col-xs-15">
				<div>

					<!-- Check for registration error -->
					<c:if test="${registrationError != null}">

						<div class="alert alert-danger col-xs-offset-1 col-xs-10">
							${registrationError}</div>

					</c:if>

				</div>
			</div>

		</div>

		<div class="form-row">
			<!-- User name -->
			<div class="form-group col-md-4">
				<label for="username">Username</label>
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span>
				<form:errors path="username" cssClass="isa_error" />
				<form:input path="username" class="form-control" />
			</div>

			<!-- Password -->
			<div class="form-group col-md-4">
				<label for="password">Password</label>
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-lock"></i></span>
				<form:errors path="password" cssClass="isa_error" />
				<form:password path="password" class="form-control"
							   autocomplete="new-password"/>
			</div>

			<!-- Confirm Password -->
			<div class="form-group col-md-4">
				<label for="matchingPassword">Confirm Password</label>
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-lock"></i></span>
				<form:errors path="matchingPassword" cssClass="isa_error" />
				<form:password path="matchingPassword" class="form-control" />
			</div>
		</div>

		<div class="form-row">
			<!-- First name -->
			<div class="form-group col-md-6">
				<label for="firstName">First Name</label>
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span>
				<form:errors path="firstName" cssClass="isa_error" />
				<form:input path="firstName" class="form-control" />
			</div>

			<!-- Last name -->
			<div class="form-group col-md-6">
				<label for="lastName">Last Name</label>
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span>
				<form:errors path="lastName" cssClass="isa_error" />
				<form:input path="lastName" class="form-control" />
			</div>
		</div>

		<!-- Email -->
		<div class="form-group">
			<label for="email">Email</label>
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="email" cssClass="isa_error" />
			<form:input path="email" class="form-control" />
		</div>

		<!-- Address Line 1 -->
		<div class="form-group">
			<label for="addressLine1">Address Line 1</label>
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="addressLine1" cssClass="isa_error" />
			<form:input path="addressLine1" class="form-control" />
		</div>

		<!-- Address Line 2 -->
		<div class="form-group">
			<label for="addressLine2">Address Line 2</label>
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="addressLine2" cssClass="isa_error" />
			<form:input path="addressLine2" class="form-control" />
		</div>

		<!-- City -->
		<div class="form-group">
			<label for="city">City</label>
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="city" cssClass="isa_error" />
			<form:input path="city" class="form-control" />
		</div>

		<!-- Country -->
		<div class="form-group">
			<label for="country">Country/State</label>
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="country" cssClass="isa_error" />
			<form:input path="country" class="form-control" />
		</div>

		<!-- Post Code -->
		<div class="form-group">
			<label for="postCode">Post Code</label>
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="postCode" cssClass="isa_error" />
			<form:input path="postCode" class="form-control" />
		</div>

		<!-- Phone Number -->
		<div class="form-group">
			<label for="phoneNumber">Phone Number</label>
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="phoneNumber" cssClass="isa_error" />
			<form:input path="phoneNumber" class="form-control" />
		</div>

		<!-- Register Button -->
		<div style="margin-top: 10px" class="form-group">
			<div class="col-sm-6 controls">
				<button type="submit" class="btn btn-primary">Register</button>
				<a href=${root} id="cancel" name="cancel" class="btn btn-default">Cancel</a>

			</div>
		</div>


	</form:form>
</main>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.0/assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://getbootstrap.com/docs/4.0/dist/js/bootstrap.min.js"></script>
</body>
</html>