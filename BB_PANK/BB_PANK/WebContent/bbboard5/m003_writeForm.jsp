<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
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
<article>
<b>글쓰기</b>
<br><br>
<form method=post name=writeForm action="${pageContext.request.contextPath}/bbboard5/writePro.do" onsubmit="return writeSave()">
	<input type=hidden name=num value="${num}">
	<input type=hidden name=ref value="${ref}">
	<input type=hidden name=step value="${step}">
	<input type=hidden name=depth value="${depth}">
	<input type=hidden name=bn value="5"> <%-- 게시판 번호, 글쓰기 화면에서 자동으로 지정된다. --%>
	<input type=hidden name=mem value="0"> <%-- 멤버 변수, 글쓰기 화면에서 자동으로 지정된다. --%>
	
	<table class=board>
		<tr>
			<td class="attr">분류</td>
			<td>
				<select name="preface">
				<option value="a" selected>[KBO]</option>
				<option value="b">[MLB]</option>
				<option value="c">[NPB]</option>
				<option value="d">[기타]</option>
				</select>
			</td>
		</tr>
		<c:choose>
			<c:when test="${login ne 1 }">
				<tr>
					<td class="attr">이름</td>
					<td>
					<input type=text name="writer">
					</td>
				</tr>
				<tr>
					<td class="attr">패스워드</td>
					<td>
				<input type=password name="pass">
			</td>
		</tr>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="writer" value="${logNick }">
				<input type="hidden" name="pass" value="${logPass }">
			</c:otherwise>
		</c:choose>
		
			
		<tr>
			<td class=attr>제목</td>
			<td>
				<input class="input" type="text" name="subject">
			</td>
		</tr>
		<tr>
			<td class="attr">내용</td>
			<td><textarea name=content rows=13 cols=50></textarea></td>
		</tr>
		<tr>
			<td colspan=2 class="attr">
				<input type=submit value="글쓰기">
				<input type=reset value="다시작성">
				<input type=button value="목록" onclick="window.location='${pageContext.request.contextPath}/bbboard5/list.do'">
			</td>
		</tr>
	</table>
</form>
<!-- 임의의 변수<br>
${tempdata}<br>-->
</article>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>