<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link href="${pageContext.request.contextPath}/bbboard4/css/style.css"
	rel="stylesheet" type="text/css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script>
	function deleteSave() {
		if (document.delForm.pass.value == "") {
			alert("비밀번호를 입력하세요");
			document.delForm.pass.focus();
			return false;
		}
	}
</script>
<link href="${pageContext.request.contextPath}/freeboard.css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/freeboard.css/deleteFormstyle.css" rel="stylesheet" type="text/css"/>
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
<form method="POST" name="delForm" action="${pageContext.request.contextPath}/bbboard4/deletePro.do?pageNum=${pageNum}" onsubmit="return deleteSave()">
<table class="deletetable">
	<tr>
		<td><b>비밀번호를 입력해 주세요.</b></td>
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
		<input type="button" value="목록" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/list.do?pageNum=${pageNum}'">
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