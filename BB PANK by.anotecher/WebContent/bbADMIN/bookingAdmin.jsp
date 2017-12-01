<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jsl.dto.ReservationDTO" %>
<%@ page import = "java.util.*" %>
<%@ page import = "jsl.service.ReservationService" %>


<%
ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
ReservationService service = new ReservationService();
list = service.getAllReservation();

%>

<h3 align="center">予約管理ページ</h3>
<br />
<div class = "container">
	<table class="table table-hover">
		<tr>
			<th>担当者</th>
			<th>客名</th>
			<th>性別</th>
			<th>種類</th>		
			<th>予約日</th>
			<th>作業</th>
		</tr>
		
		<% for(int i=0; i<list.size(); i++) { %>
		<tr>
			<td><%=service.getOneDesigner(list.get(i).getDesigner_id())%></td>
				<td data-toggle="modal" data-target='#<%="booking"+list.get(i).getNum() %>'>
				<%=list.get(i).getGuest_id() %></td>
			<td><%=service.genderValue(list.get(i).getGender()) %></td>
			<td><%=service.getContents(list.get(i).getContents()) %></td>
			<td><%=list.get(i).getRes_day() %>|<%=list.get(i).getRes_time() %></td>
			<td>
			<% if(list.get(i).getVisiable() == 1) { %>
			<button type="button"
			onclick="location.href='./delete_booking?num=<%=list.get(i).getNum()%>&visiable=<%=list.get(i).getVisiable()%>&url=./AdminFrontController?src=booking'"
			class="btn btn-danger" >削除</button>
			<% } else {%>
			<button type="button" class="btn btn-warning" >終わり</button>
			<% } %>
			</td>
		</tr>
		<% } %>
	</table>
</div>