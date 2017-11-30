<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jsl.dto.BoardDTO" %>
<%@ page import = "jsl.service.BoardService" %>
<%@ page import = "java.util.*" %>
<% 
	BoardService service = new BoardService();
	ArrayList<BoardDTO> list = service.getBoardList("all");
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

<h3 align="center">口コミ管理ページ</h3>

<div class = "container">
	<table class = "table table-hover">
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>作成者</th>
			<th>作業</th>
		</tr>
		
		<% for(int i = x; i<list.size(); i++) { %>
			<tr>
				<td><%=(i+1) %></td>
				<td data-toggle="modal" data-target='#<%="board"+list.get(i).getNum()%>'>
				<%=list.get(i).getTitle() %></td>
				<td><%=list.get(i).getId() %></td>
				<td>
					<% if(list.get(i).getVisiable() == 1)
					{
						out.print("正常");
					} else out.print("削除待機");
					%>
				</td>
			</tr>			
		<% 
			count++;
			if (count%10 == 0) break;
		} %>
	</table>
	<ul class="pagination">
		<li><a href="./AdminFrontController?src=board&contentno=0&pageno=0">
		<span class = "glyphicon glyphicon-home" aria-hidden="true"></span>
		</a></li>
	<% 	count = 0;
		int j = 0; // y = 이게 몇 페이지인가
		for(j=y; j<=((list.size()-1)/10); j++) { %>
		<!-- 게시판 페이지 목록 생성 -->
		<li><a href="./AdminFrontController?src=board&contentno=<%out.print(j*10);%>&pageno=<%=(y)%>"><%=(j+1)%></a></li>
	<% 
		count++;
		if (count%10 == 0) break;
		} %>
	<% if((list.size()/10) > j+2) { %>
		<li><a href="./AdminFrontController?src=board&contentno=<%out.print((j+1)*10);%>&pageno=<%=(y+10)%>">>></a></li>
	<% } %>
	</ul>
	
	
	<!-- Modal List -->
	
	<% count = 0; 
	for(int i = x; i<list.size(); i++) { %>
		<div class="container">
			<div class="modal fade" id="<%="board"+list.get(i).getNum()%>" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./modify_board" method="post">
							<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <input type="text" name="title" value="<%=list.get(i).getTitle()%>" />
					          <input type="hidden" name="id" value="<%=list.get(i).getId() %>" />
					        </div>
					        <div class="modal-body">
					          <textarea rows="3" cols="50" name="contents"><%=list.get(i).getContents()%></textarea><br />
					          이미지 집어넣을 폼
					          <input type="hidden" name="image" value="<%=list.get(i).getImage() %>" />
					          <input type="hidden" name="num" value="<%=list.get(i).getNum() %>" />
					          <input type="hidden" name="visiable" value="<%=list.get(i).getVisiable() %>" />
					          <input type="hidden" name="url" value="./AdminFrontController?src=board&contentno=<%=(i/10)*10 %>&pageno=<%=(y)%>" />
					        </div>
					        <div class="modal-footer">
					          <input type="submit" class="btn btn-primary" value="転送" />
				       		  <% if(list.get(i).getVisiable() == 1) { %>
					          <button type="button"
					          onclick="location.href='./delete_board?num=<%=list.get(i).getNum()%>&visiable=<%=list.get(i).getVisiable()%>&url=./AdminFrontController?src=board&contentno=<%=(i/10)*10 %>&pageno=<%=(y)%>'"
					          class="btn btn-danger" >削除</button>
					          <% } else {%>
					          <button type="button"
					          onclick="location.href='./delete_board?num=<%=list.get(i).getNum()%>&visiable=<%=list.get(i).getVisiable()%>&url=./AdminFrontController?src=board&contentno=<%=(i/10)*10 %>&pageno=<%=(y)%>'"
					          class="btn btn-warning" >復旧</button>
					          <% } %>

					          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
				        </form>
					</div>
				</div>
			</div>
		</div>
		<% 
			count++;
			if (count%10 == 0) break;
		} %>
</div>