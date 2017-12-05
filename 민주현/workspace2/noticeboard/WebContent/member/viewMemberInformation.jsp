<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
section{
	padding-top: 30px;
	width: 300px; 
	margin: auto;
	left:0; top:0; right:0; bottom:0;
}
</style>

<title>유저 정보 보기</title>  
  
</head>
<body>
<section>
	<form>
		<c:choose>
			<c:when test="${login eq 1 and logNick eq viewNick }">
				<b>[${logNick }]님 안녕하세요</b><p>
				<table class="memberInformation">
				<tr>
				<td colspan="2">[${logNick }] 회원님의 정보</td>
				</tr>
				<tr>
				<th>이름</th><td>[${logName }]</td>
				</tr>
				<tr>
				<th>이메일</th><td>[${logEmail }]</td>
				</tr>
				<tr>
				<th>닉네임</th><td>[${logNick }]</td>
				</tr>
				<tr>
				<th>보유포인트</th><td>[${logPoint }]</td>
				</tr>
				<tr>
				<th>가입날짜</th><td>[${logJoin }]</td>
				</tr>
				<tr>
				<td><a href="javascript:self.close()"><input type="button" value="닫기"></a></td>
				<td><input type="button" value="탈퇴" onclick="document.location.href=''${pageContext.request.contextPath }/member/memberOut.do'"></td>
				</tr>
				
				</table>
				</c:when>
			<c:when test="${login eq 1 and logNick ne viewNick}">
				<b>[${logNick }님 안녕하세요]</b>
				<table class="memberInformation">
				<tr>
				<td colspan="2">[${article.nickname }] 회원님의 정보</td>
				</tr>
				<tr>
				<th>이메일</th><td>[${article.email }]</td>
				</tr>
				<tr>
				<th>닉네임</th><td>[${article.nickname }]</td>
				</tr>
				<tr>
				<th>보유포인트</th><td>[${article.point }]</td>
				</tr>
				<tr>
				<td colspan="2"><a href="javascript:self.close()"><button>닫기</button></a></td>
				</tr>
				</table>
				</c:when>
			<c:otherwise>
			<table class="memberInformation">
				<tr>
				<td colspan="2">[${article.nickname }] 회원님의 정보</td>
				</tr>
				<tr>
				<th>이메일</th><td>[${article.email }]</td>
				</tr>
				<tr>
				<th>닉네임</th><td>[${article.nickname }]</td>
				</tr>
				<tr>
				<th>보유포인트</th><td>[${article.point }]</td>
				</tr>
				<tr>
				<td colspan="2"><a href="javascript:self.close()"><button>닫기</button></a></td>
				</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</form>
</section>
</body>
</html>
