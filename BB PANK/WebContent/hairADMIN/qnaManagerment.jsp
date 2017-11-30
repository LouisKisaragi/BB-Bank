<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "jsl.dto.QnADTO" %>
<%@ page import = "jsl.service.BoardService" %>

<%
BoardService service = new BoardService();
ArrayList<QnADTO> list = service.getQnAList();
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

<h3 align="center">お問い合せ管理ページ</h3>

<div class = "container">
	<table class = "table table-hover">
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>作成者</th>
			<th>返答有無</th>
		</tr>
		
		<% for(int i = x; i<list.size(); i++) { %>
			<tr>
				<td><%=(i+1) %></td>
				<td data-toggle="modal" data-target='#<%="qna"+list.get(i).getNumber()%>'>
				<%=list.get(i).getTitle() %>
				</td>
				<td><%=list.get(i).getGuest_id() %></td>
				<td>
					<% if(list.get(i).getAnswer() != null)
					{
						out.print("返答あり");
					} else out.print("返答なし");
					%>
				</td>
			</tr>			
		<% 
			count++;
			if (count%10 == 0) break;
		} %>
	</table>
	<ul class="pagination">
		<li><a href="./AdminFrontController?src=qna&contentno=0&pageno=0">
		<span class = "glyphicon glyphicon-home" aria-hidden="true"></span>
		</a></li>
	<% 	count = 0;
		int j = 0; // y = 이게 몇 페이지인가
		for(j=y; j<=((list.size()-1)/10); j++) { %>
		<!-- 게시판 페이지 목록 생성 -->
		<li><a href="./AdminFrontController?src=qna&contentno=<%out.print(j*10);%>&pageno=<%=(y)%>"><%=(j+1)%></a></li>
	<% 
		count++;
		if (count%10 == 0) break;
		} %>
	<% if((list.size()/10) > j+2) { %>
		<li><a href="./AdminFrontController?src=qna&contentno=<%out.print((j+1)*10);%>&pageno=<%=(y+10)%>">>></a></li>
	<% } %>
	</ul>
	
	<!-- Modal List -->
	
	<% count = 0; 
	for(int i = x; i<list.size(); i++) { %>
		<div class="container">
			<div class="modal fade" id="<%="qna"+list.get(i).getNumber()%>" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./qna_board" method="post">
							<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <p><%=list.get(i).getTitle()%></p>
					          <input type="hidden" name="title" value="<%=list.get(i).getTitle()%>" />
					          <input type="hidden" name="id" value="<%=list.get(i).getGuest_id() %>" />
					        </div>
					        <div class="modal-body">
					          <p>質問内容</p><br />
					          <p><%=list.get(i).getContents()%></p>
					          <textarea rows="3" cols="50" name="answer"><%=list.get(i).getAnswer()%></textarea><br />
					          <input type="hidden" name="num" value="<%=list.get(i).getNumber() %>" />
					          <input type="hidden" name="contents" value="<%=list.get(i).getContents() %>" />
					          <input type="hidden" name="url" value="./AdminFrontController?src=qna&contentno=<%=(i/10)*10 %>&pageno=<%=(y)%>" />
					        </div>
					        <div class="modal-footer">
					          <input type="submit" class="btn btn-primary" value="返答する" />
					          <button type="button"
					          onclick="location.href='./delete_qna?num=<%=list.get(i).getNumber()%>&url=./AdminFrontController?src=qna&contentno=<%=(i/10)*10 %>&pageno=<%=(y)%>'"
					          class="btn btn-danger" >削除</button>
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