<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script src="${pageContext.request.contextPath}/bbboard5/script.js"></script>
<link href="${pageContext.request.contextPath}/bbboard5/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard5/css/liststyle.css" rel="stylesheet" type="text/css"/>
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

<% request.setCharacterEncoding("UTF-8"); %>
<%-- 제목 --%>
<b>
<c:choose>
	<c:when test="${prefaces eq 'a' or prefaces eq 'b' or prefaces eq 'c' or prefaces eq 'd'}">
		${prefaces}
	</c:when>
	<c:otherwise>
		전체
	</c:otherwise>
</c:choose>
 글목록 : (전체 글 : ${count})</b>
 
<%-- 검색어 --%>
<c:if test="${keywords ne null}">
	<br>검색어 : ${keywords}
</c:if>

<table>
	<tr>
		<td>
			<!-- 해당하는 preface일 경우, 링크를 제거하자 -->
			<a href="${pageContext.request.contextPath}/bbboard5/list.do">[전체]</a>
			<a href="${pageContext.request.contextPath}/bbboard5/list.do?prefaces=a">[KBO]</a>
			<a href="${pageContext.request.contextPath}/bbboard5/list.do?prefaces=b">[MLB]</a>
			<a href="${pageContext.request.contextPath}/bbboard5/list.do?prefaces=c">[NPB]</a>
			<a href="${pageContext.request.contextPath}/bbboard5/list.do?prefaces=d">[기타]</a>
		</td>
	</tr>
</table>
<table class="listwritebutton"> <!-- css에 적용될것들을 구분하기 위해서 class를 써놓았다. -->
	<tr>
		<td><a href="${pageContext.request.contextPath}/bbboard5/writeForm.do">글쓰기</a>
		</td>
	</tr>
</table>

<c:if test="${count == 0}">
<table class="listtable">
	<tr><td>글이 없습니다.</td></tr>
</table>
</c:if>

<c:if test="${count > 0}">
<table class="listtable">
	<tr>
		<th id="num">번호</th>	<!-- th id는 스타일 관련 id, css파일을 알아보자. -->
		<th id="bun">분류</th>
		<th id="title">제목</th>
		<th id="writer">작성자</th>
		<th id="date">작성일</th>
		<th id="counter">조회</th>
	</tr>
	
	<!-- 공지 -->
	<c:forEach var="notice" items="${noticeList}">
	<tr>
		<!-- 글번호 -->
		<td align=center width=50>
			
		</td>
		<!-- 분류 -->
		<td>
			<b>공지</b>
		</td>
		<!-- 제목 -->
		<td class="titleid">
			<a href="${pageContext.request.contextPath}/bbboard5/content.do?num=${notice.num}&pageNum=${currentPage}">${notice.subject}</a>
		</td>
		<!-- 글쓴이(IP) -->
		<td>${notice.writer}</td>
		<!-- 날짜 -->
		<td>${notice.regdate}</td>
		<!-- 조회수 -->
		<td>${notice.readcount}</td>
	</tr>
	</c:forEach>
	
	<!-- 내용 -->
	<c:forEach var="article" items="${articleList}">
	<tr>
		<!-- 글번호 -->
		<td align=center width=50>
			<c:out value="${number}"/>
			<c:set var="number" value="${number - 1}"/>
		</td>
		<!-- 분류 -->
		<td>
			<b>
			<c:choose>
			<c:when test="${article.preface eq 'a' }">
				[KBO]
				</c:when>
				<c:when test="${article.preface eq 'b' }">
				[MLB]
				</c:when>
				<c:when test="${article.preface eq 'c' }">
				[NPB]
				</c:when>
				<c:when test="${article.preface eq 'd' }">
				[기타]
				</c:when>
				</c:choose>
				</b>
		</td>
		<!-- 제목 -->
		<td class="titleid">
			<c:if test="${article.depth > 0}">
				<img src="${pageContext.request.contextPath}/bbboard5/images/level.gif" width="${5*article.depth}"/>
				<img src="${pageContext.request.contextPath}/bbboard5/images/re.gif"/>
			</c:if>
			<c:if test="${article.depth == 0}">
				<img src="${pageContext.request.contextPath}/bbboard5/images/level.gif" width="${5*article.depth}"/>
			</c:if>
			<a href="${pageContext.request.contextPath}/bbboard5/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a>
			<c:if test="${article.readcount >= 20}"> <!-- 조회수 20 이상일 때 -->
				<img src="${pageContext.request.contextPath}/bbboard5/images/hot.gif"/>
			</c:if>			
		</td>
		<!-- 글쓴이(IP) -->
		<td>${article.writer}(${article.ip})</td>
		<!-- 날짜 -->
		<td>${article.regdate}</td>
		<!-- 조회수 -->
		<td>${article.readcount}</td>
	</tr>
	</c:forEach>
</table>
</c:if>

