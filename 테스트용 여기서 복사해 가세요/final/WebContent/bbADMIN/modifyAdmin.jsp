<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "bb.admin.dto.MemberDTO" %>
<%
	MemberDTO admin = (MemberDTO) session.getAttribute("AdminAuthority");

%>

<h3 align="center">管理者設定</h3>
<hr />
<div class = "container">
	<form action="modifyadmin.do" method="post" class="form-horizontal">
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">ID : </label>
			<div class="col-sm-10">
				<%--admin.getId() --%>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">パスワード : </label>
			<div class="col-sm-10">
				<input type="password" class="form-control" name="pass" placeholder="Enter password" value="<%--admin.getPass()--%>">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">名前 : </label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="name" placeholder="<%--admin.getName()--%>" value="<%--admin.getName()--%>">
			</div>
		</div>
		<div class="form-group"> 
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">転送</button>
			</div>
		</div>
	</form>
</div>