<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert/Edit Paper</title>
</head>
	<body>
		<center>
			<h1>Paper Management</h1>
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
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>
						<c:if test="${paper != null}">
                        	Edit Paper
                    	</c:if>
                    	<c:if test="${paper == null}">
                        	Add New Paper
                    	</c:if>
					</h2>
				</caption>
				<c:if test="${paper != null}">
					<input type="hidden" name ="paperID" value="<c:out value='${paper.paperID}'/>"/>
				</c:if>
				<tr>
					<th>Title: </th>
					<td>
						<input type="text" name="title" size="32" value="<c:out value='${paper.title}'/>"/>
					</td>
				</tr>
				<tr>
					<th>Abstract: </th>
					<td>
						<textarea name="summary" id="message" style="height: 200px; width: 250px; resize: none;"><c:out value='${paper.summary}'/></textarea>
					</td>
				</tr>
				<tr>
					<th>PDF: </th>
					<td>
						<input type="file" name="pdf" accept=".pdf"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<c:if test="${paper != null}">
							<input type="submit" name="updatePaper" value="Save"/>
						</c:if>
						<c:if test="${paper == null}">
							<input type="submit" name="insertPaper" value="Save"/>
						</c:if>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</body>
</html>