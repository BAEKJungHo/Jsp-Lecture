<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>설문 조사</title>
</head>
<body>
	<center>
	<h2>설문 조사</h2>
	<form name="surveyForm" action="resultSubmit.jsp" method=post>
		<label><span>이름:</span>
			<input type="text" name="myName" size="20"></label><br><br>
		<label><span>성별:</span>
			<input type="radio" name="gender" value="male" /> 남자
			<input type="radio" name="gender" value="female" /> 여자</label><br><br>
		<label><span>좋아하는 계절:</span>
			<input type='checkbox' name='season' value='spring' />봄
			<input type='checkbox' name='season' value='summer' />여름
			<input type='checkbox' name='season' value='fall' />가을
			<input type='checkbox' name='season' value='winter' />겨울</label><br><br>
		<label><span></span>&nbsp;&nbsp;<input type="submit" value="제출" name="W1">&nbsp;&nbsp;
			<input type="reset" value="재작성" name="W2">&nbsp;&nbsp;</label>
	</form>
	</center>
</body>
</html>