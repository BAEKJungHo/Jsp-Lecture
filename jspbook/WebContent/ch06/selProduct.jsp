<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
</head>
<%
	request.setCharacterEncoding("UTF-8"); // euc-kr
	String username = request.getParameter("username");
	if (username.equals("")) {
		out.println("<script>alert('로그인을 하세요');");
		out.println("history.go(-1);</script>");
	}
	session.setAttribute("username", username); // 사용자 이름을 세션에 저장
%>
<body>
	<center>
		<h2>상품 선택</h2>
		<hr>
		<%= session.getAttribute("username") %>님이 로그인한 상태 입니다.
		<hr>
		<form name="form1" method="POST" action="add2.jsp">
			<select name="product">
				<option>사과</option>
				<option>자몽</option>
				<option>포도</option>
				<option>딸기</option>
				<option>망고</option>
			</select>	
			&nbsp;&nbsp;수량: <input type="text" name="quantity" size="4" value="0">&nbsp;&nbsp;
			<input type="submit" value="추가" />
		</form>
		<a href="checkOut2.jsp">계산</a>
	</center>
</body>
</html>