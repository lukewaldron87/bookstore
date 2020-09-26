<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	
	<title>Save Products</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
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
		
		<table>
			<tbody>
				<tr>
					<td><label>Product Name:</label></td>
					<td><form:input path="productName" /></td>
				</tr>
				<tr>
					<td><label>Unit Price:</label></td>
					<td><form:input path="unitPrice" /></td>
				</tr>
				<tr>
					<td><label>Description:</label></td>
					<td><form:input path="description" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>
			</tbody>
		</table>
	
	</form:form>

	<!-- only show the delete button if product has an id greater than 0 and therefore is not new -->
	<c:if test="${product.id > 0}" >
		<input onClick="deleteProduct()" type="submit" Value="Delete">
	</c:if>

	<a href=${pageContext.request.contextPath}/employee/product/list>Back to List</a>
</main>

</body>
</html>