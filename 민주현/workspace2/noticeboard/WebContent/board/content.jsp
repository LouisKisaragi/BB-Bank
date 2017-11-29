<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/board/css/contentstyle.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/board/script.js"></script>
<!-- <script type="text/javascript"> -->
<!-- 	function onDownload(num) { -->
<!-- 		var o = document.getElementById("ifrm_filedown"); -->
<!-- 		o.src="download.do?num="+num; -->
<!-- 	} -->
<!-- </script> -->


</head>
<body>
<iframe id="ifrm_filedown" style="position:absolute; z-index:1; visibility: hidden;">
</iframe>
<section>
<b>글내용 보기</b>

<br>
<form method="post" name="content" action="${pageContext.request.contextPath }/board/comment.do?num=${num}&pageNum=${pageNum}&bn=${bn}"
onsubmit="return contentSave()">
<input type="hidden" name="num" value="${num}">
<c:choose>
	<c:when test="${login eq 1 and super_m eq '0'}">
		<input type="hidden" name="mem" value="1">
	</c:when>
	<c:when test="${login eq 1 and super_m eq '1' }">
		<input type="hidden" name="mem" value="2">
	</c:when>
	<c:otherwise>
		<input type="hidden" name="mem" value="0">
	</c:otherwise>
	
</c:choose>
	<table class="contenttable">
		<tr>
			<th>글번호</th>
			<td>${ article.num }</td>
			<th>조회수</th>
			<td>${ article.readcount }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${ article.writer }
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
			<th>작성일</th>
			<td>${ article.regdate }</td>
		</tr>
		<tr>
			<th>제목</th>

			<td colspan="3" class="contenttitle">
			&nbsp;
			<c:set var="preface" value="${article.preface}" />
			<c:choose>
				<c:when test="${article.preface eq '공지' }">
				[공지]
				</c:when>
				<c:when test="${article.preface eq '점검' }">
				[점검]
				</c:when>
				<c:when test="${article.preface eq '이벤트' }">
				[이벤트]
				</c:when>
				<c:when test="${article.preface eq '발표' }">
				[발표]
				</c:when>
			</c:choose>
			&nbsp;
			${ article.subject }
			</td>
		<tr>
			<th>글내용</th>
			<td colspan="3" class="content">
				<pre>${ article.content }</pre>
			</td>
		</tr>
		
		<c:if test="${article.filetype.startsWith('image/')}">
		<tr>
			<th>파일 이미지</th>
			<td colspan="3">
				<img src="../upload/${article.server_filename}" style="max-width:375px; height:auto;">
			</td>						
		</tr>
		</c:if>
	
			<tr>
			<th>파일</th>
			<td>
<%-- 			<a href="#" onclick="onDownload('${article.num}')"> ${ article.server_filename }</a> --%>
				<a href="${pageContext.request.contextPath}/board/download.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}">
				${article.origin_filename}</a>
			</td>
		
			<th>사이즈</th>
			<td>
			<fmt:formatNumber value="${article.filesize/1024}" pattern="#,###"/>KB
			</td>
		</tr>
		
		<tr>
			<td colspan="4">
		<c:choose>
		<c:when test="${article.mem eq '2' and login eq '1' and article.writer eq logNick}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="수 정" 
				onclick="document.location.href='${pageContext.request.contextPath}/board/updateForm.do?num=${ article.num }&pageNum=${pageNum}&bn=${bn }'">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="삭 제"
				onclick="document.location.href='${pageContext.request.contextPath}/board/deleteForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn }'">
				&nbsp;&nbsp;&nbsp;&nbsp;
		</c:when>
		<c:when test="${article.mem eq '1' and login eq '1' and article.writer eq logNick}">
		
		</c:when>
		<c:when test="${article.mem eq '1' and login eq '1' and article.writer ne logNick}">
		
		</c:when>
		
		<c:when test="${article.mem eq '1' and login eq 0}">
		
		</c:when>

		</c:choose>
				<input type="button" value="목 록"
				onclick="document.location.href='${pageContext.request.contextPath}/board/list.do?pageNum=${pageNum}&bn=${bn }'">
				&nbsp;&nbsp;&nbsp;&nbsp;				
			</td>
		</tr>
		<tr>
			<th colspan="4" style="background-color: #c0c0c0; color: black;">댓글(총 댓글수:${count})</th>
		</tr>
	</table>
<c:if test="${count == 0}">
<table class="commenttable">
	
	<tr>
		<td colspan="4" style="text-align: center;">
				댓글이 없습니다.
		</td>
	</tr>
</table>
</c:if>
<c:if test="${count > 0}">
<table class="commenttable">
	
	<c:forEach var="comment" items="${commentList}">
	<tr>
	<td>${comment.writer}
	<c:set value="${comment.ip}" var="ipcut"/>
	(
			<script type="text/javascript">
				var ipcutt="<c:out value="${ipcut}"/>";
				var ipc = ipcutt.split('.');
				document.write(ipc[0]);
				document.write(".");
				document.write(ipc[1]);
			</script>
	)</td>
	<td colspan="3" style="text-align: left;"><pre>${comment.content}</pre>
	<p style="font-size: 10pt; text-align: right;">${comment.regdate}
	<c:choose>
	<c:when test="${super_m eq '1' and login eq '1'}">
		<input type="button" value="삭제"
	onClick="document.location.href='${pageContext.request.contextPath}/board/CommentDeleteForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${comment.num}'">
	</c:when>
	
	<c:when test="${super_m eq '0' and comment.mem eq '1'and login eq '1' and comment.writer eq logNick}">
		<input type="button" value="삭제"
	onClick="document.location.href='${pageContext.request.contextPath}/board/CommentDeleteForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${comment.num}'">
	</c:when>
	
	<c:when test="${comment.mem eq '0' and login eq '0'}">
		<input type="button" value="삭제"
	onClick="document.location.href='${pageContext.request.contextPath}/board/CommentDeleteForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${comment.num}'">
	</c:when>
	</c:choose>
	</p>
	</td>				
	</tr>
	</c:forEach>
</table>
</c:if>
<table class="commenttable">
		
			<c:choose>
			<c:when test="${login eq 1}">
				<input type="hidden" name="cwriter" value="${logNick}">
				<input type="hidden" name="cpass" value="${logPass}">
			</c:when>
			<c:otherwise>
			<tr>
				<th>작성자</th>
				<td style="text-align: left;"><input type="text" name="cwriter"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td style="text-align: left;"><input type="password" name="cpass"></td>
			</tr>
			</c:otherwise>
			</c:choose>
			<tr>
				<th>내용</th>
				<td class="content">
				<textarea rows="5" cols="31" name="ccontent"></textarea>
				</td>
				<td rowspan="3"><input type="submit"   style="height: 100px; width: 125px;" 	value="등록"></td>
			</tr>
</table>		
	</form>
</section>

</body>
</html>