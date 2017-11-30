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
</style>
</head>
<body>
<div class="container-fluid" style = "background-color:black;">
 <h2 align = "center">口コミ</h2>
 <br>
<table class="table table-bordered">
    <tbody>
            <tr>
                <th>タイトル: <%=dto.getTitle() %></th>
                <td></td>
            </tr>
            <tr>
                <th>内容: </th>
                <td height ="300"><%=dto.getContents() %></td>
            </tr>
            <tr>
                <th>添付ファイル: </th>
                <td></td>
            </tr>
            <tr>
                <td colspan="2">
                	<% if(dto.getId() == user_dto.getId()) {%>                     <% } %>
                    <input type="button" value="修正" onclick="location.href='./FrontController?src=boardmodify&contentno=<%=dto.getNum() %>'" class="pull-right"/>

                    <input type="button" value="リストへ" class="pull-right" onclick="location.href='./FrontController?src=board'"/>
                </td>
            </tr>
    </tbody>
</table>
</div>
</body>
</html>