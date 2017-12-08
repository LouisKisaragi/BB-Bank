<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<link href="${pageContext.request.contextPath}/bbmember/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function back(){
history.go(-1);
}
</script>
</head>
<body>
<section>
<c:choose>
<c:when test="${check eq 1 }">
	<meta http-equiv="Refresh" content="0;url=${page}">
</c:when>
<c:otherwise>
	아이디 또는 비밀번호가 틀렸습니다.
	<a href="javascript:back();">뒤로가기</a>
</c:otherwise>
</c:choose>

<!--	id=${logId }
	pass=${logPass }
	name=${logName }
	check=${check }
	url=${page }
	point=${point }
	-->
</section>
</body>
</html>
