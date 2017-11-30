<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "jsl.dto.GuestDTO" %>
<%@ page import = "jsl.service.GuestService" %>
    
<%
//id name address phone point joindate visiable
GuestService service = new GuestService();
ArrayList<GuestDTO> list = service.getAllGuestList();
%>

<h3 align="center">お客様管理ページ</h3>

	<div class = "container">
		<table class ="table table-hover">
			<tr>
				<th>ID</th>
				<th>お名前</th>
				<th>住所</th>
				<th>連絡先</th>
				<th>ポイント</th>
				<th>加入日</th>
				<th>脱退有無</th>
			</tr>
			
			<%
				for(int i=0; i<list.size(); i++)
				{
					GuestDTO temp = list.get(i); %>
					<tr>
						<td><%=temp.getId() %></td>
						<td><%=temp.getName() %></td>
						<td><%=temp.getAddress() %></td>
						<td><%=temp.getPhone() %></td>
						<td><%=temp.getPoint() %></td>
						<td><%=temp.getJoindate() %></td>
						<td>
						<% if(temp.getVisiable() == 1) { %>
						<button class="btn btn-danger" onclick="location.href = 'delete.do?id=<%=temp.getId() %>&visiable=0'">脱退させる</button>
						<% } else { %>
						<button class="btn btn-default" onclick="location.href = 'delete.do?id=<%=temp.getId() %>&visiable=1'">Accountを復旧する</button>
						<% } %>
						</td>
					</tr>
				<%}%>
		</table>
	</div>

