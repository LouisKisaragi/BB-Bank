<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function back(){
	history.go(-1);
}
</script>
<style>

</style>
<title>아이디 찾기 </title>



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
	<form method="post"  name="findIdSend" action="${pageContext.request.contextPath}/bbmember/findIdPro.do"
	onsubmit="return idFindSendSave()">
	<input type="hidden" name="name" value="${name }">
	<input type="hidden" name="email" value="${email }">
	<div class="layer3">
		<span class="content">
		<c:choose>
			<c:when test="${check eq 1 }">
				<p>인증번호입력<br><br>
				<input type="text"  name="certification" >
				<input type="button" value="인증번호 다시 보내기" onClick="document.location.href='${pageContext.request.contextPath}/bbmember/findIdSend.do'"><p>
				<input type="submit" value="인증번호 확인">
			</c:when>
			<c:when test="${check eq 0 }">
				입력하신 이름 또는 이메일은 등록되어있지 않습니다.
				<input type="button" value="이전으로 가기" onClick="javascript:back();">
			</c:when>
		</c:choose>
		</span>
		<span class="blank"></span>
		</div>
	</form>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
<script src="script.js"></script>
</body>
</html>
