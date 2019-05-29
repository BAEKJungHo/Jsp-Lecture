<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<center><br>
	<h3>Login</h3><br>
	<hr>
	<form name="loginForm" action="login.jsp" method=post>
		<label><span>ID:</span>
			<input type="text" name="id" size="10"></label>
		<label><span>Password:</span>
			<input type="password" name="password" size="10"></label><br>
		<label>
			<input type="radio" name="position" value="customer" /> 고객
			<input type="radio" name="position" value="admin" /> 관리자
		</label><br><br>
		<input type="submit" value="로그인" name="B1">&nbsp;&nbsp;
		<input type="reset" value="취소" name="B2">
	</form>
	</center>
</body>
</html>