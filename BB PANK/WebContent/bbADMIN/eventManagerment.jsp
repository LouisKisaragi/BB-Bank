<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jsl.service.EventService" %>
<%@ page import = "java.util.*" %>
<%@ page import = "jsl.dto.EventDTO" %>
<%
ArrayList<EventDTO> list = null;
EventService service = new EventService();
list = service.loadEventpage();
%>

<h3 align="center">イベント管理ページ</h3>

<div class = "container">
	<table class = "table table-hover">
		<tr>
			<th>banner</th>
			<th>Title</th>
			<th>link</th>
			<th>datavalue</th>
		</tr>
		<% if(list != null) {
				for(int i=0; i<list.size(); i++) { %>
		<tr>
			<td data-toggle="modal" data-target='#<%="event"+list.get(i).getNum()%>'>
			<img src="hairIMAGE/<%=list.get(i).getImage() %>"></td>
			<td><%=list.get(i).getTitle() %></td>
			<td><%=list.get(i).getLinkvalue()%></td>
			<td><%=list.get(i).getDatevalue()%></td>
		</tr>
		<% } %>
	<% } else { %>
		<tr>
			<td colspan="4">イベント登録お願いします。</td>
		</tr>
		<% } %>
	</table>
	<button class = 'btn btn-default' data-toggle="modal" data-target='#insertEvent'>イベント追加</button>
	
	<!-- Modal List -->
	<% if(list != null) { 
	for(int i = 0; i<list.size(); i++) { %>
		<div class="container">
			<div class="modal fade" id="<%="event"+list.get(i).getNum()%>" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./modify_event" enctype="multipart/form-data" method="post">
							<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <input type="text" name="title" value="<%=list.get(i).getTitle()%>" />
					        </div>
					        <div class="modal-body">
					          <textarea rows="3" cols="50" name="contents"><%=list.get(i).getContent()%></textarea><br />
					          バナーイメージ : <input type="file" name="uploadFile" />
					          バナーとつなぐページ :　<input type="text" name="linkvalue" value="<%=list.get(i).getLinkvalue() %>" /><br />
					          <p>イベントが終わる日 : <%=list.get(i).getDatevalue() %></p>
					          <input type ="date" name = "datevalue"/>
					          <input type="hidden" name="image" value="<%=list.get(i).getImage() %>" />
					          <input type="hidden" name="num" value="<%=list.get(i).getNum() %>" />
					          <input type="hidden" name="visiable" value="<%=list.get(i).getVisiable() %>" />
					          <input type="hidden" name="url" value="./AdminFrontController?src=event" />
					        </div>
					        <div class="modal-footer">
					          <input type="submit" class="btn btn-primary" value="転送" />
				       		  <% if(list.get(i).getVisiable() == 1) { %>
					          <button type="button"
					          onclick="location.href='./visiable_event?num=<%=list.get(i).getNum()%>&visiable=<%=list.get(i).getVisiable()%>&url=./AdminFrontController?src=event'"
					          class="btn btn-danger" >終わり</button>
					          <% } else {%>
					          <button type="button"
					          onclick="location.href='./visiable_event?num=<%=list.get(i).getNum()%>&visiable=<%=list.get(i).getVisiable()%>&url=./AdminFrontController?src=event'"
					          class="btn btn-warning" >再スタート</button>
					          <% } %>

					          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
				        </form>
					</div>
				</div>
			</div>
		</div>
		<% } 
		} %>
		
		<!-- 등록 Modal -->
		<div class="container">
			<div class="modal fade" id="insertEvent" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./insert_event" enctype="multipart/form-data" method="post">
							<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          Title : <input type="text" name="title"/>
					        </div>
					        <div class="modal-body">
					          <p>イベントの内容</p>
					          <textarea rows="3" cols="50" name="contents"></textarea><br />
					          バナーイメージ : <input type="file" name="uploadFile" />
					          バナーとつなぐページ :　<input type="text" name="linkvalue" /><br />
					          <p>イベントが終わる日</p>
					          <input type = "date" name = "datevalue" />
					          <input type="hidden" name="url" value="./AdminFrontController?src=event" />
					        </div>
					        <div class="modal-footer">
					          <input type="submit" class="btn btn-primary" value="転送" />
					          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
				        </form>
					</div>
				</div>
			</div>
		</div>
</div>