<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>X의 제곱은 Y</title>
</head>
<body>
<center>
	<table border=1px; width=150px; style="text-align:center">
	<caption>X와 Y의 제곱</caption>
		<tr><th>X</th><th>Y</th></tr>
		<%
			for(int i=1; i<=10; i++) {
			
		%>
			<tr><td><%=i %></td><td><%=i*i %></td>
		<%
			}
		%>
	</table>
</center>
</body>
</html>