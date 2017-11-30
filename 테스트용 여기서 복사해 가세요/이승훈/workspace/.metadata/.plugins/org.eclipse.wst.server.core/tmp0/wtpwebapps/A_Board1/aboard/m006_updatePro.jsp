<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check == 1}">
	<meta http-equiv="Refresh" content="0;url=${pageContext.request.contextPath}/list.do?pageNum=${pageNum}">
</c:if>
<c:if test="${check == 0}">
<br><br>
패스워드가 다릅니다
<br><br><br>
<a href="javascript:history.go(-1)">[수정화면으로 돌아가기]</a>
</c:if>
<!DOCTYPE html>
<head>
<link href="${pageContext.request.contextPath}/aboard/css/style.css" rel="stylesheet" type="text/css"/>
</head>