<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "bb.admin.dto.PointManagerDTO" %>
<%@ page import = "bb.admin.point.PointService" %>

<%
PointService service = new PointService();
ArrayList<PointManagerDTO> list = service.LoadPointLog();
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
    
<h3 align="center">포인트 관리</h3>

<!-- 이 페이지에서 하는 작업
기존 기능은 포인트 입/차감 기능을 갖고 있으으로
'특정 회원의 마일리지 로그를 불러오는 작업' 만 수행하게 끔 페이지를 수정해야 한다.
-->

<h4 align="center">ポイントを与える</h4>
<% if(request.getAttribute("message") != null) { %>
	<script type="text/javascript">
		alert('<%=(String)request.getAttribute("message")%>');
	</script>
<% } %>
<hr />
<div class = "container">
	<form action = "./adminPointGive" method = "post">
		<div class="form-group">
			<label>ポイントを与えるID</label><br />
			<input type="text" name="id" /><br />
			<label>与えるポイント</label><br />
			<input type="text" name="point" /><br />
			<label>内容</label><br />
			<input type="text" name="context" /><br />
			<input type="submit" value="与える" class="btn btn-default" />
		</div>		
	</form>
	<hr />
	<!-- 이 아래엔 포인트 로그 -->
	<h4 align="center">ポイントログ</h4>
	<br />
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>point</th>
			<th>内容</th>
			<th>与えた日</th>
		</tr>
		<%-- <% for(int i = x; i<list.size(); i++) { %>
			<tr>
				<td><%=list.get(i).getMember_id() %></td>
				<td><%=list.get(i).getP_calcul() %></td>
				포인트의 증감치
				<td><%=list.get(i).getP_cont() %></td>
				<td>
					<%=list.get(i).getP_date() %>
				</td>
			</tr>
			<% 
			count++;
			if (count%10 == 0) break;
		} %>  --%>
		
	</table>
	<ul class="pagination">
		<li><a href="./AdminFrontController?src=point&contentno=0&pageno=0">
		<span class = "glyphicon glyphicon-home" aria-hidden="true"></span>
		</a></li>
	<% 	count = 0;
		int j = 0; // y = 이게 몇 페이지인가
		for(j=y; j<=((list.size()-1)/10); j++) { %>
		<!-- 게시판 페이지 목록 생성 -->
		<li><a href="./AdminFrontController?src=point&contentno=<%out.print(j*10);%>&pageno=<%=(y)%>"><%=(j+1)%></a></li>
	<% 
		count++;
		if (count%10 == 0) break;
		} %>
	<% if((list.size()/10) > j+2) { %>
		<li><a href="./AdminFrontController?src=point&contentno=<%out.print((j+1)*10);%>&pageno=<%=(y+10)%>">>></a></li>
	<% } %>
	</ul>	
</div>