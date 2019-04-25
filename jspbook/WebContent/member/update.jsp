<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
	<h3>회원수정</h3>
	<hr>
	<form name="updateForm" action="/jspbook/member/memberProcServlet?action=execute" method=post>
	<c:set var="member" value="${requestScope.member}" />
		<input type="hidden" id="id" name="id" value="${member.id}">
		<label><span>아이디:</span>
			${member.id}</label><br>
		<label><span>이름:</span>
			<input type="text" name="name" value="${member.name}" size="10"></label><br>
		<label><span>생일:</span>
			<input type="text" name="birthday" value="${member.birthday}" size="10"></label><br>
		<label><span>주소:</span>
			<input type="text" name="address" value="${member.address}" size="20"></label><br>
		<label><span></span><input type="submit" value="수정" name="B1">&nbsp;&nbsp;
			<input type="reset" value="재작성" name="B2">
	</form>
</body>
</html>