<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>forward_action.jsp</title>
</head>
<body>
	<h2>forward_Action.jsp 에서 footer.jsp 호출</h2>
	forward message<br>
	<jsp:forward page="footer.jsp">
		<jsp:param name="email" value="qorwdjgh13@naver.com" />
		<jsp:param name="tel" value="010-8924-1810" />
	</jsp:forward>
</body>
</html>