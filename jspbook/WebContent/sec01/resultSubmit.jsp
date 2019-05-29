<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Information</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String name = request.getParameter("myName");
String gender = request.getParameter("gender");
String[] season = request.getParameterValues("season");

out.println("이름 : " + name + "<br>");
out.println("성별 : " + gender+ "<br>");
out.println("계절 : ");
for(int i=0; i<season.length; i++) {
	out.println(season[i]);
}
%>
</body>
</html>