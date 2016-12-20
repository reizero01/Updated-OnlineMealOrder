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
	String LoginStatus = (String) session.getAttribute("LoginStatus");
	if (LoginStatus == null){
		out.print("Please enter username and password:");
	}
	else if (LoginStatus.equals("fail")){     
		out.print("Invalid username or password.");
	}
	else{  
		out.print("Login successfully");
	}
	session.setAttribute("LoginStatus", null);
%>
<form action="LoginServlet">
	<br/>Username:<input type="text" name="username"> 
	<br/>Password:<input type="password" name="password"> 
	<br/><input type="submit" value="Submit">
</form>
<% 
	String RegisterStatus = (String) session.getAttribute("RegisterStatus");
	if (RegisterStatus == null){
		out.print("Please enter username, password and email:");
	}
	else if (RegisterStatus.equals("fail")){     
		out.print("Invalid username or password.");
	}
	else{  
		out.print("Register successfully");
	}
	session.setAttribute("RegisterStatus", null);
%>
<form action="RegisterServlet">
	<br/>Username:<input type="text" name="username"> 
	<br/>Password:<input type="password" name="password"> 
	<br/>EmailAddress:<input type="text" name="email"> 
	<br/><input type="submit" value="Submit">
</form>
</body>
</html>