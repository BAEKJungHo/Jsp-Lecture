<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, jspbook.ch06.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String productname = request.getParameter("product");
	int quantity = Integer.parseInt(request.getParameter("quantity"));
	// productList라는 이름으로 세션 설정
	ArrayList<CartDTO> list = (ArrayList<CartDTO>)session.getAttribute("productList");
	if(list == null) {
		list = new ArrayList();
		session.setAttribute("productList", list);
	}
	CartDTO cart = new CartDTO();
	cart.setProductName(productname);
	cart.setQuantity(quantity);
	list.add(cart);
%>
<script>
alert("<%=productname %> 이(가) <%=quantity %>개 추가 되었습니다");
history.go(-1);
</script>
</body>
</html>