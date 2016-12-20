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
	User user = (User)session.getAttribute("User");
	FriendGroupDao fgDao = new FriendGroupDao();
	List<FriendGroup> groupList = fgDao.findGroupbyCreator(user);
	String addGroupStatus = (String) session.getAttribute("AddGroupStatus");
	if (addGroupStatus == null){
		out.print("Please enter group name:");
	}
	else if (addGroupStatus.equals("fail")){     
		out.print("Invalid group name. Try a new one");
	}
	else{  
		out.print("Group name is added successfully");
	}
	session.setAttribute("AddGroupStatus", null);
%>
<form action="CreateGroupServlet">
	<br/>GroupName:<input type="text" name="groupname"> 
	<br/><input type="submit" value="Submit">
</form>
<%
	String addMemberStatus = (String) session.getAttribute("AddMemberStatus");
	if (addMemberStatus == null){
		out.print("Please enter group name and member name:");
	}
	else if (addMemberStatus.equals("fail")){     
		out.print("Invalid group name or member name. Try a new one");
	}
	else{  
		out.print("Member is added to group successfully");
	}
	session.setAttribute("AddMemberStatus", null);
%>
<form action="AddGroupMemberServlet">
	<br/>GroupName:<input type="text" name="groupname">
	<br/>MemberName:<input type="text" name="membername">  
	<br/><input type="submit" value="Submit">
</form>

<% 
	String removeGroupStatus = (String) session.getAttribute("RemoveGroupStatus");
	if (removeGroupStatus == null){
		out.print("Choose a Group to Remove:");
	}
	else{  
		out.print("Group is removed successfully");
	}
	session.setAttribute("RemoveGroupStatus", null);
%>
<form action="RemoveGroupServlet">
<select name=friendgroup >
<%
	for(FriendGroup fg: groupList){
%>
<option value=<%= fg.getId() %>><%= fg.getName()%></option>
<%
	}
%>
</select>
<br/><input type="submit" name="Submit" value="Remove" />
</form>

<form action="Home.jsp">
	<input type="submit" name="CreateGroupBack" value="back"/>
</form>
<p><a href="LogoutServlet">Logout</a>
</body>
</html>