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
			  	<h3>管理者ログイン</h3><br />
			  	<p><small>管理者ログイン画面です。<br />
			  			権限がない方はお引き取り願います。
			  		</small></p><hr />
				<form action="/JSL_salong/adminLogin.do" method="post">
				<div class="form-group">
					<label class="control-label">ID</label>
					<input class="form-control" type="text" name="id" placeholder="IDを入力してください。" />
				</div>
				<div class="form-group">
					<label class="control-label">パスワード</label>
					<input class="form-control" type="password" name="pass" placeholder="パスワードを入力してください。" />
				</div>
				<div class="form-group">
			  		<input type="submit" value="接続" class="btn btn-sm btn-primary" />	
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