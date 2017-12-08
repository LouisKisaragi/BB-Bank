<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>테스트용 메인</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/bbboard1/list.do?pageNum=1&bn=1&preface=all">
				게시판1!</a>
	<a href="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=1&bn=2&preface=all">
				게시판2!</a>
	<a href="${pageContext.request.contextPath}/bbboard3/list.do?pageNum=1&bn=3&preface=all">
				게시판3!</a>
	<a href="${pageContext.request.contextPath}/bbboard4/list.do?pageNum=1&bn=4&preface=all">
				게시판4!</a>
	<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=1&bn=5&preface=all">
				게시판5!</a>
	<a href="${pageContext.request.contextPath}/bbgame/gameMain.do?pageNum=1&preface=all">
				게임</a>
				<c:choose>
	<c:when test="${login eq 1 }">
		<a href="${pageContext.request.contextPath }/bbmember/memberOut.do">회원탈퇴</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath }/bbmember/join.do">회원가입</a>
	</c:otherwise>
</c:choose>	
<c:choose>
	<c:when test="${login eq 1 }">
		<a href="${pageContext.request.contextPath }/bbmember/logout.do">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath }/bbmember/login.do">로그인</a>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${login eq 1 }">
		<p>로그인상태</p>
		<a href="javascript:openView('${logNick }');" target="_blank">회원정보</a>
	</c:when>
	<c:otherwise>
		<p>비로그인상태</p>
	</c:otherwise>
</c:choose>
</body>
</html>