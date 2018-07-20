<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Academic Conference Paper Management</title>
</head>
	<body>
		<center>
			<h1>Conference Management</h1>
			<h2>
				<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="newPCMember" value="New PCMember"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="listPCMembers" value="List All PCMembers"/>
				&nbsp;&nbsp;&nbsp;
				<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="newPaper" value="New Paper"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="listPapers" value="List All Papers"/>
				&nbsp;&nbsp;&nbsp;
				<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="newReviewReport" value="New Review Report"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="listReviewReports" value="List All Review Reports"/>
				<br>
				<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="searchDatabaseForm" value="Search Database"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="assignReviewersForm" value="Manage Reviewers"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="showAcceptedPapers" value="List Accepted Papers"/>
				</form>
				</form>
				<br>
				${loginStatus}
			</h2>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="initializeDatabase" value="Initialize Database"/>
			</form>
			<br>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				Username: <input type="text" name="username">
				<br>
				Password: <input type="text" name="password">
				<br>
				<br>
				<input type="submit" name="login" value="Login">
				<input type="submit" name="register" value="Register">
			</form>
		</center>
	</body>
</html>