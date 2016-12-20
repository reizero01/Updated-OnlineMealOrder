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
	List<Restaurant> Restaurants = (List<Restaurant>)session.getAttribute("restaurantList");
%>
<hr>
      <form action="MenuSelectServlet">
        <table border=1 cellpadding=5>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Address</th>
            <th>City</th>
          </tr>
<%
	for(Restaurant r: Restaurants){
%>
	<tr>
    	<td><input type="checkbox" name="RestaurantId" value="<%= r.getId() %>"></td>
        <td><%= r.getName() %></td>
        <td><%= r.getAddress() %></td>
        <td><%= r.getCity() %></td>
    </tr>
<% 
	}
%>
	</table>
	<br>
	<input type="submit" value="Menu">
	<input type="reset" value="Clear">
	</form>
	<form action="RestaurantSearch.jsp">
	<input type="submit" name="RestaurantSelectBack" value="back"/>
	</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>