<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="hairJS/hairjs.js"></script>
</head>
<body>
<h2>IDチェック</h2>
<form action="idcheck.do" method="get" name="frm">
아이디 <input type="text" name="id" value="${id }">
<input type="submit" value="チェック">
<br>
<c:if test="${result == 1 }">
	<script type="text/javascript">
		opner.document.frm.id.value="";
	</script>
	${id }使用可能なIDです。
	</c:if>
	<c:if test="${result == -1 }">
	<input type="button" value="決定" class="cancle" onclick="idok()">
	</c:if>
</form>
</body>
</html>