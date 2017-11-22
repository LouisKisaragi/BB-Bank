<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>테스트용 메인</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/board/list.do?pageNum=1&bn=2">
				게시판2!</a>
<a href="${pageContext.request.contextPath }/member/join.do?num=0&pageNum=0&bn=0">회원가입</a>	

</body>
</html>