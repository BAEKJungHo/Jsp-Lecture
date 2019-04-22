<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
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
	ArrayList list = (ArrayList)session.getAttribute("productlist");
	if(list == null) {
		list = new ArrayList();
		session.setAttribute("productlist", list);
	}
	list.add(productname);
%>
<script>
alert("<%=productname %> 이(가) 추가 되었습니다");
history.go(-1);
</script>
</body>
</html>