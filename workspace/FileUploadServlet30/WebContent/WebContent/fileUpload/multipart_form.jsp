<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>multipart Test</title>
</head>
<body>
	<section>
		<article><!-- <form action =" 최상위 context(프로젝트명)/명령어" -->
			<form action="/FileUploadServlet30/upload" method="post" enctype="multipart/form-data">
				text1 : <input type="text" name="text1"/><br>
				file1 : <input type="file" name="file1"/><br>
				file2 : <input type="file" name="file2"/><br>
				<input type="submit" value="전송"/>
			</form>
		</article>
	</section>
</body>
</html>