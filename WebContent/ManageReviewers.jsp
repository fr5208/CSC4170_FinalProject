<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Reviewers</title>
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
			</h2>
		</center>
		<center>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
					Paper ID: <input type="text" name="paperID">
				
					<br/>
					ReviewerID 1: <input type="text" name="reviewer1">
					<br/>
					ReviewerID 2: <input type="text" name="reviewer2">
					<br/>
					ReviewerID 3: <input type="text" name="reviewer3">
					<br/>
					<input type="submit" name="assignReviewers" value="Assign Reviewers">
					<br>
					<br>
			</form>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			&nbsp;&nbsp;&nbsp;
			<input type="submit" name="listPCMemberWithMostPapers" value="Find PC Member with Most Assigned Papers">
			&nbsp;&nbsp;&nbsp;
			<input type="submit" name="listPCMemberNoAssignedPapers" value="Find PC Members with No Assigned Papers">
			&nbsp;&nbsp;&nbsp;
			<input type="submit" name="rejectedByMattJohn" value="List Papers Rejected by Both Matt and John">
			</form>
		</center>
	</body>
</html>