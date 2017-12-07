<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<script src="${pageContext.request.contextPath }/bbboard4/script.js"></script>
<link href="${pageContext.request.contextPath }/bbboard4/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bbboard4/css/writeFormstyle.css" rel="stylesheet" type="text/css"/>
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
<article>
<b>글쓰기</b>
<br></br>
<form method="post" name="writeForm" action="${pageContext.request.contextPath}/bbboard4/writePro.do" onsubmit="return writeSave()">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="step" value="${step}">
<input type="hidden" name="depth" value="${depth}">
<input type="hidden" name="bn" value=4>
<c:if test="${login eq 1 }">
<input type="hidden" name="mem" value=1>
</c:if>
<c:if test="${login ne 1 }">
<input type="hidden" name="mem" value=0>
</c:if>
<table class="board">
	
		<c:if test="${login ne 1 }">
			<tr>
			<td class="attr">이 름</td>
			<td><input type = "text" name="writer"></td>
			</tr>
		</c:if>
		<c:if test="${login eq 1 }">
			<input type = "hidden" name="writer" value="${logNick }">
		</c:if>
			
		<tr>
			<td class="attr">구 분</td>
			<td>
				<select name="preface" size="1">
					<option value="1" >연예
					<option value="2">정치
					<option value="3">스포츠
					<option value="4">뻘글
				</select>
			</td>
		</tr>	
		<tr>
			<td class="attr">제 목</td>
			<td>
			<c:if test="${num == 0}">
			<input class="input" type="text" name="subject">
			</c:if>
			<c:if test="${num != 0}">
			<input class="input" type="text" name="subject" value="[답변]" >
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="attr">내 용</td>
			<td>
				<textarea name="content" rows="13" cols="40"></textarea>
			</td>
		</tr>
		<c:if test="${login ne 1 }">
			<tr>
			<td class="attr">비밀번호</td>
			<td><input type = "password" name="pass"></td>
			</tr>
		</c:if>
		<c:if test="${login eq 1 }">
			<input type = "hidden" name="pass" value="${logPass }">
		</c:if>
		
		<tr>
			<td colspan="2" class="attr">
				<input type="submit" value="글쓰기">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" OnClick="window.location='${pageContext.request.contextPath}/bbboard4/list.do'">
			</td>
		</tr>
</table>
</form>
</article>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>