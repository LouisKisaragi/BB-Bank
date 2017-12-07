<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글작성</title>
<script src="${pageContext.request.contextPath}/bbadmin/script.js"></script>
<link href="${pageContext.request.contextPath}/bbadmin/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbadmin/css/writeFormstyle.css" rel="stylesheet" type="text/css"/>
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
<article>
<b>공지사항쓰기</b>
<br></br>
<form method="post" name="writeForm" action="${pageContext.request.contextPath}/bbboard1/writePro.do?pageNum=${pageNum }&bn=${bn}"  enctype="multipart/form-data"
	onsubmit="return writeSave();">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="step" value="${step}">
	<input type="hidden" name="depth" value="${depth}">
	<input type="hidden" name="mem" value=2>
	<input type="hidden" name="pass" value="${adminPass }">
	<input type="hidden" name="writer" value="${adminId }">
	<table class="board">
		<tr>
			<td>
				<select name="bn">
						<option value=1>공지사항</option>
						<option value=2>Q&A</option>
						<option value=4>자유게시판</option>
						<option value=5>야구</option>
				</select> 
			</td>
			
			<td class="attr">제  목</td>
			<td colspan="2">
				<c:if test="${num == 0}">
				<input class="input" type="text" name="subject">
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="attr">내용</td>
			<td colspan="2">
				<textarea name="content" rows="13" cols="40"></textarea>
			</td>
		</tr>
		<tr>
			<td class="attr">파일</td>
			<td colspan="2">
					<input type="file" name="filename">
			</td>
		</tr>		
		<tr>
			<td colspan="3" class="attr">
				<input type="submit"  value="글쓰기">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" onclick="window.location='${pageContext.request.contextPath}/bbboard1/list.do?pageNum=${pageNum }&bn=${bn}'">
			</td>
		</tr>
	</table>
</form>
</article>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>