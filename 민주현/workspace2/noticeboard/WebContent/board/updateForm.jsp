<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script src="${pageContext.request.contextPath}/board/script.js"></script>
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/board/css/writeFormstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<section>
<b>글수정</b>
	
	<form method="post" name="writeForm" action="${pageContext.request.contextPath}/board/updatePro.do?pageNum=${pageNum}&bn=${bn}"
		enctype="multipart/form-data" onsubmit="return writeSave();">
			<table class="board">
				<tr>
					<td>					
					<select name="preface">
						<option value="">카테고리</option>
						<c:if test="${article.preface eq '공지'}">
						<option value="공지" selected>공지</option>
						<option value="점검">점검</option>
						<option value="이벤트">이벤트</option>
						<option value="발표">발표</option>
						</c:if>
						<c:if test="${article.preface eq '점검'}">
						<option value="공지">공지</option>
						<option value="점검" selected>점검</option>
						<option value="이벤트">이벤트</option>
						<option value="발표">발표</option>
						</c:if>
						<c:if test="${article.preface eq '이벤트'}">
						<option value="공지">공지</option>
						<option value="점검">점검</option>
						<option value="이벤트" selected>이벤트</option>
						<option value="발표">발표</option>
						</c:if>
						<c:if test="${article.preface eq '발표'}">
						<option value="공지">공지</option>
						<option value="점검">점검</option>
						<option value="이벤트">이벤트</option>
						<option value="발표"selected>발표</option>
						</c:if>
					</select>
					</td>
					<td class="attr">제목</td>
					<td>
						<input class="input" type="text" name = "subject" value="${article.subject}">
					</td>
				</tr>
				<tr>
					
					<td class="attr">작성자</td>
					<td colspan="2">${article.writer}
						<input type="hidden" name="num" value="${ article.num }">
						<input type="hidden" name="writer" value="${article.writer}">
					</td>
				</tr>
				<tr>
					<td class="attr">비밀번호</td>
					<td colspan="2">
						<input type="password" name="pass">
					</td>
				</tr>
				<tr>
					<td class="attr">내용</td>
					<td colspan="2">
						<textarea name="content" rows="13" cols="50">${article.content}</textarea>
					</td>
				</tr>
				<tr>
					<td class="attr">파일 교체</td>
					<td colspan="2">
						
					<c:if test="${article.filetype.startsWith('image/')}">
						<img src="../upload/${article.server_filename}" width="50" height="50">
					</c:if>
						<input type="file" name="fileName">
					</td>
				</tr>
				<tr>
					<td colspan="3" class="attr">
						<input type="submit"  value="글수정">
						<input type="reset" value="다시작성">
						<input type="button" value="목록보기" 
								onclick="window.location='${pageContext.request.contextPath}/board/list.do?pageNum=${pageNum}&bn=${bn}'">
					</td>
				</tr>
			</table>
	</form>
</section>
</body>
</html>