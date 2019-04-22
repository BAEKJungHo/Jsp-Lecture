<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>session</h2>
		<hr>
		<% session.setAttribute("login","홍길동");
			if(session.isNew())  {
				out.println("<script> alert('세션이 해제되어 다시 설정')</script>");
				session.setAttribute("login","홍길동");
			}
		%>
		# <%= session.getAttribute("login") %>님 환영합니다.<br>
		1. 세션 ID : <%= session.getId() %><br>
		2. 세션 유지시간 : <%= session.getMaxInactiveInterval() %><br>
	</center>
</body>
</html>