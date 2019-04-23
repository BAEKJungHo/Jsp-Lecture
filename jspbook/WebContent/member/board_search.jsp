<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="member.*" %> 
<%
	request.setCharacterEncoding("UTF-8");
	int id = (Integer)session.getAttribute("memberId");
	BbsDAO bDao = new BbsDAO();
	List<BbsDTO> list = bDao.selectAll();
	bDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bulletin Board</title>
</head>
<body>
	<div align=center>
	<H3>상세조회</H3>
	<a href="loginMain.jsp">회원 목록으로</a>
	<HR>
	<table border="1" style="border-collapse:collapse;" height=150, width=700>
	<tr bgcolor="pink"><th>아이디</th><th>멤버아이디</th><th>제목</th><th>날짜</th><th>내용</th><th>액션</th></tr>
	<%
	for (BbsDTO bb: list) {
	%>
		<tr><td><%=bb.getId()%></td>
		<td><%=bb.getMemberId()%></td>
		<td><%=bb.getTitle()%></td>
		<td><%=bb.getDate()%></td>
		<td><%=bb.getContent()%></td>
		<% 
			String updateUri = "boardServlet?action=update&id=" + bb.getId();
			String deleteUri = "boardServlet?action=delete&id=" + bb.getId();
		%>
		<td>
		 &nbsp;<button onclick="location.href='<%=updateUri%>'">수정</button>&nbsp; 
		<button onclick="location.href='<%=deleteUri%>'">삭제</button>&nbsp; </td>
		</tr>
	<%
	}
	%>
	</table>
	</div>
</body>
</html>