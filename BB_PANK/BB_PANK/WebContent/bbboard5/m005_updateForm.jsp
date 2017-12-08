<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
<script src="${pageContext.request.contextPath}/bbboard5/script.js"></script>
<link href="${pageContext.request.contextPath}/bbboard5/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard5/css/writeFormstyle.css" rel="stylesheet" type="text/css"/>
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
<b>글 수정</b>
<form method=post name=writeForm action="${pageContext.request.contextPath}/bbboard5/updatePro.do?pageNum=${pageNum}" onsubmit="return writeSave()">
	<table class=board>
		<tr>
			<td class="attr">이름</td>
			<td>${article.writer}
				<input type=hidden name=num value="${article.num}">
				<input type=hidden name=writer value="${article.writer}">
			</td>
		</tr>
		<tr>
			<td class="attr">패스워드</td>
			<td>
				<input type=password name="pass">
			</td>
		</tr>
		<tr>
			<td class=attr>제목</td>
			<td>
				<input class="input" type="text" name="subject" value="${article.subject}">
			</td>
		</tr>
		<tr>
			<td class="attr">내용</td>
			<td><textarea name=content rows=13 cols=50>${article.content}</textarea></td>
		</tr>
		<tr>
			<td colspan=2 class="attr">
				<input type=submit value="글수정">
				<input type=reset value="다시작성">
				<input type=button value="목록" onclick="window.location='${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${pageNum}'">
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