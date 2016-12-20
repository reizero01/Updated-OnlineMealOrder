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
	CheckoutDao ckDao = new CheckoutDao();
	User user = (User)session.getAttribute("User");
	List<Checkout> checkouts = ckDao.getCheckoutbyUser(user);
%>
<center>
<table width="500" border="1" cellspacing="0" cellpadding="2" border="0"> 
<caption><b>Payment History</b></caption> 
<tr> 
	<th>DateTime</th> 
	<th>TotalPrice</th> 
</tr> 
<% 
	for(Checkout c:checkouts){
%> 
 	<tr> 
 	<td align="center"><%= c.getCheckDate() %></td>
 	<td align="center">$<%= c.getTotalCost() %></td> 
 	</tr> 
<% 
 	} 
%>
</table> 
</center> 
<form action="Home.jsp">
<input type="submit" name="PaymentHistoryBack" value="back"/>
</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>