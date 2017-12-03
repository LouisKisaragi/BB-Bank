<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#footer {

    position:absolute;

    bottom:0;

    width:100%;

    height:70px;   
}

</style>
	
</head>
<body>
<%@ include file="header.jsp" %>
<section>

<%@ include file="mainContent.jsp" %>
</section>
<a href="${pageContext.request.contextPath}/board/list.do?pageNum=1&bn=1">
			공지사항</a>
<c:choose>
	<c:when test="${login eq 1 }">
		<a href="${pageContext.request.contextPath }/member/logout.do">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath }/member/join.do">회원가입</a>	
		<a href="${pageContext.request.contextPath }/member/login.do">로그인</a>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${login eq 1 }">
		<p>로그인상태</p>
	</c:when>
	<c:otherwise>
		<p>비로그인상태</p>
	</c:otherwise>
</c:choose>
<div id="footer">
<%@ include file="footer.jsp" %>
</div>


    

</body>
</html>