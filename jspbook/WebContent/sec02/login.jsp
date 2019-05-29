<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:set var="id" value="${param.id}"/>
    <c:set var="pw" value="${param.password}"/>
    <c:set var="position" value="${param.position}"/>
    <c:choose>
   	   <c:when test="${position eq 'admin'}">
       <c:choose>
           <c:when test="${pw eq '123'}">
           <c:choose>
           		<c:when test="${id eq 'admin'}">
           			관리자님 반갑습니다.
           		</c:when>
           		<c:otherwise>
           		당신은 관리자가 아닙니다.
           		</c:otherwise>
           	</c:choose>
           </c:when>
           <c:otherwise>
           	당신은 관리자가 아닙니다.
           </c:otherwise>
       </c:choose>
       </c:when>
       <c:otherwise>
          고객님 반갑습니다.
       </c:otherwise>
   </c:choose>
</body>
</html>