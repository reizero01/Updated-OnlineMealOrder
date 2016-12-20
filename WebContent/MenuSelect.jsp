<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Dao.*, Servlet.*, OnlineMealOrder.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	List<Meal> meals = (List<Meal>)session.getAttribute("mealList");
%>
<hr>
      <form action="ShoppingCartServlet">
        <table border=1 cellpadding=6>
          <tr>
            <th>Number</th>
            <th>RestaurantName</th>
            <th>MealName</th>
            <th>Description</th>
            <th>Price</th>
          </tr>
<%
	for(Meal m: meals){
%>
	<tr>
    	<td><input type="checkbox" name="mId" value="<%= m.getId()%>"></td>
    	<td><%= m.getRestaurant().getName()%></td>
        <td><%= m.getName()%></td>
        <td><%= m.getDiscription()%></td>
        <td><%= m.getPrice()%></td>
    </tr>
<% 
		}
%>
	</table>
	<br>
	<input type="Submit" value="Order">
	<input type="reset" value="Clear">
	</form>
	<form action="RestaurantSelect.jsp">
	<input type="submit" name="MenuSelectBack" value="back"/>
	</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>