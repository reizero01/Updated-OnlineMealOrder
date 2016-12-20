<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="RestaurantSearchServlet">
	<br/>Address:<input type="text" name="address"> 
	<br/>City:<input type="text" name="city"> 
	<br/>Zip:<input type="text" name="zip"> 
	<br/>Cuisine:<select name="cuisine">
	<option value="Chinese">Chinese</option>
	<option value="American">American</option>
	<option value="Thai">Thai</option>
	<option value="Indian">Indian</option>
	<option value="Italian">Italian</option>
	<option value="Mexican">Mexican</option>
	<option value="Latin">Latin</option>
	<option value="Seafood">Seafood</option>
	<option value="Pizza">Pizza</option>
	<option value="Salad">Salad</option>
	<option value="Vegetarian">Wings</option>
	<option value="Vegetarian">Sandwiches</option>
	<option value="Vegetarian">Burgers</option>
	<option value="Vegetarian">Vegetarian</option>
	</select>
	<br/><input type="submit" value="Search">
</form>
<form action="Home.jsp">
<input type="submit" name="RestaurantSearchBack" value="back"/>
</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>