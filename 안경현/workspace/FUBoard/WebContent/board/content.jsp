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
<b>자료 보기</b>
<br>
<form method="post" name="commentForm" action="${pageContext.request.contextPath}/board/comment.do?num=${num }&pageNum=${pageNum }&bn=${bn}"
	onsubmit="return commentSave()">
<input type="hidden" name="num" value="${num}">
<table  class="contenttable">
	<tr>
		<th>글번호</th>
		<td colspan="1">${article.num}</td>
		<th>업로더</th>
		<td colspan="5">	${article.writer}<p>
		<c:set value="${article.ip }" var="ipcut"/>
			(
			<script language="javascript">
				var ipcutt="<c:out value="${ipcut}"/>";
				var ipc = ipcutt.split('.');
				document.write(ipc[0]);
				document.write(".");
				document.write(ipc[1]);
			</script>
			)</td>
		<th>작성일</th>
		<td>${article.regdate}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${article.readcount}</td>	
		<th>글제목</th>
		<td class="preface">
			<c:set var="preface" value="${article.preface }"/>
			<c:choose>
				<c:when test="${article.preface eq 'problem' }">
				[문제]
				</c:when>
				<c:when test="${article.preface eq 'solution' }">
				[해결]
				</c:when>
			</c:choose>
		</td>
		<td colspan="6" class="contenttitle">${article.subject}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="10" class="content">
		<pre>${article.content}</pre></td>
	</tr>
	<tr>
		<th>미리보기</th>
		<td colspan="10">
			<c:choose>
				<c:when test="${article.server_filename !='' || article.server_filename ne null}">
				<img src="../upload/${article.server_filename }"  style="max-width:300px; height:auto;">
				</c:when>
				<c:otherwise>
				[미리보기 없음]
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<th>파일이름</th>
		<td colspan ="8"><a href="${pageContext.request.contextPath}/board/download.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}">
				${article.origin_filename}</a></td>
		<td><fmt:formatNumber value="${article.filesize/1024}" pattern="#,###"/>KB</td>
	</tr>
	<tr>
		<td colspan="10">
		<input type="button" value="수 정" onClick="document.location.href='${pageContext.request.contextPath}/board/updateForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn }">
			&nbsp;&nbsp;
		<input type="button" value="삭 제" onClick="document.location.href='${pageContext.request.contextPath}/board/deleteForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}">
			&nbsp;&nbsp;
		<input type="button" value="답 글" onClick="document.location.href='${pageContext.request.contextPath}/board/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}&pageNum=${pageNum }&bn=${bn}'">
			&nbsp;&nbsp;
		<input type="button" value="목 록" onClick="document.location.href='${pageContext.request.contextPath}/board/list.do?pageNum=${pageNum}&bn=${bn }'">
			&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<th colspan="10">댓글</th>
	</tr>
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
		<td colspan="6"></td>
		<td colspan="2">여기에 날짜</td>
	</tr>
	<tr>
		<td colspan ="10">여기에 내용</td>
	</tr>
	<tr>
		<td colspan="9"></td>
		<td colspan="1"><input type="button" value="답글"></td>
	</tr>
	<tr>
		<th colspan="2">작성자</th>
		<td><input type="text" name="writer"></td>
		<th colspan="6">비밀번호</th>
		<td><input type="password" name="pass"></td>
	</tr>
	<tr>
		<td colspan="9"><textarea name="content" rows="4" cols="40"></textarea></td>
		<td><input type="submit"   style="WIDTH: 130pt; HEIGHT: 60pt" 	value="등록"></td>
	</tr>
</table>
</form>
</section>
</body>
</html>
