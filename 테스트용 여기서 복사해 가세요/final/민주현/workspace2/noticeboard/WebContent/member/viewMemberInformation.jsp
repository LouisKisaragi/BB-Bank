<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link href="${pageContext.request.contextPath}/member/css/style.css" rel="stylesheet" type="text/css"> --%>
<%-- <link href="${pageContext.request.contextPath}/member/css/liststyle.css" rel="stylesheet" type="text/css"/> --%>

<title>유저 정보 보기</title>  
  
</head>
<body>
<section>
	<form>
		<c:choose>
			<c:when test="${login eq 1 and logNick eq viewNick }">
				<b>[${logNick }]님 안녕하세요</b><p>
				<table class="memberInformation">
				<tr>
				<td colspan="2">[${logNick}] 회원님의 정보</td>
				</tr>
				<tr>
				<th>이름</th><td>[${logName}]</td>
				</tr>
				<tr>
				<th>이메일</th><td>[${logEmail}]</td>
				</tr>
				<tr>
				<th>닉네임</th><td>[${logNick}]</td>
				</tr>
				<tr>
				<th>보유포인트</th><td>[${logPoint}]</td>
				</tr>
				<tr>
				<th>가입날짜</th><td>[${logJoin}]</td>
				</tr>
				</table>
				</c:when>
		
		</c:choose>
	</form>
</section>
</body>
</html>
