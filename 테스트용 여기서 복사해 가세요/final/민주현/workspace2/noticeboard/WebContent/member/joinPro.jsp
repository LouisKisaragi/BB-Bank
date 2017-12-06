<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>
<html>
<head>
</head>
<title>회원가입을 축하합니다</title>
<link href="${pageContext.request.contextPath}/member/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/member/css/joinstyle.css" rel="stylesheet" type="text/css"/>
<script src="script.js"></script>
<script src="${pageContext.request.contextPath}/member/script.js"></script>
<body>
	<p><br><br>회원가입을 축하 드립니다<br><br><p>
<%-- 	<input type ="button" value="이전페이지로 가기" onClick="location.href='${page}'"> --%>
	<input type ="button" value="메인페이지로 가기" onClick="location.href='${pageContext.request.contextPath }/board/main.do'">
</body>
</html>
