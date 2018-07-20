<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert New PCMember</title>
</head>
	<body>
		<center>
			<h1>PC Member Management</h1>
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
			<form action="${pageContext.request.contextPath}/servlets/dbservlet" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>
						<c:if test="${pcmember != null}">
                        	Edit PCMember
                    	</c:if>
                    	<c:if test="${pcmember == null}">
                        	Add New PCMember
                    	</c:if>
					</h2>
				</caption>
				<c:if test="${pcmember != null}">
					<input type="hidden" name ="memberID" value="<c:out value='${pcmember.memberID}'/>"/>
				</c:if>
				<tr>
					<th>Username: </th>
					<td>
						<input type="text" name="username" size="32" value="<c:out value='${pcmember.username}'/>"/>
					</td>
				</tr>
				<tr>
					<th>Password: </th>
					<td>
						<input type="text" name="password" size="32" value="<c:out value='${pcmember.password}'/>"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<c:if test="${pcmember != null}">
							<input type="submit" name="updatePCMember" value="Save"/>
						</c:if>
						<c:if test="${pcmember == null}">
							<input type="submit" name="insertPCMember" value="Save"/>
						</c:if>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</body>
</html>