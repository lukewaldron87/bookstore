<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<title>Save Products</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel='icon' href='${root}/resources/images/favicon.ico' type='image/x-icon' sizes="16x16" />
	
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<script type="text/javascript">
		function deleteProduct(){
			var deleteConfirmation = confirm('Are you sure you want to delete this product?');
			if( deleteConfirmation == true){
				window.location.href="${pageContext.request.contextPath}/employee/product/delete?productId=<c:out value='${product.id}'/>";
			}
		}
	</script>
	
</head>

<body>
<jsp:include page="employee-navbar.jsp"/>

<main role="main" class="container">

	<h2>Product Form</h2>
	
	<form:form action="saveProduct" modelAttribute="product" method="POST">
	
		<!-- associate data with product id -->
		<form:hidden path="id"/>
		<div class="form-group">
			<label for="productName">Product Name</label>
			<form:input path="productName" class="form-control" id="productName"/>
		</div>
		<div class="form-group">
			<label for="unitPrice">Unit Price</label>
			<form:input path="unitPrice" class="form-control" id="unitPrice"/>
		</div>
		<div class="form-group">
			<label for="description">Description</label>
			<form:input path="description" class="form-control" id="description"/>
		</div>
		<div class="form-group">
			<label for="unitsInStock">Units in Stock</label>
			<form:input path="unitsInStock" class="form-control" id="unitsInStock"/>
		</div>
		<button class="btn btn-primary" type="submit">Save</button>
		<button class="btn btn-primary" type="button"
				onClick="window.location.href='${pageContext.request.contextPath}/employee/product/list'">
			Cancel
		</button>
		<!-- only show the delete button if product has an id greater than 0 and therefore is not new -->
		<c:if test="${product.id > 0}" >
			<button class="btn btn-primary" type="button" onClick="deleteProduct()">Delete</button>
		</c:if>
	</form:form>
</main>

</body>
</html>