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
<link href="${pageContext.request.contextPath}/bbboard3/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bbboard3/css/contentstyle.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/bbboard3/script.js"></script>
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
<b>자료 보기</b>
<br>
<form method="post" name="content" action="${pageContext.request.contextPath}/bbboard3/comment.do?num=${num }&pageNum=${pageNum }&bn=${bn}"
	onsubmit="return contentSave()">
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
				<c:when test="${article.preface eq '1' }">
				[연예]
				</c:when>
				<c:when test="${article.preface eq '2' }">
				[정치]
				</c:when>
				<c:when test="${article.preface eq '3' }">
				[스포츠]
				</c:when>
				<c:when test="${article.preface eq '4' }">
				[뻘글]
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
		<td colspan ="8"><a href="${pageContext.request.contextPath}/bbboard2/download.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}">
				${article.origin_filename}</a></td>
		<td><fmt:formatNumber value="${article.filesize/1024}" pattern="#,###"/>KB</td>
	</tr>
	<tr>
		<td colspan="10">
		<input type="button" value="답 글" onClick="document.location.href='${pageContext.request.contextPath}/bbadmin/writeForm.do?num=${article.num}&ref=${article.ref}&step=${article.step}&depth=${article.depth}&pageNum=${pageNum }&bn=${bn}'">
			&nbsp;&nbsp;
		<input type="button" value="삭 제" onClick="document.location.href='${pageContext.request.contextPath}/bbadmin/delete.do?num=${article.num}&pageNum=${pageNum}&bn=${bn }'">
				&nbsp;&nbsp;
		<input type="button" value="목 록" onClick="document.location.href='${pageContext.request.contextPath}/bbadmin/adlist.do?pageNum=${pageNum}&bn=${bn }'">
			&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<th colspan="10">댓글(총댓글수:${count })</th>
	</tr>
	</table>
<c:if test="${count == 0}">
<table  class="contenttable">
	<tr>
		<td>
			게시판에 저장된 댓글이 없습니다.
		</td>
	</tr>
</table>
</c:if>
<c:if test="${count > 0}">
<table class="contenttable">
	<c:forEach var="articlec" items="${articleList}">
	<tr>
	<c:choose>
		<c:when test="${articlec.depth>0}">
			<td>
				<img src="${pageContext.request.contextPath}/bbboard2/images/level.gif"
					width="${5 * article.depth}">
				<img src="${pageContext.request.contextPath}/bbboard2/images/re.gif">
			</td>
		</c:when>
		</c:choose>
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
	<td colspan ="10">${articlec.content }</td><td>${articlec.regdate }<p></td>
	<c:choose>
		<c:when test="${articlec.mem eq '1' and login eq 1 and articlec.writer eq logNick}">
			<td><input type="button" value="x" onClick="document.location.href='${pageContext.request.contextPath}/bbboard2/CommentDeleteForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${articlec.num}'"><p><input type="button" value="답글"onClick="document.location.href='${pageContext.request.contextPath}/bbboard2/recomment.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${articlec.num}'"></td>
		</c:when>
		<c:when test="${articlec.mem eq '0' and login ne 1 }">
			<td><input type="button" value="x" onClick="document.location.href='${pageContext.request.contextPath}/bbboard2/CommentDeleteForm.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${articlec.num}'"><p><input type="button" value="답글"onClick="document.location.href='${pageContext.request.contextPath}/bbboard2/recomment.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${articlec.num}'"></td>
		</c:when>		
		<c:otherwise>
			<td>
			<input type="button" value="답글"onClick="document.location.href='${pageContext.request.contextPath}/bbboard2/recomment.do?num=${article.num}&pageNum=${pageNum}&bn=${bn}&cnum=${articlec.num}'">
			</td>
		</c:otherwise>
	</c:choose>
	</tr>
	</c:forEach>
	</table>
</c:if>
<table class="contenttable">
	<tr>
	<c:choose>
		<c:when test="${login eq 1 }">
			<input type="hidden" name="cwriter" value="${logNick }">
			<input type="hidden" name="cpass" value="${logPass }">
			<!-- 로그인상태일때 -->
		</c:when>
		<c:otherwise>
			<th colspan="2">작성자</th>
			<td><input type="text" name="cwriter"></td>
			<th colspan="6">비밀번호</th>
			<td><input type="password" name="cpass"></td><!-- 비로그인상태일때 -->
		</c:otherwise>
	</c:choose>
		</tr>
		<tr>
		<td colspan="9"><textarea name="ccomment" rows="4" cols="40"></textarea></td>
		<td><input type="submit"   style="WIDTH: 130pt; HEIGHT: 60pt" 	value="등록"></td>
	</tr>
</table>
</form>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>
