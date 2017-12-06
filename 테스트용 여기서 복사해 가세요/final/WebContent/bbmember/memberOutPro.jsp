<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript">
function back(){
history.go(-1);
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
<link href="${pageContext.request.contextPath}/member/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<section>
<c:choose>

	<c:when test="${check eq 1 }">
		탈퇴가 완료되었습니다.<p>
		<a href="../board/main.do">[메인으로 가기]</a>
	</c:when>
	<c:when test="${check eq -2 }">
		로그인 갱신이 종료되었습니다.<p>
		다시 로그인 해주세요.<p>
		<a href="../board/main.do">[메인으로 가기]</a>
	</c:when>
	<c:otherwise>
		비밀번호가 잘못되었습니다.<p>
		<a href="javascript:back();">[이전으로 가기]</a>
	</c:otherwise>
</c:choose>

</section>
</body>
</html>
