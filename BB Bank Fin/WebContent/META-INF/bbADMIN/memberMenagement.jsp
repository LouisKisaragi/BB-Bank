<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "bb.dto.MemberDTO" %>
<%@ page import = "bb.service.GuestService" %>
    
<%
//id name address phone point joindate visiable
MemberService service = new MemberService();
ArrayList<MemberDTO> list = service.getAllGuestList();
%>

<h3 align="center">회원관리페이지</h3>

	<div class = "container">
		<table class ="table table-hover">
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>포인트</th>
				<th>가입날짜</th>
				<th>탈퇴여부</th>
			</tr>
			
			<%
				for(int i=0; i<list.size(); i++)
				{
					MemberDTO temp = list.get(i); %>
					<tr>
						<td><%=temp.getId() %></td>
						<td><%=temp.getName() %></td>
						<td><%=temp.getEmail() %></td>
						<td><%=temp.getPoint() %></td>
						<td><%=temp.getJoindate() %></td>
						<td>
						<% if(temp.getVisiable() == 1) { %>
						<button class="btn btn-danger" onclick="location.href = 'delete.do?id=<%=temp.getId() %>&visiable=0'">탈퇴하기</button>
						<% } else { %>
						<button class="btn btn-default" onclick="location.href = 'delete.do?id=<%=temp.getId() %>&visiable=1'">Account 복구</button>
						<% } %>
						</td>
					</tr>
				<%}%>
		</table>
	</div>

