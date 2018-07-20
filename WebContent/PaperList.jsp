<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Papers</title>
</head>
	<body>
		<center>
			<h1>Paper Management</h1>
			<h2>
				<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="newPCMember" value="New PCMember"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="listPCMembers" value="List All PCMembers"/>
				</form>
				<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
				<input type="submit" name="newPaper" value="New Paper"/>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" name="listPapers" value="List All Papers"/>
				</form>
			</h2>
		</center>
		<div align="center">
			<table border="1" cellpadding="5">
				<caption><h2>List of Papers</h2></caption>
				<tr>
					<th>PaperID</th>
					<th>Title</th>
					<th>Abstract</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="paper" items="${listPapers}">
					<tr>
						<td><c:out value="${paper.paperID}"/></td>
						<td><c:out value="${paper.title}"/></td>
						<td><c:out value="${paper.summary}"/></td>
						<td>
							<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
							<input type="submit" name="editPaper" value="Edit"/>
							&nbsp;&nbsp;&nbsp;
							<input type="submit" name="deletePaper" value="Delete"/>
							<input type="hidden" name="paperID" value="${paper.paperID}"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>