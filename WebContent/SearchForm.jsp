<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
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
			</h2>
		</center>
		<center>
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				Author name search, supports up to 2 authors.
				<br>
				<br>
				Author 1: <input type="text" name="author1">
				&nbsp;&nbsp;
				Author 2: <input type="text" name="author2">
				<br>
				<br>
				<input type="submit" name="singleAuthorNameSearch" value="Search as Single Author">
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="firstAuthorNameSearch" value="Search by First Author">
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="twoAuthorNameSearch" value="Search by Two Authors">
			</form>
			<br/>
		<div align="center">
			<table border="1" cellpadding="5">
				<caption><h2>Search Results</h2></caption>
				<tr>
					<th>PaperID</th>
					<th>Title</th>
					<th>Summary</th>
				</tr>
				<c:forEach var="paper" items="${searchResults}">
					<tr>
						<td><c:out value="${paper.paperID}"/></td>
						<td><c:out value="${paper.title}"/></td>
						<td><c:out value="${paper.summary}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</center>
	</body>
</html>