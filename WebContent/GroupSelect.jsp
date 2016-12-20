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
	List<FriendGroup> groupList = (List<FriendGroup>)session.getAttribute("groupList");
	List<User> memberList = (List<User>)session.getAttribute("memberList");
%>
<form action="SelectGroupServlet">
<select name=selectgroup >
<%
	if(groupList != null){
	for(FriendGroup fg: groupList){
%> 
<option value=<%= fg.getId() %>><%= fg.getName()%></option>
<%
		}}
	else
		response.sendRedirect("Home.jsp");
%>
</select>
<br/><input type="submit" name="Submit" value="Select" />
</form>
<center> 
<table width="200" border="1" cellspacing="0" cellpadding="2" border="0"> 
<caption><b>Group Member List</b></caption> 
 <tr> 
	<th>Name</th>
	<th>EmailAddress</th>
 </tr> 
<% 
	if(memberList != null){
	for(User u : memberList){
%>
 	<tr> 
 	<td align="center"><%= u.getUsername()%></td>
 	<td align="center"><%= u.getEmail() %></td> 
 	</tr> 
<%
 	}}
%>
</table> 
</center> 
<form action="SendMailServlet">
<input type="submit" name="SendMail" value="Send Mail"/>
</form>
<form action="Home.jsp">
<input type="submit" name="HomeBack" value="Home"/>
</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>