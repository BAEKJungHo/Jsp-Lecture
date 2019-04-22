<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//request.setCharacterEncoding("UTF-8"); request.getParameter("error");
	String error = (String)request.getAttribute("error");
	System.out.println(error);
	 if(error != null) {
		out.println("<script>alert('" + error + "')</script>");
	}
	
%>
	<center><br><br>
	<h3>Member Login</h3><br>
	<hr>
	<!-- <form name="loginForm" action=/jspbook/member/loginProcServlet method=post> -->
	<form name="loginForm" action="/jspbook/member/memberProcServlet?action=login" method=post>
		<label><span>ID:</span><input type="text" name="id" size="10"></label>
		<label><span>PASSWORD:</span><input type="password" name="password"	size="10"></label><br><br>
		<label><span></span><input type="submit" value="로그인" name="B1">&nbsp;&nbsp;</label><br><br>
	</form>
	<button onclick="location='register.html'">회원 가입</button>
	</center>
</body>
</html>