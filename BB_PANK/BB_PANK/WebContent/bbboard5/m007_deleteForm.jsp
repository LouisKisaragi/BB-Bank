<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 삭제</title>
<script>
	function deleteSave() {
		if(document.delForm.pass.value=="") {
			alert("패스워드를 입력하시오");
			document.delForm.pass.focus();
			return false;
		}
	}
</script>
<link href="${pageContext.request.contextPath}/bbboard5/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard5/css/deleteFormstyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<section>
<b>글삭제</b>
<form method=post name=delForm action="${pageContext.request.contextPath}/deletePro.do?pageNum=${pageNum}" onsubmit="return deleteSave()">
<table class=deletetable>
	<tr>
		<td><b>패스워드를 입력해 주시오</b></td>
	</tr>
	<tr>
		<td>패스워드 : <input type="password" name="pass"><input type="hidden" name="num" value="${num}"></td>
	</tr>
	<tr>
		<td><input type="submit" value="삭제"> <input type=button value="목록" onClick="document.location.href='${pageContext.request.contextPath}/list.do?pageNum=${pageNum}'">
	</tr>
</table>
</form>
</section>
</body>
</html>