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

</script>
<style>

</style>
<title>유저 정보 보기</title>
</head>
<body>
<section>
	<form>
		<c:choose>
			<c:when test="${login eq 1 and logNick eq viewNick }">
				<b>[${logNick }]님 안녕하세요</b>
			</c:when>
			<c:otherwise>
				<b>[${viewNick }님 안녕하세요]</b>
			</c:otherwise>
		</c:choose>
	</form>
</section>
<script src="script.js"></script>
</body>
</html>
