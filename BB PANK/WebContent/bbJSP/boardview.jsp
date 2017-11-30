<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jsl.service.BoardService" %>
<%@ page import = "jsl.dto.BoardDTO" %>
<%@ page import = "jsl.dto.GuestDTO" %>

<%
BoardDTO dto = new BoardDTO();
BoardService service = new BoardService();
int num = Integer.parseInt(request.getParameter("contentno"));
System.out.println(num);
dto = service.getOneNote(num);

GuestDTO user_dto = (GuestDTO)session.getAttribute("login");


%>
    
<style>
h2,th{
color : white;
}
td{
color : #A6A6A6;
}
.board_button_color{
color : black;
}
</style>

	<div class="col-md-10 col-md-offset-1">
		<div class="container-fluid" style = "background-color:black;">
		 <h2 align = "center">口コミ</h2>
		 <br>
		<form action="view_board" method="post" encType="multipart/form-data">
		<table class="table table-bordered">
		    <tbody>
		            <tr>
		                <th width = "30%">タイトル:</th>
		                <td><%=dto.getTitle() %></td>
		            </tr>
		            <tr>
		                <th>内容: </th>
		                <td height ="500"><%=dto.getContents() %></td>
		            </tr>
		            <tr>
		                <th>イメージ: </th>
		                <td height = "500">
		                <img src = "./hairIMAGE/hairSELF/<%=dto.getImage() %>">		                
		                </td>
		            </tr>
		            <tr>
		                <td colspan="2">
		                	<% if(dto.getId() == user_dto.getId()) {%>                     <% } %>
		                    <input type="button" value="修正" onclick="location.href='./FrontController?src=boardmodify&contentno=<%=dto.getNum() %>'" class="board_button_color pull-right"/>
		
		                    <input type="button" value="リストへ" class="board_button_color pull-left" onclick="location.href='./FrontController?src=board'"/>
		                </td>
		            </tr>
		    </tbody>
		</table>
		</form>
		</div>
	</div>