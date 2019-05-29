<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>물품 구매</title>
</head>
<body>
	<center>
	<h2>물품을 선택 하세요</h2>
	<form name="surveyForm" action="buyItemSubmit.jsp" method=post>
		<label><span>품목</span>
			<input type='checkbox' name='item' value='hat' />모자
			<input type='checkbox' name='item' value='bag' />가방
			<input type='checkbox' name='item' value='tv' />TV
			<input type='checkbox' name='item' value='phone' />핸드폰</label><br><br>
		<label><span></span>&nbsp;&nbsp;<input type="submit" value="제출" name="W1">&nbsp;&nbsp;
			<input type="reset" value="재작성" name="W2">&nbsp;&nbsp;</label>
	</form>
	</center>
</body>
</html>