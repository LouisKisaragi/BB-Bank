<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/board/css/contentstyle.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/board/script.js"></script>
</head>
<body>
<section>
<b>답글 달기</b>
<br>
<form method="post" name="content" action="${pageContext.request.contextPath}/board/comment.do?num=${num }&pageNum=${pageNum }&bn=${bn}&cnum=${cnum}"
	onsubmit="return contentSave()">

<table class="contenttable">
	<c:set var="articlec" value="${article}"/>
	<tr>
		<td colspan="2">${articlec.writer}<p>
		<c:set value="${articlec.ip }" var="ipcut"/>
			(
			<script language="javascript">
				var ipcutt="<c:out value="${ipcut}"/>";
				var ipc = ipcutt.split('.');
				document.write(ipc[0]);
				document.write(".");
				document.write(ipc[1]);
			</script>
			)</td>
		<td colspan ="10">${articlec.content }</td>
		<td colspan="2">${articlec.regdate }<p></td>
	</tr>		
	</table>
<table class="contenttable">
	<tr>
		<td rowspan="2">답글 달기</td>
		<th colspan="2">작성자</th>
		<td><input type="text" name="cwriter"></td>
		<th colspan="6">비밀번호</th>
		<td><input type="password" name="cpass"></td>
	</tr>
	<tr>
		<td colspan="9"><textarea name="ccomment" rows="4" cols="40"></textarea></td>
		<td><input type="submit"   style="WIDTH: 130pt; HEIGHT: 60pt" 	value="등록"></td>
	</tr>
</table>
</form>
</section>
</body>
</html>
