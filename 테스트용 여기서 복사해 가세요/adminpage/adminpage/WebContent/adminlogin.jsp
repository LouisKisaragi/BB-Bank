<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = null;
	if(request.getAttribute("message") != null) message = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	
	    <!-- 부트스트랩 적용-->	    
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
				<h3>Admin login page</h3>
				<p><small>관리자(admin)계정을 위한 페이지 입니다.<br/>관리 권한이 없으면 로그인 할 수 없습니다.
				</small></p><hr/>
				<font action="" method="post">
				<!-- 아이디 입력 -->
				<div class ="form-group">
					<label class="control-label">ID</label>
					<input class="form-control" type="text" name="id" placeholder ="아이디를 입력해 주세요"/>					
				</div>
				<div class="form-group">
					<label class="control-label">Password</label>
					<input class="form-control" type="password" name="pass" placeholder="비밀번호를 입력해 주세요" />
				</div>
				<div class="form-group">
					<input type="submit" value="login" class="btn btn-sm btn-primary" />
				</div>
				</font>
				
				</div>				
			</div>
			</div>
			<hr />
			<%
			 if(message != null) out.print(message);
			%>

</body>
</html>