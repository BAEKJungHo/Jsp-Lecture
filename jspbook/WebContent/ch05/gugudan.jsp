<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>홀수 구구단</title>
</head>
<body>
<center>
	<table border=1px; width=500px; style="text-align:center">
	<caption>홀수 구구단</caption>
		<th>&nbsp;</th>
		<%
			for(int i=3; i<=9; i+=2) {
		%>
				<th><%=i %></th>
		<%
			} 
			for(int i=3; i<=9; i+=2) {
		%>
				<tr><td><%=i %></td>
		<%	
				for(int k=3; k<=9; k+=2) {
		%>  
					<td><%=i*k %></td>
		<%
				}
		%>
			</tr>
		<%
			}
		%>

	</table>
</center>
</body>
</html>