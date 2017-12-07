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

<link href="${pageContext.request.contextPath}/bbboard2/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bbboard2/css/liststyle.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/bbboard2/script.js"></script>
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
<c:set var="bn" value="2"/>
<b>글목록(전체 글:${count})</b>
<p><c:if test="${login eq 1 }">(${logNick }님 안녕하세요)</c:if></p>

<table class="listwritebutton">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath}/bbboard2/writeForm.do?pageNum=${pageNum}&bn=${bn}&preface=${preface}">글쓰기</a>
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
	<td colspan="10">
	<c:choose>
		<c:when test="${preface eq 'all'}">
			[전부]&nbsp;&nbsp;&nbsp;		
			<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=problem">[문제]</a>&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=solution">[해결]</a>
		</c:when>
		<c:when test="${preface eq 'solution' }">
			<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=all">[전부]</a>&nbsp;&nbsp;&nbsp;		
			<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=problem">[문제]</a>&nbsp;&nbsp;&nbsp;
			[해결]
		</c:when>
		<c:when test="${preface eq 'problem' }">
			<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=all">[전부]</a>&nbsp;&nbsp;&nbsp;		
			[문제]&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=solution">[해결]</a>
		</c:when>
		<c:otherwise>
		<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=all">[전부]</a>&nbsp;&nbsp;&nbsp;		
		<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=problem">[문제]</a>&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/bbboard2/list.do?pageNum=1&bn=${bn}&preface=solution">[해결]</a>
		</c:otherwise>
	</c:choose>
	</td>
</tr>
	<tr>
		<th id="num">번 호</th>
		<th id="preface" width="10%">분 류</th>
		<th id="title">제 목</th>
		<th id="writer">업로더</th>
		<th id="filename">파일이름</th>
		<th id="date">작성일</th>
		<th id="counter">조 회</th>
	</tr>
	<c:forEach var="marticle" items="${MarticleList}">
	<tr>
		<td align="center" width="50">
			<c:out value="1"/>
			<c:set var="number" value="${number - 1}"/>
		</td>
		<td class="preface">
			[공지]
		</td>
		<td class="titletd">
		<a href="${pageContext.request.contextPath}/bbboard2/content.do?num=${marticle.num}&pageNum=${pageNum }&bn=${bn}&preface=${preface}&details=${details }&search=${search}'">${marticle.subject }</a>
		</td>
		<td>
		
			<c:out value="${marticle.writer}"/>
			
		</td>
		<td>${marticle.origin_filename }</td>
		<td>${marticle.regdate}</td>
		<td>${marticle.readcount}</td>
	</tr>
	</c:forEach>
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
				<img src="${pageContext.request.contextPath}/bbboard2/images/level.gif"
					width="${5 * article.depth}">
				<img src="${pageContext.request.contextPath}/bbboard2/images/re.gif">
			</c:if>
			<c:if test="${article.depth == 0}">
				<img src="${pageContext.request.contextPath}/bbboard2/images/level.gif"
					width="${5 * article.depth}">
			</c:if>
			<a href="${pageContext.request.contextPath}/bbboard2/content.do?num=${article.num}&pageNum=${pageNum }&bn=${bn}&preface=${preface}&details=${details }&search=${search}">
				${article.subject}</a>
			<c:if test="${article.readcount >= 20}">
				<img src="${pageContext.request.contextPath}/bbboard2/images/hot.gif">
			</c:if>
		</td>
		<td>	
			<c:choose>
				<c:when test="${article.mem eq 1 }">	
					<a href="javascript:openView('${article.writer }');" target="_blank"><c:out value="${article.writer}"/></a>
				</c:when>
				<c:otherwise>
					<c:out value="${article.writer}"/>
				</c:otherwise>
			</c:choose>
			<p>
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
	<form method="post" name="listSearch" action="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=1&bn=${bn }&preface=${preface }" 
	onsubmit="return listSearchSave()">
	<select name="details">
		<option value="subject">제목</option>
		<option value="content">내용</option>
		<option value="subjectcontent">제목+내용</option>
		<option value="writer">글쓴이</option>
	</select>
	<input type="text" name="search" value="${search }">
	<input type="submit" value="검색" >
	<p>
	</form>
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
		<a href="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=1&bn=${bn}&preface=${preface}&details=${details }&search=${search}">[처음]</a>
		<c:if test="${startPage > pageBlock}"> <!-- startPage 값이 pageBlock보다 클 때부터 작동한다. -->
			<a href="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=${startPage - pageBlock}&bn=${bn}&preface=${preface}&search=${search}&details=${details }">[이전]</a>
		</c:if>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=${i}&bn=${bn}&preface=${preface}&search=${search}&details=${details }">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<c:choose>
			<c:when test="${(startPage + pageBlock) <= lastPage}">
				<a href="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=${startPage + pageBlock}&bn=${bn}&preface=${preface}&search=${search}&details=${details }">[다음]</a>
			</c:when>
		</c:choose>
	</c:if>
	
	<c:choose>
		<c:when test="${lastPage eq pageNum}"></c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=${lastPage}&bn=${bn}&preface=${preface}&search=${search}&details=${details }">[끝]</a>
		</c:otherwise>
	</c:choose>
	
</c:if>
<br><br>

</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>
