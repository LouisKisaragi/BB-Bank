<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script src="${pageContext.request.contextPath}/bbboard3/script.js"></script>
<link href="${pageContext.request.contextPath}/bbboard3/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard3/css/writeFormstyle.css" rel="stylesheet" type="text/css"/>
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
<b>글수정</b>
<article>
	<form method="post" name="writeForm" action=
		"${pageContext.request.contextPath}/bbboard3/updatePro.do?pageNum=${pageNum}&bn=${bn}" onsubmit="return writeSave()"  enctype="multipart/form-data">
		<table class="board">
		<tr>
			<td>
				<select name="preface">
					<option value="problem">문제</option>
					<option value="solution">해결</option>
				</select>
			</td>
			<td class="attr">제  목</td>
			<td COLSPAN="1">
				<input class="input" type="text" name="subject" value="${article.subject }">
			</td>
		</tr>
		<tr>
			<td class="attr">업로더</td>
			<td COLSPAN="2">
				<input type="hidden" name="num" value="${article.num}">
				<input type="hidden" name="writer" value="${article.writer}">
				${article.writer }
			</td>
		</tr>
		<tr>
			<td class="attr">비밀번호</td>
			<td COLSPAN="2">
				<input type="password" name="pass">
			</td>
		</tr>
		<tr>
			<td class="attr">내 용</td>
			<td COLSPAN="2">
				<textarea name="content" rows="13" cols="40">${article.content }</textarea>
			</td>
		</tr>
		<tr>
			<td class="attr">파일업로드</td>
			<td COLSPAN="2">
				<input type="file"  name="file">
			</td>
		</tr>
		<tr>
			<td colspan="3" class="attr">
				<input type="submit" value="글수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" 
				OnClick="window.location='${pageContext.request.contextPath}/bbboard3/list.do?pageNum=${pageNum}&bn=${bn}'">
			</td>
		</tr>
		</table>
	</form>
</article>
</section>
<div id="footer">
<%@ include file="../bbadmin/adfooter.jsp" %>
</div>
</body>
</html>
