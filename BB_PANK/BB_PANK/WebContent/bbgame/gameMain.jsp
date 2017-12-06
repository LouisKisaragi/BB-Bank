<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="script.js"></script>
<link href="${pageContext.request.contextPath}/bbgame/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bbgame/css/gamestyle.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>응원</title>
</head>
<body>
<section>

팀 응원<p>
<c:if test="${login eq 1}">
	${logNick }님의 보유 포인트는 ${logPoint }점 입니다.
</c:if>

<c:if test="${count == 0}">


<table class="gameMain">

	<tr>
		<td>
			게시판에 저장된 글이 없습니다.
		</td>
	</tr>
</table>
</c:if>

<c:if test="${count > 0}">

	

<table class="gamelisttable">
	
<tr>
	<td colspan="10">
	<c:choose>
		<c:when test="${preface eq 'all' and login eq 1}">
			[전부]&nbsp;		
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=me">[응원한 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=notme">[응원 안한 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endgame">[끝난 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endmygame">[응원한 끝난 경기만 보기]</a>&nbsp;
		</c:when>
		<c:when test="${preface eq 'notme' and login eq 1 }">
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=all">[전부]</a>&nbsp;		
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=me">[응원한 경기만 보기]</a>&nbsp;
			[응원 안한 경기만 보기]
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endgame">[끝난 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endmygame">[응원한 끝난 경기만 보기]</a>&nbsp;
		</c:when>
		<c:when test="${preface eq 'me' and login eq 1}">
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=all">[전부]</a>&nbsp;		
			[응원한 경기만 보기]&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=notme">[응원 안한 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endgame">[끝난 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endmygame">[응원한 끝난 경기만 보기]</a>&nbsp;
		</c:when>
		<c:when test="${preface eq 'endgame' and login eq 1}">
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=all">[전부]</a>&nbsp;		
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=me">[응원한 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=notme">[응원 안한 경기만 보기]</a>&nbsp;
			[끝난 경기만 보기]
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endmygame">[응원한 끝난 경기만 보기]</a>&nbsp;
		</c:when>
		<c:when test="${preface eq 'endmygame' and login eq 1}">
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=all">[전부]</a>&nbsp;		
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=me">[응원한 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=notme">[응원 안한 경기만 보기]</a>&nbsp;
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endgame">[끝난 경기만 보기]</a>&nbsp;
			[응원한 끝난 경기만 보기]
		</c:when>
		<c:when test="${preface eq 'all' and login ne 1 }">
			[전부]
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=endgame">[끝난 경기만 보기]</a>
		</c:when>
		<c:when test="${preface eq 'endgame' and login ne 1 }">
			<a href="${pageContext.request.contextPath }/bbgame/gameMain.do?pageNum=1&preface=all">[전부]</a>&nbsp;
			[끝난 경기만 보기]
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	</td>
	</tr>
	<tr>
		<th>번호</th><th>리그</th><th>title</th><th>시작기간</th>
		<th>종료기간</th><th>1팀</th><th>1팀투표율</th>
		<th>2팀투표율</th><th>2팀명</th>
		<th>진행사항</th>
	</tr>
	<c:forEach var="article" items="${articleList}">
	<tr>
		<td align="center" width="50">
			<c:out value="${number}"/>
			<c:set var="number" value="${number - 1}"/>
		</td>
		<td class="league">
			<c:set var="league" value="${article.league }"/>
			<c:choose>
				<c:when test="${article.league eq 'KBO' }">
				<img src="${pageContext.request.contextPath}/bbgame/images/KBO.JPG" width="50px" height="50px">
				</c:when>
				<c:when test="${article.league eq 'NLB' }">
				<img src="${pageContext.request.contextPath}/bbgame/images/NPB.JPG" width="50px" height="50px">
				</c:when>
				<c:when test="${article.league eq 'MLB' }">
				<img src="${pageContext.request.contextPath}/bbgame/images/MLB.JPG" width="50px" height="50px">
				</c:when>
				
			</c:choose>
		</td>
		<td class="titletd">
		<a href="${pageContext.request.contextPath}/bbgame/gameView.do?gnum=${article.gnum}&pageNum=${pageNum}&preface=${preface}">${article.title }</a>
		</td>
		<td>
			${article.startday }
		</td>
		<td>
			${article.endday }
		</td>
		<td>	
			<c:out value="${article.team1}"/>
		<p>
			
		</td>
		<td>${article.team1vote }%</td>
		<td>${article.team2vote }%</td>
		<td>	<c:out value="${article.team2}"/></td>
		<td>
			<c:if test="${article.play eq 'yes' }">
				종료된 <p>경기
			</c:if>
			<c:if test="${article.play eq 'no' }">
				진행중인<p> 경기
			</c:if>
		</td>
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
		<a href="${pageContext.request.contextPath}/bbgame/gameMain.do?pageNum=1&preface=${preface}">[처음]</a>
		<c:if test="${startPage > pageBlock}"> <!-- startPage 값이 pageBlock보다 클 때부터 작동한다. -->
			<a href="${pageContext.request.contextPath}/bbgame/gameMain.do?pageNum=${startPage - pageBlock}&preface=${preface}">[이전]</a>
		</c:if>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/bbgame/gameMain.do?pageNum=${i}&preface=${preface}">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<c:choose>
			<c:when test="${(startPage + pageBlock) <= lastPage}">
				<a href="${pageContext.request.contextPath}/bbgame/gameMain.do?pageNum=${startPage + pageBlock}&preface=${preface}">[다음]</a>
			</c:when>
		</c:choose>
	</c:if>
	
	<c:choose>
		<c:when test="${lastPage eq pageNum}"></c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/bbgame/gameMain.do?pageNum=${lastPage}}&preface=${preface}">[끝]</a>
		</c:otherwise>
	</c:choose>
	
</c:if>
<br><br>

</section>
</body>
</html>