<!-- 페이징 처리 -->
<c:if test="${count > 0}"><%-- 게시글이 하나라도 있으면 페이징 처리 화면이 뜬다. --%>
	<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1}"/><%-- 아래의 pageCount를 제어하는 삼항연산자. --%>
	<c:set var="pageCount" value="${count / pageSize + imsi}"/><%-- 정수 부분이 중요하다. 정수 부분만 확인하자. --%>
	<c:set var="pageBlock" value="${3}"/><%-- 페이징 처리에서 표시할 페이지의 수 --%>
	<fmt:parseNumber var="lastPage" value="${pageCount}" integerOnly="true"/>
	<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock}" integerOnly="true"/>
	<c:set var="startPage" value="${result * pageBlock + 1}"/>
	<c:set var="endPage" value="${startPage + pageBlock - 1}"/>
	
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}"/>
	</c:if>
	
	<%-- 처음 --%>
	<c:if test="${pageNum > 1}">
		<c:choose> <%-- 키워드가 있을때, 없을때를 구분해야 한다. --%>
			<%-- 키워드가 없을 때 --%>
			<c:when test="${keywords eq null}">
				<%-- 처음 버튼 --%>
				<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=1&prefaces=${prefaces}">[처음]</a>
				<%-- 이전 버튼 --%>
				<c:if test="${startPage > pageBlock}"> <%-- startPage 값이 pageBlock보다 클 때부터 작동한다. --%>
					<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${startPage - pageBlock}&prefaces=${prefaces}">[이전]</a>
				</c:if>
			</c:when>
			<%-- 키워드가 있을 때 --%>
			<c:otherwise>
				<%-- 처음 버튼 --%>
				<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=1&prefaces=${prefaces}&keywords=${keywords}&jogun=${jogun}">[처음]</a>
				<%-- 이전 버튼 --%>
				<c:if test="${startPage > pageBlock}"> <%-- startPage 값이 pageBlock보다 클 때부터 작동한다. --%>
					<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${startPage - pageBlock}&prefaces=${prefaces}&keywords=${keywords}&jogun=${jogun}">[이전]</a>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:if>
	
	<%-- 중간 --%>
	<%-- 해당하는 페이지일 경우, 숫자에 링크를 떼는 등 차별화한다. --%>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:choose> <%-- 키워드가 있을때, 없을때를 구분해야 한다. --%>
			<%-- 키워드가 없을 때 --%>
			<c:when test="${keywords eq null}">
				<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${i}&prefaces=${prefaces}">[${i}]</a>
			</c:when>
			<%-- 키워드가 있을 때 --%>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${i}&prefaces=${prefaces}&keywords=${keywords}&jogun=${jogun}">[${i}]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<%-- 다음 --%>
	<c:if test="${endPage < pageCount}">
		<c:choose> <%-- 키워드가 있을때, 없을때를 구분해야 한다. --%>
			<%-- 키워드가 없을 때 --%>
			<c:when test="${keywords eq null}">
				<c:choose>
					<c:when test="${(startPage + pageBlock) <= lastPage}">
						<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${startPage + pageBlock}&prefaces=${prefaces}">[다음]</a>
					</c:when>
				</c:choose>
			</c:when>
			<%-- 키워드가 있을 때 --%>
			<c:otherwise>
				<c:choose>
					<c:when test="${(startPage + pageBlock) <= lastPage}">
						<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${startPage + pageBlock}&prefaces=${prefaces}&keywords=${keywords}&jogun=${jogun}">[다음]</a>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:if>
	
	<%-- 끝 --%>
	<c:choose>
		<c:when test="${lastPage eq pageNum}"></c:when>
		<c:otherwise>
			<c:choose> <%-- 키워드가 있을때, 없을때를 구분해야 한다. --%>
				<%-- 키워드가 없을 때 --%>
				<c:when test="${keywords eq null}">
					<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${lastPage}&prefaces=${prefaces}">[끝]</a>
				</c:when>
				<%-- 키워드가 있을 때 --%>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=${lastPage}&prefaces=${prefaces}&keywords=${keywords}&jogun=${jogun}">[끝]</a>
				</c:otherwise>
			</c:choose>	
		</c:otherwise>
	</c:choose>
	
</c:if><br><br>
<!-- form -> list.do가 액션으로 지정되도록 -->
<!-- script.js에 넣거나 해서 아무것도 입력안하면 작동안하도록 동작시켜라 -->
<form method=post name=search action="${pageContext.request.contextPath}/bbboard5/list.do?&prefaces=${prefaces}" onsubmit="return searchGuard()">
	<table>
		<tr>
			<td>
				<select name="jogun">
				<option value="a" selected>제목</option>
				<option value="b">내용</option>
				<option value="c">제목+내용</option>
				<option value="d">작성자</option>
				</select>
			</td>
			<td><input type=text name=keywords></td>
			<td><input type=submit value=찾기></td>
		</tr>
	</table>
</form>
<!-- 
변수체크란<br>
입력한 키워드 : [${keywords}]<br>
분류 : ${prefaces}<br>
bn : ${bn}<br>
조건 : ${jogun}<br>
세션 값 : ${tempdata}<br>
페이지 번호 : ${pageNum}<br>-->
<c:if test="${keywords ne null}">
크으으
</c:if>

<%-- <br><br>
prefaces : ${prefaces}<br>
pageNum : ${pageNum}<br>
전체 글 수 count : ${count}<br>
한 페이지당 글 개수 pageSize : ${pageSize}<br>
count % pageSize : ${count % pageSize}<br>
imsi 값 결정 공식 : count % pageSize가 0이면 0, 그 외의 값이면 1로 나온다.<br>
imsi : ${imsi}<br>
pageCount 공식 : count / pageSize + imsi<br>
너도왜소수? pageCount : ${pageCount}<br>
시작 번호 startRow : ${startRow}<br>
끝 번호 endRow : ${endRow}<br>
목록에 뜰 번호 개수 pageBlock : ${pageBlock}<br>
result : ${result}<br>
startPage : ${startPage}<br>
endPage : ${endPage}<br> <!-- 간혹 소수가 뜨는 이유는 if test="${endPage > pageCount} 조건에 걸려서이다. -->
lastPage : ${lastPage}<br> --%>

</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>