<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구매 현황</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String[] item = request.getParameterValues("item");
int total = 0;
out.println("당신이 선택한 물품은 : ");
for(int i=0; i<item.length; i++) {
	if(item[i].equals("hat")) { 
		out.println("모자");
		total += 10000;
	}
	else if(item[i].equals("bag")) { 
		out.println("가방");
		total += 50000;
	}
	else if(item[i].equals("tv")) {
		out.println("TV");
		total += 100000;
	}
	else if(item[i].equals("phone")) {
		out.println("핸드폰");
		total += 1000000;
	}
}
out.println("<br>" + "총 가격 : " + total);
%>
</body>
</html>