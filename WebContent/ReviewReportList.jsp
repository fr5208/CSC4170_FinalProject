<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Reports</title>
</head>
	<body>
		<center>
			<h1>Review Report Management</h1>
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
				<caption><h2>List of Review Reports</h2></caption>
				<tr>
					<th>ReviewID</th>
					<th>PaperID</th>
					<th>ReviewerID</th>
					<th>Description</th>
					<th>Recommendation</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="reviewreport" items="${listReviewReports}">
					<tr>
						<td><c:out value="${reviewreport.reviewID}"/></td>
						<td><c:out value="${reviewreport.paperID}"/></td>
						<td><c:out value="${reviewreport.reviewerID}"/></td>
						<td><c:out value="${reviewreport.description}"/></td>
						<td><c:out value="${reviewreport.recommendation}"/></td>
						<td>
							<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
							<input type="submit" name="editReviewReport" value="Edit"/>
							&nbsp;&nbsp;&nbsp;
							<input type="submit" name="deleteReviewReport" value="Delete"/>
							<input type="hidden" name="reviewReportID" value="${reviewreport.reviewID}"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>