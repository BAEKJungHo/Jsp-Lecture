<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, jspbook.ch06.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<H2>계산</H2>
	<%=session.getAttribute("username")%>님이 선택한 상품 목록
	<HR>
	<%	// productList라는 이름으로 세션 설정
		ArrayList<CartDTO> list = (ArrayList<CartDTO>)session.getAttribute("productList"); 
		if(list == null) {
			out.println("선택한 상품이 없습니다.!!!");
		}
		else {
			for(CartDTO cart: list) {
				out.println(cart.getProductName() + " " + cart.getQuantity() + "개<br>");
			}
		}
	%>
	</div>
</body>
</html>