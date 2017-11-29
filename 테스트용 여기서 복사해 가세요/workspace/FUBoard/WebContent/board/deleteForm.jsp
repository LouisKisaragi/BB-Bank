<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css"/>
<head>
<title>게시판</title>
<script>
	function deleteSave(){
		if(document.delForm.pass.value==""){
			alert("비밀번호를 입력하세요.");
			document.delForm.pass.focus();
			return false;
		}
		function back(){
			history.go(-1);
		}
	}
</script>
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/board/css/deleteFormstyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<section>
<b>글삭제</b>
<c:choose>
	<c:when test="${mem } eq 1">
		<c:when test="${login } eq 1">
			<c:when test="${id } eq ${writer }">
				정말로 삭제하시겠습니까?
				<a href="${pageContext.request.contextPath}/board/deletePro.do?pageNum=${pageNum}&bn=${bn}">네</a>
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:when>
	</c:when>
<c:otherwise>
<form method="POST" name="delForm" action="${pageContext.request.contextPath}/board/deletePro.do?pageNum=${pageNum}&bn=${bn}" 
	onsubmit="return deleteSave()">
	<table class="deletetable">
		<tr>
			<td>
				<b>비밀번호를 입력해 주세요.</b>
			</td>
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
				onClick="document.location.href='${pageContext.request.contextPath}/board/list.do?pageNum=${pageNum}&bn=${bn}'">
			</td>
		</tr>
	</table>
</form>
</c:otherwise>
</c:choose>
</section>
</body>
</html>
