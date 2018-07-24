<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert/Edit Review Report</title>
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
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>
						<c:if test="${reviewreport != null}">
                        	Edit Review Report
                    	</c:if>
                    	<c:if test="${reviewreport == null}">
                        	Add New Review Report
                    	</c:if>
					</h2>
				</caption>
				<c:if test="${reviewreport != null}">
					<input type="hidden" name ="reviewID" value="<c:out value='${reviewreport.reviewID}'/>"/>
				</c:if>
				<tr>
					<th>PaperID: </th>
					<td>
						<input type="text" name="paperID" size="32" value="<c:out value='${reviewreport.paperID}'/>"/>
					</td>
				</tr>
				<tr>
					<th>ReviewerID: </th>
					<td>
						<input type="text" name="reviewerID" size="32" value="<c:out value='${reviewreport.reviewerID}'/>"/>
					</td>
				</tr>
				<tr>
					<th>Description: </th>
					<td>
						<input type="text" name="description" size="32" value="<c:out value='${reviewreport.description}'/>"/>
					</td>
				</tr>
				<tr>
					<th>Recommendation: </th>
					<td>
						<input type="text" name="recommendation" size="32" value="<c:out value='${reviewreport.recommendation}'/>"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<c:if test="${reviewreport != null}">
							<input type="submit" name="updateReviewReport" value="Save"/>
						</c:if>
						<c:if test="${reviewreport == null}">
							<input type="submit" name="insertReviewReport" value="Save"/>
						</c:if>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</body>
</html>