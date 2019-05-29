<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String[] movieList = {"타이타닉", "쥬만지", "가오갤", "스파이더맨"};
		pageContext.setAttribute("movieList", movieList);
	%>
	<table border="1" style="width: 100%; text-align: center;">
		<tr>
			<th>index</th>
			<th>count</th>
			<th>title</th>
		</tr>
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<tr>
				<td>${status.index}</td>
				<td>${status.count}</td>
				<td>${movie}</td>
			</tr>
		</c:forEach>
	</table>
	
	<ul>
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<c:choose>
				<c:when test="${status.first}">
				<li style="font-weight: bold; color: red;">${movie}</li>
				</c:when>
				<c:otherwise>
					<li>${movie}</li>
				</c:otherwise>	
			</c:choose>
		</c:forEach>
	</ul>
	<c:forEach var="movie" items="${movieList}" varStatus="status">
	${movie} <c:if test="${not status.last}">, </c:if>
	</c:forEach>
</body>
</html>