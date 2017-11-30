<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.util.*" %>
<%@ page import = "jsl.service.ReservationService" %>
<%@ page import = "jsl.dto.ReservationDTO" %>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Date now_day = null;
Date res_day = null;

Calendar cal = Calendar.getInstance();
ReservationService service = new ReservationService();
ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
list = service.getAllReservation();

String message = (String)request.getAttribute("message");

%>

<style>
hr{
border-top: 1px dotted #525252;
}
h2,h3,p,label{
color:white;
}
.bookingvalue {
color:white;
}
</style>

<% if(session.getAttribute("login")== null) {%>
  <script type="text/javascript">
   alert("会員専用ページです。");
   history.go(-1);
  </script>
<%}%>
<body style = "background-color:black">
<div class="container-fluid" style = "background-color:black"> <!-- 가장 바깥의 div -->
	<div class="col-md-6 col-md-offset-3" style = "background-color:black">
		<h3 align="center">予約状況</h3>
		<h4 align="center" style = "color : #BDBDBD;">今、JSL Salongの予約状況でございます。</h4><br />
		
<ul class="nav nav-tabs">
	<%for(int i=0; i<7; i++) { %>
		<li><a data-toggle="tab" href="#menu<%=i%>"><%=cal.get(cal.MONTH)+1 %>月<%=cal.get(cal.DATE) %>日</a></li>
	<% cal.add(Calendar.DATE, 1); } %>
</ul>

<div class="tab-content">
<% cal = Calendar.getInstance(); 
for(int i=0; i<7; i++) { %>
	<div id="menu<%=i %>" class="tab-pane fade">
		<table class="table bookingvalue">
		<%
	for(int j=0; j<list.size(); j++) {
		if((cal.get(cal.MONTH)+1) == (list.get(j).getRes_day().getMonth()+1)) {
			if(cal.get(cal.DATE) == list.get(j).getRes_day().getDate()) { 
				switch(Integer.parseInt(list.get(j).getRes_time()))
				{
				case 1000:
					%><tr>
					<th>10 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1100:
					%><tr>
					<th>11 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1200:
					%><tr>
					<th>12 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id())%></td>
				</tr><% break;
				case 1300:
					%><tr>
					<th>13 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1400:
					%><tr>
					<th>14 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1500:
					%><tr>
					<th>15 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1600:
					%><tr>
					<th>16 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1700:
					%><tr>
					<th>17 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1800:
					%><tr >
					<th>18 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 1900:
					%><tr>
					<th>19 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 2000:
					%><tr>
					<th>20 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 2100:
					%><tr>
					<th>21 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
				case 2200:
					%><tr>
					<th>22 : 00</th>
					<td><%=service.getOneDesigner(list.get(j).getDesigner_id()) %></td>
				</tr><% break;
					default:
					%><tr>

					</tr><% break;
				} 
			%>
<% } } } %> </table> </div> <%cal.add(Calendar.DATE, 1); } %>
</div>

		<button class='btn' data-toggle="modal" data-target='#res_modal'>予約する</button>
		<%if(message != null) { %>
		<p><%=message %></p>
		<% } %>
	</div> 

<script>
function designer_select()
{
	var i = $("#a_store").prop('selectedIndex');
	var content = $('#designer_select > div');
	content.css('display', 'none');
	content.eq(i).css('display', 'block');
	$('#demo').innerHTML = i; 
}
</script>

<!-- modal set -->
		<div class="container" style = "background-color:black">
			<div class="modal fade" id="res_modal" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./booking.do" method="post">
							<div class="modal-header" style = "background-color:black">
								<h2>予約ページ</h2>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<p>JSLサロンへようこそ！</p>
					        </div>
					        <div class="modal-body" style = "background-color:black">
					            <div class="form-group">
					              <label for="sel1">お店選択 </label>
					              <select id="a_store" class="form-control" name="bk_store" onchange="designer_select()">
					                <option value='1'>東京(本店)</option>
					                <option value='2'>東京(チェーン店)</option>
					                <option value='3'>大阪</option>
					                <option value='4'>京都</option>
					              </select>
					              <br>
					
					              <label for="sel1">デザイナー選択</label>
					              <div id="designer_select">
						              <div style="display:block;">
							              <select id="store_designer" class="form-control" name="store_designer" >
							                <!-- 東京(本店) -->
							                <option value="10" selected>Designer LEE 東京(本店)</option>
							                <option value="11">Designer HA 東京(本店)</option>
							                <option value="12">Designer SIM 東京(本店)</option>
							                <option value="13">Designer SHIN 東京(本店)</option>
							              </select>
						              </div>
						              <div style="display:none;">
							              <select id="store_designer" class="form-control" name="store_designer">
							                <!-- 東京(チェーン店) -->
							                <option value="20" selected>Designer HA 東京(チェーン店)</option>
							                <option value="21">Designer YOON 東京(チェーン店)</option>
							                <option value="22">Designer LEE 東京(チェーン店)</option>
							                <option value="23">Designer KIM 東京(チェーン店)</option>
							              </select>
						              </div>
						              <div style="display:none;">
							              <select id="store_designer" class="form-control" name="store_designer">
							                <!-- 東京(チェーン店) -->
							                <option value="30" selected>Designer NANASHI 大阪</option>
							                <option value="31">Designer DOKOUDA 大阪</option>
							                <option value="32">Designer NANDA 大阪</option>
							                <option value="33">Designer KOREWA 大阪</option>
							              </select>
						              </div>
						              <div style="display:none;">
							              <select id="store_designer" class="form-control" name="store_designer">
							                <!-- 東京(チェーン店) -->
							                <option value="40" selected>Designer NANAYA 京都</option>
							                <option value="41">Designer SAKATA 京都</option>
							                <option value="42">Designer NINNIKU 京都</option>
							                <option value="43">Designer NINJIN 京都</option>
							              </select>
						              </div>
					              </div>
					              <br>
					
					              <label for="sel1">スタイル選択</label>
					              <select class="form-control" name="res_gender">
					                <option value="1" selected>男性</option>
					                <option value="2">女性</option>
					              </select>
					              <select class="form-control" name="res_hairStyle">
					                <option value="1" selected>カット........1,500円</option>
					                <option value="2">パーマ.......4,000円</option>
					                <option value="3">カラー......3,500円</option>
					              </select>
					              <br>
					
					              <label for="sel1">Select Date :</label><br>
					              <input type="date" name ="res_date">
					              <br><br>
					
					              <label for="sel1">時間選択</label>
					              <select class="form-control" name="res_time">
					                <option value="1000" selected>10:00</option>
					                <option value="1100">11:00</option>
					                <option value="1200">12:00</option>
					                <option value="1300">13:00</option>
					                <option value="1400">14:00</option>
					                <option value="1500">15:00</option>
					                <option value="1600">16:00</option>
					                <option value="1700">17:00</option>
					                <option value="1800">18:00</option>
					                <option value="1900">19:00</option>
					                <option value="2000">20:00</option>
					                <option value="2100">21:00</option>
					                <option value="2200">22:00</option>
					              </select>
					              <br>
								</div>
					        </div>
					        <div class="modal-footer text-center" style = "background-color:black">
					          <input type="submit" class="btn btn-primary" value="予約" />
					          <input type="reset" class="btn btn-danger" value="リセット" />
					          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
				        </form>
					</div>
				</div>
			</div>
		</div>
</div><!-- 가장 바깥의 div -->
</body>