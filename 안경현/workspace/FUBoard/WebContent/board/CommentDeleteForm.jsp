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
	function ComDeleteSave(){
		if(document.ComDelForm.pass.value==""){
			alert("비밀번호를 입력하세요.");
			document.ComDelForm.pass.focus();
			return false;
		}
	}
</script>
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/board/css/ComDeleteFormstyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<section>
<b>글삭제</b>
<form method="POST" name="ComDelForm" action="${pageContext.request.contextPath}/board/CommentDeletePro.do?num=${num }pageNum=${pageNum}&bn=${bn}" 
	onsubmit="return ComDeleteSave()">
	<table class="ComDeletetable">
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
				onClick="document.location.href='${pageContext.request.contextPath}/board/list.do?num=${num }pageNum=${pageNum}&bn=${bn}'">
			</td>
		</tr>
	</table>
</form>
</section>
</body>
</html>
