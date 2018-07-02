<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
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
		<table width="100%">
		<tr align="center">
		<th>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="initializeDatabase" value="Initialize Database"/>
			</form>
		</th>
		<th>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				Username: <input type="text" name="username">
				<br/>
				Password: <input type="text" name="password">
				<br/>
				<input type="submit" name="login" value="Login">
				<input type="submit" name="register" value="Register">
			</form>
		</th>
		</tr>
		<tr align="center">
		<td>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				Paper ID: <input type="text" name="paperID">
				<br/>
				Reviewer 1: <input type="text" name="reviewer1">
				<br/>
				Reviewer 2: <input type="text" name="reviewer2">
				<br/>
				Reviewer 3: <input type="text" name="reviewer3">
				<br/>
				<input type="submit" name="assignReviewers" value="Assign Reviewers">
			</form>
		</td>
		<td>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				Author name search. To search two authors, separate their names with a comma.
				<br/>
				Search papers by name: <input type="text" name="authorNameSearch">
				<br/>
				<input type="submit" name="singleAuthorNameSearch" value="Search as Single Author">
				<br/>
				<input type="submit" name="firstAuthorNameSearch" value="Search by First Author">
				<br/>
				<input type="submit" name="twoAuthorNameSearch" value="Search by Two Authors">
			</form>
			<br/>
			Search Results:
			<br/>
			${searchResults}
		</td>
		</tr>
		<tr align="center">
		<td>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<br/>
			<input type="submit" name="listPCMemberWithMostPapers" value="Find PC Member with Most Assigned Papers">
			<br/>
			<input type="submit" name="listPCMemberNoAssignedPapers" value="Find PC Members with No Assigned Papers">
			<br/>
			<input type="submit" name="rejectedByMattJohn" value="List Papers Rejected by Both Matt and John">
			<br/>
			<input type="submit" name="listAcceptedPapers" value="List Accepted Papers">
			</form>
			<br/>
		</td>
		</tr>
		<tr align="center">
		<td>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<input type="submit" name="updatePCMembers" value="Update PC Members">
			</form>
		</td>
		<td>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<input type="submit" name="updatePapers" value="Update Papers">
			</form>
		</td>
		<td>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<input type="submit" name="updateReviewReports" value="Update Review Reports">
			</form>
		</td>
		</tr>
		</table>
	</body>
</html>