<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<title>ch06 : twitter_list.jsp</title>
</head>
<body>
	<div align=center>
	<H3>My Simple Twitter!!</H3>
	<a href="loginMain.jsp">회원 목록으로</a>
	<HR>
	<form action="/jspbook/member/twitServlet" method="POST">
		<!-- 세션에 저장된 이름 출력 -->
		@<%=session.getAttribute("memberName") %>&nbsp;
		<input type="text" name="msg">&nbsp;&nbsp;<input type="submit" value="트윗">
	</form>
	<HR>
	<div align="left">
	<UL>
	<%
		// application 내장객체를 통해 msgs 이름으로 저장된 ArrayList를 가지고 옴
		ArrayList<String>msgs = (ArrayList<String>)application.getAttribute("msgs");
		
		//msgs가 null 이 아닌 경우에만 목록 출력
		if(msgs != null) {
			for(String msg : msgs) {
				out.println("<LI>"+msg+"</LI>");
			}
		}
	%>
	</UL>
	</div>
	</div>
</body>
</html>