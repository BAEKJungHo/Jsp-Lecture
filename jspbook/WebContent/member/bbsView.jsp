    
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, member.*" %> 
<%
	BbsMember bm = (BbsMember)request.getAttribute("bbsMember");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 상세보기</title>
	<style>
		th { text-align: center; }
		td { padding-left: 5px; }
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
	<h3>게시글 상세보기</h3>
	<hr>
	<table border="1" style="border-collapse:collapse;" width="500">
	<tr bgcolor="skyblue"><th height="30" width="80">항목</th><th>내용</th></tr>
		<tr><td height="25">번호</td><td><%=bm.getId()%></td></tr>
		<tr><td height="25">제목</td><td><%=bm.getTitle()%></td></tr>
		<tr><td height="25">글쓴이</td><td><%=bm.getName()%></td></tr>
		<tr><td height="25">수정일시</td><td><%=bm.getDate().substring(0, 16)%></td></tr>
		<tr><td height="100">내용</td><td><%=bm.getContent()%></td></tr>
	</table>
	<br>
	<%
		String updateUri = "bbsServlet?action=update&id=" + bm.getId();
		String deleteUri = "bbsServlet?action=delete&id=" + bm.getId();
	%>
	<button onclick="location.href='<%=updateUri%>'">수정</button>&nbsp;&nbsp;
	<button onclick="location.href='<%=deleteUri%>'">삭제</button>&nbsp;&nbsp;
	<a href="bbsServlet?action=list&page=1">목록으로</a>
	</center>
</body>
</html>