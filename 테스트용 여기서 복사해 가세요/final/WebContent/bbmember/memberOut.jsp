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

	<form method="post"  name="memberOut" action="${pageContext.request.contextPath}/member/memberOutPro.do"
	onsubmit="return outSave()"> 
			
			
				회원 탈퇴시 복구 불가능합니다! 정말로 탈퇴하시겠습니까?
				<p><p><p>
				
				
				탈퇴를 위해 비밀번호를 다시 입력해주세요<p>
				비밀번호<input type="password" name="pass"><p>
				<input type="submit" value="탈퇴하기"><p>
				
				<input type="button" value="메인으로 돌아가기" onClick="document.location.href='${pageContext.request.contextPath}/board/main.do'">
		
	
	</form>
</section>
<script src="${pageContext.request.contextPath }/member/script.js"></script>
</body>
</html>
