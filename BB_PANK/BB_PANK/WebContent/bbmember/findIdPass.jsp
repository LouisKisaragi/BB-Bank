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
<script src="script.js"></script>
<script type="text/javascript">
function back(){
	history.go(-1);
}
</script>
<style>

</style>
<title>아이디 비밀번호 찾기 </title>



</head>
<body>
<section>
<form>
<a href="${pageContext.request.contextPath }/bbmember/findId.do" >아이디 찾기</a>
<a href="${pageContext.request.contextPath }/bbmember/findPass.do" >비밀번호 찾기</a>
<a href="javascript:back()">이전으로 가기</a>
</form>
</section>
<script src="script.js"></script>
</body>
</html>
