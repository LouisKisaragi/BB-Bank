<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = null;
	if(request.getAttribute("message") != null) message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>ADMIN Login</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
			  <div class="col-md-offset-4 col-sm-6 col-md-4 col-xs-12">
			  	<h3>BB PANK Admin login page</h3><br />
			  	<p><small>관리자 로그인 페이지 입니다.<br />
			  			관리 권한이 없으면 로그인이 불가능합니다.
			  		</small></p><hr />
<<<<<<< HEAD:BB Bank Fin/WebContent/bbADMIN/adminLogin.jsp
				<form action="../adminLogin.do" method="post">
=======
				<form action="BB_Bank/adminLogin.do" method="post">
>>>>>>> 3f2b6f44cea533eb651386cb6143c69e926f4e3d:.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/BB_Bank/bbADMIN/adminLogin.jsp
				<div class="form-group">
					<label class="control-label">ID</label>
					<input class="form-control" type="text" name="id" placeholder="ID를 입력 하세요" />
				</div>
				<div class="form-group">
					<label class="control-label">Password</label>
					<input class="form-control" type="password" name="pass" placeholder="패스워드를 입력하세요" />
				</div>
				<div class="form-group">
			  		<input type="submit" value="Login" class="btn btn-sm btn-primary" />	
			  	</div>
				</form>
			  </div>
			</div>
		</div>
		<hr />
		<% 
			if(message != null) out.print(message);
		%>
	</body>
</html>