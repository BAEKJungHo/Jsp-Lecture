<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<title>ch06 : twitter_list.jsp</title>
</head>
<body>
	<div align=center>
	<H3>My Simple Twitter!!</H3>
	<a href="loginMain.jsp">회원 목록으로</a>
	<HR>
	<form action="/jspbook/member/twitServlet" method="POST">
		<!-- 세션에 저장된 이름 출력 -->
		@${memberName}&nbsp;
		<input type="text" name="msg">&nbsp;&nbsp;<input type="submit" value="트윗">
	</form>
	<HR>
	<div align="left">
	<UL>
	<c:set var="msgs" value="${applicationScope.msgs}" />
	<c:if test="${msgs ne null}">
		<c:forEach var="msg" items="${msgs}">
			<li>${msg}</li>
		</c:forEach>
	</c:if>
	</UL>
	</div>
	</div>
</body>
</html>