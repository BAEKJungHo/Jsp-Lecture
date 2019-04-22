<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>forward, sendRedirect</h2>
	<form method=post action=forward_action2.jsp>
		forward 액션 : <input type=text name=username>
		<input type=submit value="확인">
	</form>
	
	<form method=post action=response_sendRedirect.jsp>
		response.sendRedirect 액션 : <input type=text name=username>
		<input type=submit value="확인">
	</form>
</body>
</html>