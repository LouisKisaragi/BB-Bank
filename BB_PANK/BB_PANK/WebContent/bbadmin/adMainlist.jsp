<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/bbadmin/css/style.css"
rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbadmin/css/liststyle.css"
rel="stylesheet" type="text/css"/>
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
<%@ include file="../bbadmin/adheader.jsp" %>
<section>
<c:set var="bn" value="1"/>
	<b>글목록(전체글:${count})</b>
	<p>
	<c:if test="${login eq 1}">
	(${logNick}님 안녕하세요)
	</c:if>
	</p>

		<c:if test="${login eq 1 and super_m eq '1'}">
			<table class="listwritebutton">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath }/bbadmin/adwriteForm.do?pageNum=${pageNum}&bn=${bn}">글쓰기</a>
					</td>
				</tr>
			</table>
	</c:if>
<c:if test="${count == 0 }">
<table class="listtable">
	<tr>
		<td> 
				게시판에 저장된 글이 없습니다.
		</td>
	</tr>
</table>
</c:if>	

<c:if test="${count > 0 }">
<table class="listwritebutton">
	<tr>
		<td>
			<a href="${pageContext.request.contextPath}/bbadmin/writeForm.do?pageNum=${pageNum}">글쓰기</a>
		</td>
	</tr>
</table>
<table class="listtable">
	<tr>
		<th id="num">번호</th>
		<th id="preface">분류</th>
		<th id="title">제목</th>
		<th id="writer">작성자</th>
		<th id="filename">파일</th>
		<th id="date">작성일</th>
		<th id="counter">조회</th>
	</tr>
	<c:forEach var="article" items="${ articleList }">
	<tr>
		<td align="center" >
			<c:out value="${ number }"/>
			<c:set var="number" value="${ number-1 }" />
		</td>
		<td>
			<c:set var="preface" value="${article.preface }"/>
			<c:choose>
				<c:when test="${article.bn eq '1' and article.preface eq '공지' }">
				[공지]<p>[공지]
				</c:when>
				<c:when test="${article.bn eq '1' and article.preface eq '점검' }">
				[공지]<p>[점검]
				</c:when>
				<c:when test="${article.bn eq '1' and article.preface eq '이벤트' }">
				[공지]<p>[이벤트]
				</c:when>
				<c:when test="${article.bn eq '1' and article.preface eq '발표' }">
				[공지]<p>[발표]
				</c:when>
				<c:when test="${article.bn eq '5'}">
				[야구]<p>[공지]
				</c:when>
				<c:when test="${article.bn eq '2' }">
				[Q&A]<p>[공지]
				</c:when>
				<c:when test="${article.bn eq '3'}">
				[이슈]<p>[공지]
				</c:when>
			</c:choose>
		</td>
		<td class="titletd" >
			<c:if test="${ article.depth > 0 }">
			<img src="${ pageContext.request.contextPath }/bbadmin/adimages/level.gif" width="${5* article.depth }">
			<img src="${ pageContext.request.contextPath }/bbadmin/adimages/re.gif">
			</c:if>
			<c:if test="${ article.depth == 0 }">
			<img src="${ pageContext.request.contextPath }/bbadmin/adimages/level.gif" width="${5* article.depth }">
			</c:if>
			<a href="${ pageContext.request.contextPath}/bbadmin/content.do?num=${article.num}&pageNum=${currentPage}&bn=${article.bn}">
			${ article.subject }</a>
			<c:if test="${ article.readcount >= 20 }">
			<img src = "${ pageContext.request.contextPath}/bbadmin/adimages/hot.gif">
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
			<script type="text/javascript">
				var ipcutt="<c:out value="${ipcut}"/>";
				var ipc = ipcutt.split('.');
				document.write(ipc[0]);
				document.write(".");
				document.write(ipc[1]);
			</script>
			)
		</td>
		<td>${ article.server_filename }</td>
		<td>${ article.regdate }</td>
		<td>${ article.readcount }</td>
	</tr>
	</c:forEach>
</table>
</c:if>

<form method="post" name="search" action="${pageContext.request.contextPath}/bbadmin/adMainlist.do?pageNum=${pageNum }&bn=${bn}">
<table style="border: 0px;">
  <tr>
    <td style="text-align: right;">
      <select name="keyField">
      	<option value="subject">제목</option>      
      	<option value="writer">이름</option>
      	<option value="content">내용</option>
      </select>
	    <input type="text" size=16 name="keyWord"><input type="submit" value="찾기"> &nbsp;<input type="button" value=" 전체 목록" onclick="window.location='${pageContext.request.contextPath}/bbadmin/adMainlist.do?pageNum=${pageNum }&bn=${bn}'"></td>
   </tr> 
   <tr><td colspan="3" align="center">

<c:if test="${count > 0 }">
	<c:set var="imsi" value="${count % pageSize == 0 ? 0 : 1 }"/>
	<c:set var="pageCount" value="${count /pageSize + imsi }"/>
	<c:set var="pageBlock" value="${3}"/>
	<fmt:parseNumber var="result" value="${(currentPage-1) / pageBlock}" integerOnly="true"/>
	<c:set var="startPage" value="${result * pageBlock + 1 }"/>
	<c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
	
	<c:if test="${endPage > pageCount }">
		<c:set var="endPage" value="${pageCount}"/>
	</c:if>
	<c:if test="${startPage > pageBlock }">
	<a href="${pageContext.request.contextPath}/bbadmin/adMainlist.do?pageNum=${startPage - pageBlock}&bn=${bn}&keyField=${keyField}&keyWord=${keyWord}">이전</a>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/bbadmin/adMainlist.do?pageNum=${i}&bn=${bn}&keyField=${keyField}&keyWord=${keyWord}">[${i}]</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<a href="${pageContext.request.contextPath}/bbadmin/adMainlist.do?pageNum=${startPage + pagBlock}&bn=${bn}&keyField=${keyField}&keyWord=${keyWord}">다음</a>
	</c:if>
</c:if>
</td></tr>
</table>
</form>
</section>
<div id="footer">
<%@ include file="../bbadmin/adfooter.jsp" %>
</div>
</body>
</html>