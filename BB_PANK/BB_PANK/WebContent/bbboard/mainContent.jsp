<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
	<link href="${pageContext.request.contextPath}/Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/Resources/css/modern-business.css" rel="stylesheet">
     <!-- Page level plugin CSS-->
  <link href="${pageContext.request.contextPath}/Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/Resources/css/sb-admin.css" rel="stylesheet">
<style type="text/css">

section {

	height:100%;
	width: 1000px; 
	margin: auto;
	left:30; top:0; right:0; bottom:0;
	font-size: 15px;
	color:#ce16e9;
	text-align: center;
}

</style>
</head>
<body>

<header>
      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner" role="listbox">
          <!-- Slide One - Set the background image for this slide in the line below -->
          <div class="carousel-item active" style="background-image: url(' ${pageContext.request.contextPath}/images/MainImg.jpg'); background-size: 1000px 300px;">
            <div class="carousel-caption d-none d-md-block">
              <h3>First Slide</h3>
            </div>
          </div>
        </div>
      </div>
    </header>
<section>
    <article>
    
   
    
    <div class="row">
                <!-- /.col-lg-6 -->
 
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <a class="nav-link" href="${pageContext.request.contextPath}/bbboard1/list.do?pageNum=1&bn=1">
           						 <span class="nav-link-text">공지사항</span>
         					 </a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                           
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th id="preface">분류</th>
											<th id="title">제목</th>
											<th id="writer">작성자</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="article" items="${articleList1}">
                                        <tr>
                                        <td>
												<c:set var="preface" value="${article.preface }"/>
												<c:choose>
													<c:when test="${article.preface eq '공지' }">
													[공지]
													</c:when>
													<c:when test="${article.preface eq '점검' }">
													[점검]
													</c:when>
													<c:when test="${article.preface eq '이벤트' }">
													[이벤트]
													</c:when>
													<c:when test="${article.preface eq '발표' }">
													[발표]
													</c:when>
													
												</c:choose>
											</td>
											<td class="titletd" >
												<c:if test="${ article.depth > 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard1/images/level.gif" width="${5* article.depth }">
												<img src="${ pageContext.request.contextPath }/bbboard1/images/re.gif">
												</c:if>
												<c:if test="${ article.depth == 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard1/images/level.gif" width="${5* article.depth }">
												</c:if>
												<a href="${ pageContext.request.contextPath}/bbboard1/content.do?num=${article.num}&pageNum=${currentPage}&bn=1">
												${ article.subject }</a>
												<c:if test="${ article.readcount >= 20 }">
												<img src = "${ pageContext.request.contextPath}/bbboard1/images/hot.gif">
												</c:if>		
											</td>
											<td>${ article.writer }
											<c:set value="${article.ip }" var="ipcut"/>
												(
												<script type="text/javascript">
													var ipcutt="<c:out value="${ipcut}"/>";
													var ipc = ipcutt.split('.');
													document.write(ipc[0]);
													document.write(".");
													document.write(ipc[1]);
												</script>
												)
											</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                   
                <!-- /.col-lg-6 -->
                 <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          <a class="nav-link" href="${pageContext.request.contextPath}/bbboard4/list.do?pageNum=1&bn=4">
        				    <span class="nav-link-text">자유 게시판</span>
        				  </a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                        	<th id="preface">분류</th>
											<th id="title">제목</th>
											<th id="writer">작성자</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="article" items="${articleList4}">
                                        <tr>
                                        <td>
												<c:set var="preface" value="${article.preface }"/>
												<c:choose>
													<c:when test="${article.preface eq '1' }">
													[연예]
													</c:when>
													<c:when test="${article.preface eq '2' }">
													[정치]
													</c:when>
													<c:when test="${article.preface eq '3' }">
													[스포츠]
													</c:when>
													<c:when test="${article.preface eq '4' }">
													[뻘글]
													</c:when>
													
												</c:choose>
											</td>
											<td class="titletd" >
												<c:if test="${ article.depth > 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard4/images/level.gif" width="${5* article.depth }">
												<img src="${ pageContext.request.contextPath }/bbboard4/images/re.gif">
												</c:if>
												<c:if test="${ article.depth == 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard4/images/level.gif" width="${5* article.depth }">
												</c:if>
												<a href="${ pageContext.request.contextPath}/bbboard4/content.do?num=${article.num}&pageNum=${currentPage}&bn=4">
												${ article.subject }</a>
												<c:if test="${ article.readcount >= 20 }">
												<img src = "${ pageContext.request.contextPath}/bbboard4/images/hot.gif">
												</c:if>		
											</td>
											<td>${ article.writer }
											<c:set value="${article.ip }" var="ipcut"/>
												(
												<script type="text/javascript">
													var ipcutt="<c:out value="${ipcut}"/>";
													var ipc = ipcutt.split('.');
													document.write(ipc[0]);
													document.write(".");
													document.write(ipc[1]);
												</script>
												)
											</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                 <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                         <a class="nav-link" href="${pageContext.request.contextPath}/bbboard2/list.do?pageNum=1&bn=2">
           					 <span class="nav-link-text">Q&A</span>
       					 </a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th id="preface">분류</th>
											<th id="title">제목</th>
											<th id="writer">작성자</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="article" items="${articleList2}">
                                        <tr>
                                        <td>
												<c:set var="preface" value="${article.preface }"/>
												<c:choose>
													<c:when test="${article.preface eq 'problem' }">
													[문제]
													</c:when>
													<c:when test="${article.preface eq 'solution' }">
													[해결]
													</c:when>
													
												</c:choose>
											</td>
											<td class="titletd" >
												<c:if test="${ article.depth > 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard2/images/level.gif" width="${5* article.depth }">
												<img src="${ pageContext.request.contextPath }/bbboard2/images/re.gif">
												</c:if>
												<c:if test="${ article.depth == 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard2/images/level.gif" width="${5* article.depth }">
												</c:if>
												<a href="${ pageContext.request.contextPath}/bbboard2/content.do?num=${article.num}&pageNum=${currentPage}&bn=2">
												${ article.subject }</a>
												<c:if test="${ article.readcount >= 20 }">
												<img src = "${ pageContext.request.contextPath}/bbboard2/images/hot.gif">
												</c:if>		
											</td>
											<td>${ article.writer }
											<c:set value="${article.ip }" var="ipcut"/>
												(
												<script type="text/javascript">
													var ipcutt="<c:out value="${ipcut}"/>";
													var ipc = ipcutt.split('.');
													document.write(ipc[0]);
													document.write(".");
													document.write(ipc[1]);
												</script>
												)
											</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                 <!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        <a class="nav-link" href="${pageContext.request.contextPath}/bbboard5/list.do?pageNum=1&bn=5">
        				    <span class="nav-link-text">야구게시판</span>
          				</a>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th id="preface">분류</th>
											<th id="title">제목</th>
											<th id="writer">작성자</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="article" items="${articleList5}">
                                        <tr>
                                        <td>
												<c:set var="preface" value="${article.preface }"/>
												<c:choose>
													<c:when test="${article.preface eq '공지' }">
													[공지]
													</c:when>
													<c:when test="${article.preface eq '점검' }">
													[점검]
													</c:when>
													<c:when test="${article.preface eq '이벤트' }">
													[이벤트]
													</c:when>
													<c:when test="${article.preface eq '발표' }">
													[발표]
													</c:when>
													
												</c:choose>
											</td>
											<td class="titletd" >
												<c:if test="${ article.depth > 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard5/images/level.gif" width="${5* article.depth }">
												<img src="${ pageContext.request.contextPath }/bbboard5/images/re.gif">
												</c:if>
												<c:if test="${ article.depth == 0 }">
												<img src="${ pageContext.request.contextPath }/bbboard5/images/level.gif" width="${5* article.depth }">
												</c:if>
												<a href="${ pageContext.request.contextPath}/bbboard5/content.do?num=${article.num}&pageNum=${currentPage}&bn=5">
												${ article.subject }</a>
												<c:if test="${ article.readcount >= 20 }">
												<img src = "${ pageContext.request.contextPath}/bbboard5/images/hot.gif">
												</c:if>		
											</td>
											<td>${ article.writer }
											<c:set value="${article.ip }" var="ipcut"/>
												(
												<script type="text/javascript">
													var ipcutt="<c:out value="${ipcut}"/>";
													var ipc = ipcutt.split('.');
													document.write(ipc[0]);
													document.write(".");
													document.write(ipc[1]);
												</script>
												)
											</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                
            </div>
 	</article>
 	<br><br>
</section>    
<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath}/Resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
     
</body>
</html>