<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<link href="${ pageContext.request.contextPath }/bbboard1/css/style.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<script src="${ pageContext.request.contextPath }/bbboard1/script.js"></script>
<link href="${ pageContext.request.contextPath}/bbboard1/css/style.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath}/bbboard1/css/deleteFormstyle.css" rel="stylesheet" type="text/css">
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
<b>글삭제</b>
<c:choose>
	<c:when test="${mem} eq 2">
		<c:when test="${login} eq 1">
			<c:when test="${id} eq ${writer}">
				정말로 삭제하시겠습니까?
				<a href="${pageContext.request.contextPath}/bbboard1/deletePro.do?pageNum=${pageNum}&bn=${bn}">네</a>
			</c:when>
		</c:when>
	</c:when>
</c:choose>
<form method="post" name="delForm" action="${ pageContext.request.contextPath }/bbboard1/deletePro.do?pageNum=${pageNum}&bn=${bn}"
	onsubmit="return deleteSave()">
	
	<table class="deletetable">
		<tr>
			<td><b>비밀번호를 입력해 주세요</b></td>
		</tr>
		<tr>
			<td>비밀번호 : 
					<input type="password" name="pass">
					<input type="hidden" name="num" value="${num}">
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="삭제">
				<input type="button" value="목록"
					onclick="document.location.href='${pageContext.request.contextPath}/bbboard1/list.do?pageNum=${pageNum}&bn=${bn}'">
			</td>					
		</tr>
	</table>
</form>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>

</body>
</html>