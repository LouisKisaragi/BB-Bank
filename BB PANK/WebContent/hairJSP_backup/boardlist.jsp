<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jsl.dto.BoardDTO" %>
<%@ page import = "jsl.service.BoardService" %>
<%@ page import = "java.util.*" %>
<% 
	BoardService service = new BoardService();
	ArrayList<BoardDTO> list = service.getBoardList("nodel");
	int x = 0, y = 0, count = 0;
	if(request.getParameter("contentno") != null)
	{
		x = Integer.parseInt(request.getParameter("contentno"));
	}
	if(request.getParameter("pageno") != null)
	{
		y = Integer.parseInt(request.getParameter("pageno"));
	}
%>

		<% if(session.getAttribute("login")== null) {%>
             <script type="text/javascript">
             	alert("会員専用ページです。");
              	history.go(-1);
            </script>
           <%}%>
<div class="container-fluid" style = "background-color:black";>    
<h3 align="center" style = "color:white;">口コミ</h3>
<div class = "container">
	<table class = "table table-hover">
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>作成者</th>
		</tr>
		
		<% for(int i = x; i<list.size(); i++) { %>
			<tr>
				<td><%=(i+1) %></td>
				<td><a href = "./FrontController?src=boardview&contentno=<%=list.get(i).getNum() %>">
				<%=list.get(i).getTitle() %></a></td>
				<td><%=list.get(i).getId() %></td>
			</tr>			
		<% 
			count++;
			if (count%10 == 0) break;
		} %>
	</table>
	<ul class="pagination">
		<li><a href="./FrontController?src=board&contentno=0&pageno=0">
		<span class = "glyphicon glyphicon-home" aria-hidden="true"></span>
		</a></li>
	<% 	count = 0;
		int j = 0; // y = 이게 몇 페이지인가
		for(j=y; j<=((list.size()-1)/10); j++) { %>
		<!-- 게시판 페이지 목록 생성 -->
		<li><a href="./FrontController?src=board&contentno=<%out.print(j*10);%>&pageno=<%=(y)%>"><%=(j+1)%></a></li>
	<% 
		count++;
		if (count%10 == 0) break;
		} %>
	<% if((list.size()/10) > j+2) { %>
		<li><a href="./FrontController?src=board&contentno=<%out.print((j+1)*10);%>&pageno=<%=(y+10)%>">>></a></li>
	<% } %>
	</ul>
	<button class = "btn btn-default" onclick="location.href = './FrontController?src=boardwrite'">글쓰기</button>
</div>
</div>