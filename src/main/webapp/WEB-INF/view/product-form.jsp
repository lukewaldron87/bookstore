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
	
	
</head>

<body>

	<h2>Add Product</h2>
	
	<h3>Save Production</h3>
	
	<form:form action="saveProduct" modelAttribute="product" methoe="POST">
	
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
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>
			</tbody>
		</table>
	
	</form:form>
	
	<a href=${pageContext.request.contextPath}/product/list>Back to List</a>

</body>
</html>