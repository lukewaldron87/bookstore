<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<title>Customer Registration</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
</head>
<body>

	<!-- Registration Form -->
	<form:form
		action="${pageContext.request.contextPath}/register/processRegistrationForm"
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

		<!-- User name -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="username" cssClass="error" />
			<form:input path="username" placeholder="username (*)"
				class="form-control" />
		</div>

		<!-- Password -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-lock"></i></span>
			<form:errors path="password" cssClass="error" />
			<form:password path="password" placeholder="password (*)"
				class="form-control" />
		</div>

		<!-- Confirm Password -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-lock"></i></span>
			<form:errors path="matchingPassword" cssClass="error" />
			<form:password path="matchingPassword"
				placeholder="confirm password (*)" class="form-control" />
		</div>

		<!-- First name -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="firstName" cssClass="error" />
			<form:input path="firstName" placeholder="first name (*)"
				class="form-control" />
		</div>

		<!-- Last name -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="lastName" cssClass="error" />
			<form:input path="lastName" placeholder="last name (*)"
				class="form-control" />
		</div>

		<!-- Email -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="email" cssClass="error" />
			<form:input path="email" placeholder="email (*)" class="form-control" />
		</div>

		<!-- Address Line 1 -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="addressLine1" cssClass="error" />
			<form:input path="addressLine1" placeholder="address Line 1 (*)" class="form-control" />
		</div>

		<!-- Address Line 2 -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="addressLine2" cssClass="error" />
			<form:input path="addressLine2" placeholder="address Line 2 (*)" class="form-control" />
		</div>

		<!-- City -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="city" cssClass="error" />
			<form:input path="city" placeholder="city (*)" class="form-control" />
		</div>

		<!-- Country -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="country" cssClass="error" />
			<form:input path="country" placeholder="country (*)" class="form-control" />
		</div>

		<!-- Post Code -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="postCode" cssClass="error" />
			<form:input path="postCode" placeholder="postCode (*)" class="form-control" />
		</div>

		<!-- Phone Number -->
		<div style="margin-bottom: 25px" class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-user"></i></span>
			<form:errors path="phoneNumber" cssClass="error" />
			<form:input path="phoneNumber" placeholder="phoneNumber (*)" class="form-control" />
		</div>

		<!-- Register Button -->
		<div style="margin-top: 10px" class="form-group">
			<div class="col-sm-6 controls">
				<button type="submit" class="btn btn-primary">Register</button>
			</div>
		</div>


	</form:form>

</body>
</html>