<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="RestaurantFindServlet">
<input type="submit" name="RestaurantFindForward" value="Find Historical Restaurants"/>
</form>
<form action="RestaurantSearchServlet">
<input type="submit" name="RestaurantSelectForward" value="Search New Restaurants"/>
</form>
<form action="PaymentHistory.jsp">
<input type="submit" name="UserHistoryForward" value="Payment History"/>
</form>
<form action="CreateGroup.jsp">
<input type="submit" name="CreateGroupForward" value="Create New Group"/>
</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>