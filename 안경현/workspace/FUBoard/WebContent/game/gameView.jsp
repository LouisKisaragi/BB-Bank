<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>경기응원</title>
<link href="${pageContext.request.contextPath}/game/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/game/css/gameviewstyle.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/game/script.js"></script>
</head>
<body>
<section>
<b>경기보기</b>
<br>
<form method="post" name="content" action="${pageContext.request.contextPath}/game/vote.do?gnum=${gnum }&pageNum=${pageNum }&preface=${preface}"
	onsubmit="return contentSave()">
<input type="hidden" name="gnum" value="${gnum}">
<input type="hidden" name="id" value="${logId }">
<input type="hidden" name="nick" value="${logNick }">
<table  class="gameviewtable">
	<tr>
		<th>내가 가진 포인트</th>
		<c:choose>
			<c:when test="${login eq 1 }">
				<td>${logPoint}P</td>
			</c:when>
			<c:otherwise>
				<td>0P</td>
			</c:otherwise>
		</c:choose>
		<th>사용가능 포인트</th>
		<c:choose>
			<c:when test="${login eq 1 }">
				<td>${canPoint }P</td>
			</c:when>
			<c:otherwise>
				<td>0P</td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr class="title">
			<td colspan="10">${article.title }</td>
		</tr>
		<tr>
			<td>
			<c:choose>
				<c:when test="${article.team1 eq 'lotte' }">
					<img src="${pageContext.request.contextPath}/game/images/LOTTE.JPG" width="50px" height="50px"><p>
					롯데 자이언츠
				</c:when>
				<c:when test="${article.team1 eq 'samsung' }">
					<img src="${pageContext.request.contextPath}/game/images/SAMSUNG.JPG" width="50px" height="50px"><p>
					삼성 라이온즈
				</c:when>
				<c:when test="${article.team1 eq 'kia' }">
					<img src="${pageContext.request.contextPath}/game/images/KIA.JPG" width="50px" height="50px"><p>
					KIA 타이거즈
				</c:when>
				<c:when test="${article.team1 eq 'dusan' }">
					<img src="${pageContext.request.contextPath}/game/images/DUSAN.JPG" width="50px" height="50px"><p>
					두산 베어스
				</c:when>
				<c:when test="${article.team1 eq 'nc' }">
					<img src="${pageContext.request.contextPath}/game/images/NC.JPG" width="50px" height="50px"><p>
					NC 다이노스
				</c:when>
				<c:when test="${article.team1 eq 'sk' }">
					<img src="${pageContext.request.contextPath}/game/images/SK.JPG" width="50px" height="50px"><p>
					SK 와이번스
				</c:when>
				<c:when test="${article.team1 eq 'lg' }">
					<img src="${pageContext.request.contextPath}/game/images/LG.JPG" width="50px" height="50px"><p>
					LG 트윈스
				</c:when>
				<c:when test="${article.team1 eq 'nexen' }">
					<img src="${pageContext.request.contextPath}/game/images/NEXEN.JPG" width="50px" height="50px"><p>
					넥센 히어로즈
				</c:when>
				<c:when test="${article.team1 eq 'hanhwa' }">
					<img src="${pageContext.request.contextPath}/game/images/HANHWA.JPG" width="50px" height="50px"><p>
					한화 이글스
				</c:when>
				<c:when test="${article.team1 eq 'kt' }">
					<img src="${pageContext.request.contextPath}/game/images/KT.JPG" width="50px" height="50px"><p>
					KT wiz
				</c:when>
			</c:choose>
			</td>
			<td colspan="2">
				<img src="${pageContext.request.contextPath}/game/images/VS.JPG" width="50px" height="50px"><p>
					총 참여 인원 : ${count }<p>총 누적 포인트 : ${count*500 }P<p>응원 포인트 한도 : 500P
			</td>
			<td>
			<c:choose>
				<c:when test="${article.team2 eq 'lotte' }">
					<img src="${pageContext.request.contextPath}/game/images/LOTTE.JPG" width="50px" height="50px"><p>
					롯데 자이언츠
				</c:when>
				<c:when test="${article.team2 eq 'samsung' }">
					<img src="${pageContext.request.contextPath}/game/images/SAMSUNG.JPG" width="50px" height="50px"><p>
					삼성 라이온즈
				</c:when>
				<c:when test="${article.team2 eq 'kia' }">
					<img src="${pageContext.request.contextPath}/game/images/KIA.JPG" width="50px" height="50px"><p>
					KIA 타이거즈
				</c:when>
				<c:when test="${article.team2 eq 'dusan' }">
					<img src="${pageContext.request.contextPath}/game/images/DUSAN.JPG" width="50px" height="50px"><p>
					두산 베어스
				</c:when>
				<c:when test="${article.team2 eq 'nc' }">
					<img src="${pageContext.request.contextPath}/game/images/NC.JPG" width="50px" height="50px"><p>
					NC 다이노스
				</c:when>
				<c:when test="${article.team2 eq 'sk' }">
					<img src="${pageContext.request.contextPath}/game/images/SK.JPG" width="50px" height="50px"><p>
					SK 와이번스
				</c:when>
				<c:when test="${article.team2 eq 'lg' }">
					<img src="${pageContext.request.contextPath}/game/images/LG.JPG" width="50px" height="50px"><p>
					LG 트윈스
				</c:when>
				<c:when test="${article.team2 eq 'nexen' }">
					<img src="${pageContext.request.contextPath}/game/images/NEXEN.JPG" width="50px" height="50px"><p>
					넥센 히어로즈
				</c:when>
				<c:when test="${article.team2 eq 'hanhwa' }">
					<img src="${pageContext.request.contextPath}/game/images/HANHWA.JPG" width="50px" height="50px"><p>
					한화 이글스
				</c:when>
				<c:when test="${article.team2 eq 'kt' }">
					<img src="${pageContext.request.contextPath}/game/images/KT.JPG" width="50px" height="50px"><p>
					KT wiz
				</c:when>
			</c:choose>
			</td>
		</tr>
		<tr>
		
		</tr>
	<tr>
		<th colspan="10">응원댓글(총응원댓글수:${count })</th>
		
	</tr>
	</table>
