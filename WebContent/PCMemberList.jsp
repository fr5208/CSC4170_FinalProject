<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>PCMembers</title>
</head>
	<body>
		<center>
			<h1>PC Member Management</h1>
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
			</h2>
		</center>
		<div align="center">
			<table border="1" cellpadding="5">
				<caption><h2>List of PCMembers</h2></caption>
				<tr>
					<th>MemberID</th>
					<th>Username</th>
					<th>Password</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="pcmember" items="${listPCMembers}">
					<tr>
						<td><c:out value="${pcmember.memberID}"/></td>
						<td><c:out value="${pcmember.username}"/></td>
						<td><c:out value="${pcmember.password}"/></td>
						<td><c:out value="${pcmember.email}"/></td>
						<td>
							<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
							<input type="submit" name="editPCMember" value="Edit"/>
							&nbsp;&nbsp;&nbsp;
							<input type="submit" name="deletePCMember" value="Delete"/>
							<input type="hidden" name="memberID" value="${pcmember.memberID}"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>