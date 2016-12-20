<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Dao.*, Servlet.*, OnlineMealOrder.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cart" scope="session" class="OnlineMealOrder.ShoppingCart" /> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
    List<Meal> selectMeals = (List<Meal>)session.getAttribute("mealOrder");
%>
Shopping Cart TotalPrice: $<%= cart.getCost()%>
<hr>
<table border="1" width="1000" cellspacing="0"  cellpadding="2" align="center"> 
          <tr>
            <th>Quantity</th>
            <th>ResturantName</th>
            <th>MealName</th>
            <th>Price</th>
          </tr>
	
<% 
	for(Meal m: selectMeals){
%>
		<tr>
		<form action="AddtoShoppingCartServlet">
    	<td><input type="number" name="quantity" min=1 ></td>
    	<td><%= m.getRestaurant().getName()%></td>
        <td><%= m.getName()%></td>
        <td><%= m.getPrice()%></td>
        <td><input type="submit" name="Add" value="Add"></td> 
        <td><input type="submit" name="Remove" value="Remove"></td> 
        <input type="hidden" name="mealId" value=<%= m.getId()%>>
		</form>
		</tr>
<%
	}
%>
</table> 
<form action="MenuSelect.jsp">
<input type="submit" name="ShoppingCartBack" value="back"/>
</form>
<center> 
<table width="1000" border="1" cellspacing="0" cellpadding="2" border="0"> 
<caption><b>Shopping Cart Contents</b></caption> 
 <tr> 
	<th>ResturantName</th>
 	<th>MealName     </th> 
	<th>Price</th> 
 	<th>Quantity</th> 
 </tr> 
<% 
	Enumeration<CartItem> allItems = cart.getEnumeration();
	CartItem tmpItem; 
 	while (allItems.hasMoreElements()) { 
	 tmpItem = (CartItem)allItems.nextElement();
%> 
 	<tr> 
 	<td align="center"><%= tmpItem.getMeal().getRestaurant().getName() %></td>
 	<td align="center"><%= tmpItem.getMeal().getName() %></td>
 	<td align="center">$<%= tmpItem.getMeal().getPrice() %></td> 
 	<td align="center"><%= tmpItem.getQuantity()%></td> 
 	</tr> 
<% 
 	} 
%>
 </table> 
 </center> 
<form action="OrderConfirmationServlet">
<input type="submit" name="OrderConfirmation" value="Confirm"/>
</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>