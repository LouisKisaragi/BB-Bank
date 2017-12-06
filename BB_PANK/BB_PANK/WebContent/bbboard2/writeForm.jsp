<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script src="${pageContext.request.contextPath}/bbboard2/script.js"></script>
<link href="${pageContext.request.contextPath}/bbboard2/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard2/css/writeFormstyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<section>
<article>
<b>게시글 작성</b>
<br></br>
<form method="post" name="writeForm" action="${pageContext.request.contextPath}/bbboard2/writePro.do?pageNum=${pageNum }&bn=${bn}"
	onsubmit="return writeSave()" enctype="multipart/form-data">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="step" value="${step}">
	<input type="hidden" name="depth" value="${depth}">
	<input type="hidden" name="bn" value="${bn}">	
	<c:choose>
		<c:when test="${login eq 1 }">
		<input type="hidden" name="mem" value=1><!-- 로그인상태일때 -->
		</c:when>
		<c:otherwise>
		<input type="hidden" name="mem" value="0"><!-- 비로그인상태일때 -->
		</c:otherwise>
	</c:choose>
	<table class="board">
		<tr>
			<td>
				<select name="preface">
					<option value="problem">문제</option>
					<option value="solution">해결</option>
				</select>
			</td>
			<td class="attr">제  목</td>
			<td>
				<c:if test="${num == 0}">
					<input class="input" type="text" name="subject">
				</c:if>
				<c:if test="${num != 0}">
					<input class="input" type="text" name="subject" value="[답변]"> 
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="attr">업로더</td>
			<td COLSPAN="2">
			<c:choose>
				<c:when test="${login eq 1 }">${logNick }<input type="hidden" name="writer" value="${logNick }"></c:when>
				<c:otherwise><input type="text" name="writer"></c:otherwise>
			</c:choose>
				</td>
		</tr>
			<c:choose>
				<c:when test="${login eq 1 }"><input type="hidden" name="pass" value="${logPass }"></c:when>
				<c:otherwise>
					<tr>
						<td class="attr">비밀번호</td>
						<td COLSPAN="2">
							<input type="password" name="pass">
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		<tr>
			<td class="attr">내 용</td>
			<td COLSPAN="2">
				<textarea name="content" rows="13" cols="40"></textarea>
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
				<input type="submit" value="업로드">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" 
				OnClick="window.location='${pageContext.request.contextPath}/bbboard2/list.do?pageNum=${pageNum }&bn=${bn}'">
			</td>
		</tr>
		</table>
</form>
</article>
</section>
</body>
</html>