<c:if test="${count == 0}">
<table  class="contenttable">
	<tr>
		<td>
			저장된 응원댓글이 없습니다.
		</td>
	</tr>
</table>
</c:if>

<table class="votetable">
	<tr>
	</tr>
	<tr>
		<td>${article.team1vote}%<p>
		참여인원 : <p>누적포인트 : P<p>배당률 : 배<p>
		<textarea name="commentTeam1" rows="4" cols="20"></textarea><p>
		<input type="submit"   style="WIDTH: 130pt; HEIGHT: 60pt" value="등록"></td>
		<td>
		</td>
		<td>
		<td>${article.team2vote}%<p>
		참여인원 : <p>누적포인트 : P<p>배당률 : 배<p>
		<textarea name="commentTeam2" rows="4" cols="20"></textarea><p>
		<input type="submit"   style="WIDTH: 130pt; HEIGHT: 60pt" value="등록"></td>
	</tr>
	<c:if test="${count > 0}">
	<div style="border: 1px solid blue; float: left; width: 50%;">
		<table class="voteteam1table">
			<c:forEach var="articlec" items="${articleList}">
				<c:if test="${articlec.voteteam eq article.team1 }">	
					<tr>
					<td>${articlec.voteteam }
					</td>
					<td colspan="2">${articlec.votenick}<p>
					<td colspan ="10">${articlec.votecomment }
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	<div style="border: 1px solid red; float: left; width: 50%;">
		<table class="voteteam2table">
			<c:forEach var="articlec" items="${articleList}">
				<c:if test="${articlec.voteteam eq article.team2 }">	
					<tr>
						<td>${articlec.voteteam }
						</td>
						<td colspan="2">${articlec.votenick}<p>
						<td colspan ="10">${articlec.votecomment }
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</c:if>
</table>
</form>
</section>
</body>
</html>
