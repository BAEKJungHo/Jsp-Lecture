<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, member.*" %> 
<%
	request.setCharacterEncoding("UTF-8");
	List<BbsMember> bmList = (List<BbsMember>)request.getAttribute("bbsMemberList");
	List<String> pageList = (List<String>)request.getAttribute("pageList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시판 목록</title>
	<style>
		td, th { text-align: center; }
		.button {
			font-weight: bold; font-size: 9pt;
			border: 1px solid powderblue;
		}
		input[type=submit] {
			width: 5em; height: 2.5em;
			font-weight: bold; font-size: 10pt;
		}
	</style>
</head>
<body>
	<center>
	<h3>게시판</h3>
	<%=(String)session.getAttribute("memberName")%> 회원님 반갑습니다.<br>
	<a href="bbsWrite.jsp">글쓰기</a>&nbsp;&nbsp;
	<a href="twitter_list.jsp">트윗</a>&nbsp;&nbsp;
	<a href="loginMain.jsp">회원목록</a>&nbsp;&nbsp;
	<a href="memberProcServlet?action=logout">로그아웃</a>
	<hr>
	<table border="1" style="border-collapse:collapse;" width=600>
	<tr bgcolor="skyblue" height="30"><th>글번호</th><th>제목</th><th>글쓴이</th><th>최종수정일</th><th>액션</th></tr>
	<%
	for (BbsMember bm: bmList) {
	%>
		<tr height="25"><td><%=bm.getId()%></td>
		<td><a href="bbsServlet?action=view&id=<%=bm.getId()%>"><%=bm.getTitle()%></a></td>
		<td><%=bm.getName()%></td>
		<td><%=bm.getDate().substring(0, 16)%></td>
		<%
			String updateUri = "bbsServlet?action=update&id=" + bm.getId();
			String deleteUri = "bbsServlet?action=delete&id=" + bm.getId();
		%>
		<td>&nbsp;<button onclick="location.href='<%=updateUri%>'">수정</button>&nbsp;
			<button onclick="location.href='<%=deleteUri%>'">삭제</button>&nbsp;</td></tr>
	<%
	}
	%>
	</table>
	<%
	for (String bmPage: pageList)
		out.print(bmPage);
	%>
	</center>
</body>
</html>