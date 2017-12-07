<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<meta http-equiv="Refresh" content="0; url=${pageContext.request.contextPath}/bbadmin/adlist.do?pageNum=${pageNum}&bn=${bn}">
    
<c:if test="${check ==0 }">
<!DOCTYPE html>
<html>
<head>
<link href="${ pageContext.request.contextPath }/bbboard1/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ pageContext.request.contextPath }/bbboard1/css/deleteFormstyle.css" rel="stylesheet" type="text/css"/>
</head>




</c:if>
</body>
</html>