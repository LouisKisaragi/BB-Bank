<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글 내용</title>
<link href="${pageContext.request.contextPath}/bbboard5/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard5/css/contentstyle.css" rel="stylesheet" type="text/css"/>
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
<%@ include file="../bbboard/header.jsp" %>
<section>
<b>글 내용 보기</b><br>
<form>
<table class=contenttable>
	<tr>
		<th>글번호</th>
		<td>${article.num}</td>
		<th>조회수</th>
		<td>${article.readcount}</td>		
	</tr>
	<tr>
		<th>작성자</th>
		<td>${article.writer}</td>
		<th>작성일</th>
		<td>${article.regdate}</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td colspan=3 class=contenttitle>${article.subject}</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td colspan=3 class=content><pre>${article.content}</pre></td>
	</tr>
	<tr>
		<td colspan=4>
			<c:if test="${mem ne 2}">
				<input type=button value="수정" onclick="document.location.href='${pageContext.request.contextPath}/bbboard5/updateForm.do?num=${article.num}&pageNum=${pageNum}'">
				<input type=button value="삭제" onclick="document.location.href='${pageContext.request.contextPath}/bbboard5/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">
			</c:if>
			<input type=button value="목록" onclick="document.location.href='${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${pageNum}'">
		</td>
	</tr>
</table>
</form>
<!-- 임의의 변수<br>
멤버 번호 : ${mem}<br>
세션 값 : ${tempdata}<br>-->
</section>

<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>