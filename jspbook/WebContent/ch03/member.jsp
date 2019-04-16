<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="member" scope="page" class="jspbook.ch03.MemberBean" />
<jsp:setProperty name="member" property="*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보</title>
</head>
<body>
	<center>
	<h3>회원 정보 입력</h3>
	<hr>
	<form name="member_join" method="post">
		이름<input type="text" name="name" width="200" size="10"><br>
		이메일<input type="text" name="email" width="200" size="20"><br>
		전화번호<input type="text" name="phone" width="200" size="15"><br>
		<input type="submit" value="가입" name="B1"><br>
		<input type="reset" value="다시 입력" name="B2"><br>
	</form>
	<hr>
	<br>
	회원 ID : <jsp:getProperty name="member" property="id" />
	</center>
</body>
</html>