<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Include</title>
</head>
<body>
	<h2>inlcude action</h2>
	<hr>
	include_action.jsp에서 출력한 메시지입니다.<br>
	<jsp:include page="footer.jsp">
		<jsp:param name="email" value="test@naver.com" />
		<jsp:param name="tel" value="010-1515-1115" />
	</jsp:include> 
</body>
</html>