<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<jsp:useBean id="dao" class="member.model.MemberDao"/>
<%
	String id = request.getParameter("id");
	int check = dao.MemberIdCheck(id); 
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
<style>
a{text - decoration : none;}
</style>
<title>아이디 중복확인 체크</title>
</head>
<body>
<section>
<%=id %>
<%if(check==1){out.println("는 이미 존재하는 ID입니다.<br/>");}
else{
	out.println("는 사용 가능합니다.<br/>");
}%>
<a href="#" onClick="javascript:self.close()">닫기</a>
	<p>
	<input type="text" >
</section>
</body>
</html>