<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<link href="${ pageContext.request.contextPath }/bbboard1/css/style.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>

<script src="${ pageContext.request.contextPath }/bbadmin/script.js"></script>
<link href="${ pageContext.request.contextPath}/bbadmin/css/style.css" rel="stylesheet" type="text/css">
<link href="${ pageContext.request.contextPath}/bbadmin/css/deleteFormstyle.css" rel="stylesheet" type="text/css">
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
<%@ include file="../bbadmin/adheader.jsp" %>
<section>
	<div class="layer1">
			<b>글삭제</b>
			정말로 삭제하시겠습니까?<p>
			<a href="${pageContext.request.contextPath}/bbadmin/deletePro.do?num=${num }&pageNum=${pageNum}&bn=${bn}">네</a>
		</div>	

<form method="post" name="delForm" action="${ pageContext.request.contextPath }/bbadmin/deletePro.do?num=${num }pageNum=${pageNum}&bn=${bn}"
	onsubmit="return deleteSave()">
	
	<table class="deletetable">
		<tr>
			<td>
				<input type="button" value="목록"
					onclick="document.location.href='${pageContext.request.contextPath}/bbadmin/adlist.do?pageNum=${pageNum}&bn=${bn}'">
			</td>					
		</tr>
	</table>
</form>
</section>
<div id="footer">
<%@ include file="../bbadmin/adfooter.jsp" %>
</div>

</body>
</html>