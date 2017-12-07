<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>



<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>멤버정보</title>

<link href="${pageContext.request.contextPath}/bbadmin/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bbadmin/css/liststyle.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/bbadmin/script.js"></script>
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
<%@ include file="../bbadmin/adheader.jsp" %>
<section>

<b>유저목록(전체 유저:${count})</b>
<p><c:if test="${login eq 1 }">(${logNick }님 안녕하세요)</c:if></p>

<c:if test="${count == 0}">
<table  class="listtable">
	<tr>
		<td>
			게시판에 저장된 유저가 없습니다.
		</td>
	</tr>
</table>
</c:if>

<c:if test="${count > 0}">

	

<table class="listtable">
	
<tr>
	<td colspan="10">
	</td>
</tr>
	<tr>
		<th id="num">번 호</th>
		<th id="id">아이디</th>
		<th id="name">이 름</th>
		<th id="nickname">닉네임</th>
		<th id="email">이메일</th>
		<th id="point">보유포인트</th>
		<th id="joindate">가입날짜</th>
		<th id="logindate">최근접속일짜</th>
		<th>탈퇴</th>
	</tr>
	<c:forEach var="article" items="${articleList}">
	<tr>
		<td align="center" width="50">
			<c:out value="${number}"/>
			<c:set var="number" value="${number - 1}"/>
		</td>
		<td class="id">
			${article.id }
		</td>
		<td class="name">
			${article.name }
		</td>
		<td class="nickname">	
			${article.nickname }
		</td>
		<td>${article.email}</td>
		<td>${article.point}</td>
		<td>${article.joindate}</td>
		<td>${article.logindate}</td>
		<td  colspan="2"><a href="${pageContext.request.contextPath }/bbadmin/deletemember.do?id=${article.id}&pass=${article.pass}">[탈퇴시키기]</a></td>
	</tr>
	</c:forEach>
</table>

</c:if>
</section>
<div id="footer">
<%@ include file="../bbadmin/adfooter.jsp" %>
</div>
</body>
</html>
