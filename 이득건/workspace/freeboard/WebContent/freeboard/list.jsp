<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/freeboard/css/style.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/freeboard/css/liststyle.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<section>
		<%
			request.setCharacterEncoding("UTF-8");
		%>
		<b> <c:choose>
				<c:when
					test="${prefaces eq '1' or prefaces eq '2' or prefaces eq '3' or prefaces eq '4'}">
		${prefaces}
	</c:when>
				<c:otherwise>
		전체
	</c:otherwise>
			</c:choose> 
			글목록(전체 글:${count})</b>
		<!-- 검색어 -->
		<c:if test="${keywords ne null}">
			<br>검색어 : ${keywords}
</c:if>
		<table>
			<tr>
				<td>
				<!-- 해당하는 preface일 경우, 링크를 제거하자 -->
				<a
					href="${pageContext.request.contextPath}/board/list.do?bn=4&prefaces=0">[전체목록]
				</a> <a
					href="${pageContext.request.contextPath}/board/list.do?bn=4&prefaces=1">[연예]
				</a> <a
					href="${pageContext.request.contextPath}/board/list.do?bn=4&prefaces=2">[정치]
				</a> <a
					href="${pageContext.request.contextPath}/board/list.do?bn=4&prefaces=3">[스포츠]
				</a> <a
					href="${pageContext.request.contextPath}/board/list.do?bn=4&prefaces=4">[뻘글]
				</a></td>
			</tr>
		</table>
		<table class="listwritebutton">
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/board/writeForm.do?">글쓰기</a>
				</td>
			</tr>
		</table>
		<c:if test="${count == 0}">
			<table class="listtable">
				<tr>
					<td>게시판에 저장된 글이 없습니다.</td>
				</tr>
			</table>
		</c:if>

		<c:if test="${count > 0}">
			<table class="listtable">
				<tr>
					<th id="num">번 호</th>
					<th id="category">구 분</th>
					<th id="title">제 목</th>
					<th id="writer">작성자</th>
					<th id="date">작성일</th>
					<th id="counter">조 회</th>
					<th id="ip">조 회</th>
				</tr>
				<c:forEach var="article" items="${articleList}">
					<tr>
						<!-- 글 번호 -->
						<td align="center" width="50"><c:out value="${number}" /> <c:set
								var="number" value="${number - 1}" /></td>
						<!-- 구분 -->
						<td>
						<c:choose>
						<c:when test="${article.preface==1}">
						연예
						</c:when>
						<c:when test="${article.preface==2}">
						정치
						</c:when>
						<c:when test="${article.preface==3}">
						스포츠
						</c:when>
						<c:when test="${article.preface==4}">
						뻘글
						</c:when>
						</c:choose>
						</td>
						<!-- 제목 -->
						<td class="titletd"><c:if test="${article.depth > 0}">
								<img
									src="${pageContext.request.contextPath}/freeboard/images/level.gif"
									width="${5 * article.depth}">
								<img
									src="${pageContext.request.contextPath}/freeboard/images/re.gif">
							</c:if> <c:if test="${article.depth == 0}">
								<img
									src="${pageContext.request.contextPath}/freeboard/images/level.gif"
									width="${5 * article.depth}">
							</c:if> <a
							href="${pageContext.request.contextPath}/board/content.do?num=
			${article.num}&pageNum=${currentPage}&bn=4">
								${article.subject}</a> <c:if test="${article.readcount >= 20}">
								<img
									src="${pageContext.request.contextPath}/freeboard/images/hot.gif">
							</c:if></td>
						<!-- 작성자 -->
						<td>${article.writer}</td>
						<!-- 날짜 -->
						<td>${article.regdate}</td>
						<!-- 조회수 -->
						<td>${article.readcount}</td>
						<!-- IP -->
						<td>${article.ip}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<!--페이징 처리  -->
		<c:if test="${count > 0}">
			<!-- 게시글이 하나라도 있으면 페이징 처리 화면이 뜬다. -->
			<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }" />
			<!-- 아래의 pageCount를 제어하는 삼항연산자. -->
			<c:set var="pageCount" value="${count / pageSize + imsi }" />
			<!-- 정수 부분이 중요하다. 정수 부분만 확인하자. -->
			<c:set var="pageBlock" value="${3}" />
			<!-- 페이징 처리에서 표시할 페이지의 수 -->
			<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock}"
				integerOnly="true" />
			<c:set var="startPage" value="${result * pageBlock + 1 }" />
			<c:set var="endPage" value="${startPage + pageBlock - 1 }" />

			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>

			<c:if test="${startPage > pageBlock}">
				<!-- startPage 값이 pageBlock보다 클 때부터 작동한다. -->
				<a
					href="${pageContext.request.contextPath}/board/list.do?pageNum=
${startPage - pageBlock }&prefaces=${prefaces}">이전</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a
					href="${pageContext.request.contextPath}/board/list.do?pageNum=${i}&prefaces=${prefaces}">[${i}]</a>
			</c:forEach>

			<c:if test="${endPage < pageCount}">
				<a
					href="${pageContext.request.contextPath}/board/list.do?pageNum=${startPage + pageBlock }&prefaces=${prefaces}">다음</a>
			</c:if>
		</c:if>
		<p>
			<!-- 검색기능  -->
		<form method=post name=search
			action="${pageContext.request.contextPath}/board/list.do?&prefaces=${prefaces}&bn=${bn}"
			onsubmit="return searchGuard()">
			<table>
				<tr>
				
					<td><select name="condition">
							<option value="1" selected>작성자
							<option value="2">제목
							<option value="3">내용
							<option value="4">제목+내용
					</select></td>
					<td><input type=text name=keywords></td>
					<td><input type=submit value=찾기></td>
				</tr>
			</table>
		</form>
		
		prefaces: ${prefaces}<br>
		article.preface: ${article.preface}
	</section>
</body>
</html>