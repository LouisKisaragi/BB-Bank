<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<link href="${pageContext.request.contextPath}/bbboard2/css/style.css" rel="stylesheet" type="text/css"/>
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
<link href="${pageContext.request.contextPath}/bbboard2/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bbboard2/css/deleteFormstyle.css" rel="stylesheet" type="text/css"/>
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
	<c:when test="${mem eq 1 and login eq 1 and logNick eq writer }">
				정말로 삭제하시겠습니까?
				<a href="${pageContext.request.contextPath}/bbboard2/deletePro.do?pageNum=${pageNum}&bn=${bn}&num=${num}">네</a>
		</c:when>
<c:otherwise>
<form method="POST" name="delForm" action="${pageContext.request.contextPath}/bbboard2/deletePro.do?pageNum=${pageNum}&bn=${bn}&num=${num}" 
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
				onClick="document.location.href='${pageContext.request.contextPath}/bbboard2/list.do?pageNum=${pageNum}&bn=${bn}'">
			</td>
		</tr>
	</table>
</form>
</c:otherwise>
</c:choose>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>
