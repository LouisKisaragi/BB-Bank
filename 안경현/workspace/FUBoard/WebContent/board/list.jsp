<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>



<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/board/css/liststyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<section>
<!-- <input type="hidden" name="bn" value="2">-->
<c:set var="bn" value="2"/>
<b>글목록(전체 글:${count})</b>
<p><c:if test="${login eq 1 }">(${logNick }님 안녕하세요)</c:if></p>
<table class="returnmain">
<tr>
<td><a href="${pageContext.request.contextPath }/board/main.do">메인으로가기</a></td>
</tr>
</table>

<table class="listwritebutton">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath}/board/writeForm.do?pageNum=${pageNum}&bn=${bn}">글쓰기</a>
		</td>
	</tr>
</table>
<c:if test="${count == 0}">
<table  class="listtable">
	<tr>
		<td>
			게시판에 저장된 글이 없습니다.
		</td>
	</tr>
</table>
</c:if>

<c:if test="${count > 0}">
<table class="listtable">
	<tr>
		<th id="num">번 호</th>
		<th id="preface">분류</th>
		<th id="title">제 목</th>
		<th id="writer">업로더</th>
		<th id="filename">파일이름</th>
		<th id="date">작성일</th>
		<th id="counter">조 회</th>
	</tr>
	<c:forEach var="article" items="${articleList}">
	<tr>
		<td align="center" width="50">
			<c:out value="${number}"/>
			<c:set var="number" value="${number - 1}"/>
		</td>
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
		<td class="titletd">
			<c:if test="${article.depth > 0}">
				<img src="${pageContext.request.contextPath}/board/images/level.gif"
					width="${5 * article.depth}">
				<img src="${pageContext.request.contextPath}/board/images/re.gif">
			</c:if>
			<c:if test="${article.depth == 0}">
				<img src="${pageContext.request.contextPath}/board/images/level.gif"
					width="${5 * article.depth}">
			</c:if>
			<a href="${pageContext.request.contextPath}/board/content.do?num=${article.num}&pageNum=${pageNum }&bn=${bn}">
				${article.subject}</a>
			<c:if test="${article.readcount >= 20}">
				<img src="${pageContext.request.contextPath}/board/images/hot.gif">
			</c:if>
		</td>
		<td>	
			<c:out value="${article.writer}"/><p>
			<c:set value="${article.ip }" var="ipcut"/>
			(
			<script language="javascript">
				var ipcutt="<c:out value="${ipcut}"/>";
				var ipc = ipcutt.split('.');
				document.write(ipc[0]);
				document.write(".");
				document.write(ipc[1]);
			</script>
			)
		</td>
		<td>${article.origin_filename }</td>
		<td>${article.regdate}</td>
		<td>${article.readcount}</td>
	</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${count > 0}"><!-- 게시글이 하나라도 있으면 페이징 처리 화면이 뜬다. -->
	<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1}"/><!-- 아래의 pageCount를 제어하는 삼항연산자. -->
	<c:set var="pageCount" value="${count / pageSize + imsi}"/><!-- 정수 부분이 중요하다. 정수 부분만 확인하자. -->
	<c:set var="pageBlock" value="${3}"/><!-- 페이징 처리에서 표시할 페이지의 수 -->
	<fmt:parseNumber var="lastPage" value="${pageCount}" integerOnly="true"/>
	<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock}" integerOnly="true"/>
	<c:set var="startPage" value="${result * pageBlock + 1}"/>
	<c:set var="endPage" value="${startPage + pageBlock - 1}"/>
	
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}"/>
	</c:if>
	
	<c:if test="${pageNum > 1}">
		<a href="${pageContext.request.contextPath}/board/list.do?pageNum=1&bn=${bn}">[처음]</a>
		<c:if test="${startPage > pageBlock}"> <!-- startPage 값이 pageBlock보다 클 때부터 작동한다. -->
			<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${startPage - pageBlock}&bn=${bn}">[이전]</a>
		</c:if>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${i}&bn=${bn}">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<c:choose>
			<c:when test="${(startPage + pageBlock) <= lastPage}">
				<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${startPage + pageBlock}&bn=${bn}">[다음]</a>
			</c:when>
		</c:choose>
	</c:if>
	
	<c:choose>
		<c:when test="${lastPage eq pageNum}"></c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${lastPage}&bn=${bn}">[끝]</a>
		</c:otherwise>
	</c:choose>
	
</c:if>
<br><br>

<!--<c:if test="${count > 0}">
	<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }"/>
	<c:set var="pageCount" value="${count / pageSize + imsi }"/>
	<c:set var="pageBlock" value="${3}"/>
	<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock}" 
	integerOnly="true"/>
	<c:set var="startPage" value="${result * pageBlock + 1 }"/>
	<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
	
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}"/>
	</c:if>
	
	<c:if test="${startPage > pageBlock}">
		<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${startPage - pageBlock }&bn=${bn}">이전</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${i}&bn=${bn}">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<a href="${pageContext.request.contextPath}/board/list.do?pageNum=${startPage + pageBlock }&bn=${bn }">다음</a>
	</c:if>
</c:if>-->
</section>
</body>
</html>
