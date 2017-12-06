<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/bbboard4/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bbboard4/css/contentstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<section>
<b>글내용 보기</b>
<br>
<form>

<table class="contenttable">
	<tr>
		<th>글번호</th>
		<td>${article.num}</td>
		<th>조회수</th>
		<td>${article.readcount}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${article.writer}</td>
		<th>작성일</th>
		<td>${article.regdate}</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td colspan="3" class="contenttitle">${article.subject}</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td colspan="3" class="content">
		<pre>${article.content}</pre></td>
	</tr>
	
	<tr>
		<td colspan="4">
		<c:choose>

	
		<c:when test="${article.mem eq '1' and login eq 1 and article.writer eq logNick }">
		<input type="button" value="수 정" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/updateForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn} '">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="삭 제" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="답 글" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}'">&nbsp;&nbsp;&nbsp;&nbsp;
		</c:when>
			
		
		<c:when test="${article.mem eq'1' and login eq 1 and article.writer ne logNick }">
		<input type="button" value="답 글" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}'">&nbsp;&nbsp;&nbsp;&nbsp;
		</c:when>
		
		
		<c:when test="${article.mem eq '1' and login ne 1 }">
		<input type="button" value="답 글" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}'">&nbsp;&nbsp;&nbsp;&nbsp;
		</c:when>
		
		
		<c:when test="${article.mem eq '2' }">
		</c:when>
		
		
		<c:when test="${article.mem eq '0' and login eq 1}">
		<input type="button" value="답 글" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}'">&nbsp;&nbsp;&nbsp;&nbsp;
		</c:when>
		
		<c:otherwise>
		<input type="button" value="답 글" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}'">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="수 정" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/updateForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn} '">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="삭 제" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
		</c:otherwise>
		</c:choose>
		
		<input type="button" value="목 록" onClick="document.location.href='${pageContext.request.contextPath}/bbboard4/list.do?pagenum=${pageNum}'"> &nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		</tr>
</table> 
</form>
</section>
</body>
</html>