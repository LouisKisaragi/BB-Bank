<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script src="${pageContext.request.contextPath}/bbadmin/script.js"></script>
<link href="${pageContext.request.contextPath}/bbboard1/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard1/css/writeFormstyle.css" rel="stylesheet" type="text/css"/>
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
<b>글쓰기</b>
<br></br>
<form method="post" name="writeForm" action="${pageContext.request.contextPath}/bbboard1/writePro.do?pageNum=${pageNum }&bn=${bn}"  enctype="multipart/form-data"
	onsubmit="return writeSave();">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="step" value="${step}">
	<input type="hidden" name="depth" value="${depth}">
	<input type="hidden" name="mem" value="2">
		
	<table class="board">
		<tr>
			<td>
				
				<select name="preface" >
						<option value="">카테고리</option>
						<option value="공지">공지</option>
						<option value="점검">점검</option>
						<option value="이벤트">이벤트</option>
						<option value="발표">발표</option>
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
			<td class="attr">작성자</td>
			<td colspan="2">
			<c:choose>
				<c:when test="${login eq 1}">${logNick}<input type="hidden" name="writer" value="${logNick}"></c:when>
				<c:otherwise><input type="text" name="writer"></c:otherwise>
			</c:choose>
			</td>
		</tr>
		<c:choose>
				<c:when test="${login eq 1 }"><input type="hidden" name="pass" value="${logPass }"></c:when>
				<c:otherwise>
		<tr>
			<td class="attr">비밀번호</td>
			<td colspan="2">
				<input type="password" name="pass">
			</td>
		</tr>
		</c:otherwise>
			</c:choose>
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