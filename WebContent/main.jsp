<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<%!
	ResultSet resultSet = null;
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sampledb?user=john&password=pass1234";
	static final String USER = "john";
	static final String PASS = "pass1234";
%>
<%!
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Academic Conference Paper Management</title>
</head>
	<body>
		<h1>
		Academic Conference Paper Management
		</h1>
		<h2>
			${loginStatus}
		</h2>
		<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<input type="submit" name="initializeDatabase" value="Initialize Database"/>
		</form>
		<br/>
		<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			Username: <input type="text" name="username">
			<br/>
			Password: <input type="text" name="password">
			<br/>
			<input type="submit" name="login" value="Login">
			<br/>
			<input type="submit" name="register" value="Register">
		</form>
		<br/>
		<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			Paper ID: <input type="text" name="paperID">
			<br/>
			Author 1: <input type="text" name="author1">
			<br/>
			Author 2: <input type="text" name="author2">
			<br/>
			Author 3: <input type="text" name="author3">
			<br/>
			<input type="submit" name="assignAuthors" value="Assign Authors">
		</form>
	</body>
</html>