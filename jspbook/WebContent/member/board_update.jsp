<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
<%
	//request.setCharacterEncoding("UTF-8");
	BbsDTO bbs = (BbsDTO) request.getAttribute("bbs");
%>
	<h3>게시물 수정</h3>
	<hr>
	<form name="updateBoardForm" action="/jspbook/member/boardServlet?action=execute" method=post>
		<input type="hidden" id="id" name="id" value="<%=bbs.getId()%>">
		<label><span>아이디:</span>
			<%=bbs.getId()%></label><br>
		<label><span>제목:</span>
			<input type="text" name="title" value="<%=bbs.getTitle()%>" size="10"></label><br>
		<label><span>내용:</span>
			<textarea rows="10" cols="30" name="contents"></textarea>
			</label><br>
		<label><span></span><input type="submit" value="수정" name="B1">&nbsp;&nbsp;
			<input type="reset" value="재작성" name="B2">
	</form>
</body>
</html>