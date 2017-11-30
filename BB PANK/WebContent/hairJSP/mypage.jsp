<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsl.dto.GuestDTO" %>
<%@ page import="jsl.dto.ReservationDTO" %>
<%@ page import="jsl.service.ReservationService" %>
<%@ page import="jsl.mypage.MypageService" %>
<%@ page import="jsl.dto.PointManagerDTO" %>
<%@ page import="java.util.*" %>
<%@ page import ="jsl.dto.QnADTO" %>
<%
	ArrayList<ReservationDTO> list = new ArrayList<ReservationDTO>();
	GuestDTO guest = (GuestDTO) session.getAttribute("login");
	ReservationService res_service = new ReservationService();
	list = res_service.getOneReservation(guest.getId());
	MypageService mserv = new MypageService(guest.getId());
	ArrayList<PointManagerDTO> point_list = new ArrayList<PointManagerDTO>();
	ArrayList<QnADTO> qna_list = new ArrayList<QnADTO>();
	qna_list = mserv.getBoardList();

%>
<style>
#p{
border-top: 1px solid white;
}
h2,h3,label,p,a,th,td{
color : white;
}

hr{
border-top: 1px dotted #525252;
}

.board_button_color{
color : black;
}
</style>

          <!-- 회원정보수정 폼 -->
          <div id="modify" style = "background-color:black">
          <br>
          </div>
<div class="col-md-12">
           <div class="col-md-8 col-md-offset-2">
           <div class="container-fluid text-center" style = "background-color:black" >
          <form action="./modify.do" method="post">
              <h2>登録情報修正 <small>修正する情報を入力してください</small></h2>
           <hr>
           
             <div class = "container-fluid">
             
              <div class="form-group">
              <div class="col-md-3 text-center">
                <label for="MyPage">名前 :</label>
                </div>
               <div class="col-md-9 text-center">
                <input type="text" class="form-control" name="name" value="<%= guest.getName() %>">
              </div>
              </div>
              <br><br>
              
              <div class="form-group">
              <div class = "col-md-3 text-center">
                <label for="MyPage">パスワード:</label>
                </div>
                <div class = "col-md-9 text-center">
                <input type="text" class="form-control" name="pass" placeholder="新しいパスワードを入力してください" />
                <input type="hidden" name="moto_pass" value="<%guest.getPass(); %>" />
              </div>
              </div>
              <br><br>

              <div class="form-group">
              <div class = "col-md-3 text-center">
                <label for="MyPage">パスワード確認 :</label>
                </div>
                <div class = "col-md-9 text-center">
                <input type="text" class="form-control" name="pass_ch" placeholder="新しいパスワードを再入力してください">
                 </div>
                 <br>
                <p class="help-block">パスワード確認のためもう一回入力してください </p>
                <p class="color">${pass_error } </p>
              </div>
              <br>

              <div class="form-group">
              <div class = "col-md-3 text-center">
                 <label for="MyPage">住所 :</label>
               </div>                
               <div class = "col-md-9 text-center">
                <input type="text" class="form-control" name="address" value="<%= guest.getAddress() %>">
              </div>
              </div>
              <br><br>
              
              <div class="form-group">
              <div class = "col-md-3 text-center">
                <label for="MyPage">連絡先 :</label>
                </div>
                <div class = "col-md-9 text-center">
                <input type="text" class="form-control" name="phone" value="<%= guest.getPhone() %>">
                </div>
                 <p class="help-block">- なしで入力してください</p>
              </div>
              <br><br>
              
              <div class="form-group">
                <div class="col-sm-12 text-center">
                  <p class="color">${ modify_message }</p>
                  <button class="btn btn-primary" type="submit">決定</button>
                  <button class="btn btn-success" type="reset">リセット</button>
                  <button class="btn btn-danger" type="submit">登録解除</button>
                </div>
              </div>

            </div>

          </form>
        </div>
        </div>
</div>


        <!-- 예약확인 -->
        <div id="reservation" style = "background-color:black">
        <br>
        </div>
        
        <div class="container-fluid text-center" style = "background-color:black" >
         <div class="col-md-8 col-md-offset-2">
         <br>
            <h2>予約案内 <small>予約の確認・変更が可能です。</small></h2>
          <hr>

          <div class="container-fluid">

            <!-- 예약이 없을 경우 -->
            <% if (list == null) { %>
            <h3>今 <%= guest.getName() %> 様の予約がございません。</h3>
            <hr>
            <% } else { %>
            <!-- 예약 확인 -->
            <h3>予約確認</h3>
            <br>
            <table class="table table-bordered">
              <tbody>
              <tr >
				<th width = "15%" style = "text-align : center;">店舗</th>
				<th width = "15%" style = "text-align : center;">デザイナー</th>
				<th width = "25%" style = "text-align : center;">内容(スタイル)</th>
				<th width = "15%" style = "text-align : center;">予約日にち</th>
				<th width = "15%" style = "text-align : center;">性別</th>
				<th width = "15%" style = "text-align : center;"></th>
			  </tr>
			  <% for(int i=0; i<list.size(); i++) { %>
    		  <tr>
                <td></td>
                <td><%=res_service.getOneDesigner(list.get(i).getDesigner_id()) %></td>
                <td><%=res_service.getContents(list.get(i).getContents()) %></td>
                <td><%=list.get(i).getRes_day() %> | <%=list.get(i).getRes_time() %></td>
                <td><%=res_service.genderValue(list.get(i).getGender()) %></td>
                <td><input type="button" value="予約変更" class="pull-left board_button_color" data-toggle="modal" data-target='#res_modal<%=i %>'/>
                <button class="pull-right board_button_color" onclick="location.href = './booking_delete.do?num=<%=list.get(i).getNum()%>'">予約キャンセル</button></td>
              </tr>
              <% } %>
              </tbody>
             </table>
             </div>
			<br>
		
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
		
		<% for(int i=0; i<list.size(); i++) { %>
		<div class="container" style = "background-color:black">
			<div class="modal fade" id="res_modal<%=i %>" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./booking_modify.do" method="post">
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
					              <input type="hidden" name="num" value="<%=list.get(i).getNum() %>" />
					              <input type="hidden" name="visiable" value="<%=list.get(i).getVisiable() %>" />
								</div>
					        </div>
					        <div class="modal-footer text-center" style = "background-color:black">
					          <input type="submit" class="btn btn-primary" value="修正" />
					          <input type="reset" class="btn btn-danger" value="リセット" />
					          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
				        </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
		<% } //modal for close %>
		<% } //if close %>
		
      <!-- 1 : 1 문의 -->
      <div id="qna" style = "background-color:black">
      <br>
      </div>
<div class = "container-fluid text-center" style = "background-color:black">
   <div class="col-md-8 col-md-offset-2">
       <div class="container-fluid text-center" style = "background-color:black" >
          <h2>問い合わせ <small>確認次第お返しいたします。</small></h2>
          <hr>
          <h3 align = "center">Q&A</h3>
          <br>
    <table class="table table-bordered">
    	<tr>
    		<th>タイトル</th>
    		<th>返答有無</th>
    	</tr>
    	<% for(int i=0; i<qna_list.size(); i++) { %>
    		<tr>
    			<td><a data-toggle="modal" data-target='#qna_modal<%=qna_list.get(i).getNumber() %>'><%=qna_list.get(i).getTitle() %></a></td>
    			<td>
    			<% if(qna_list.get(i).getAnswer() != null) { 
    				out.print("あり");
    				} else { out.print("なし"); }%>
    			</td>
    		</tr>
    	<% } %>
    </table>
    
    <!-- QnA answer modal -->
    <% for(int i=0; i<qna_list.size(); i++) { %>
    <div class="container">
    	<div class="modal fade" id="qna_modal<%=qna_list.get(i).getNumber() %>" role="dialog">
    		<div class="modal-dialog">
    			<div class="modal-content">
    				<div class="modal-header">
    					タイトル : <%=qna_list.get(i).getTitle() %>
    				</div>
    				<div class="modal-body">
    					<%=qna_list.get(i).getContents() %>
    					
    					<% if(qna_list.get(i).getAnswer() != null) { %>
    					<hr />
    					<%=qna_list.get(i).getAnswer() %>
    					<% } %>
    				</div>
    				<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
    			</div>
    		</div>
    	</div>
    </div>
    <% } %>
          
          
          
	<form action="qnawrite.do" method="post">
		<table class="table table-bordered">
			<tbody>
				<tr>
                	<th>タイトル: </th>
                	<td><input type="text" placeholder="タイトルを入力してください" name="title" class="form-control"/></td>
            	</tr>
            	<tr>
                	<th>内容: </th>
                	<td height ="100"><textarea cols="10" rows="5" placeholder="内容を入力してください" name="content" class="form-control"></textarea></td>
            	</tr>
            	<tr>
                	<td colspan="2">
                    	<input type="submit" value="決定" class="pull-right board_button_color"/>
                    	<input type="reset" value="リセット" class="pull-left board_button_color"/>
                	</td>
            	</tr>
			</tbody>
		</table>
	</form>
</div>
<br>
</div>
</div>



<!-- 마일리지 페이지 -->

    <!-- grid form -->
      <div id="point" style = "background-color:black">
      <br>
      </div>
<div class="col-md-12">
     <div class="col-md-8 col-md-offset-2">
     <div class="container-fluid text-center" style = "background-color:black" >
          <h2>ポイント <small>ポイント履歴</small></h2>
          <hr id = "p" />
     </div>
     
     <div class="col-md-4">
     	<div class='row'>
     		<div class="col-md-12 text-center">
     			<p>ポイント残高</p>
     			<hr>
    			<div class="panel panel-default">
                <img src="hairIMAGE/img2.jpg" class="img-responsive" style='display: width :450px; height=150px;'>
                <img src="hairIMAGE/img1.png" class="img-circle" style='display: block; width: 120px; margin: -60px auto 0;'>
                <div class="panel-body">
                  <p style = "color:black;" class='lead text-center'>ポイント : <%=mserv.getMyPoint() %>点</p>
                  <hr>
                  <ul class='list-unstyled'>
					<li class='text-center'><%= guest.getName() %> 様</li>
                    <li class='text-center'>ご利用ありがとうございます。</li>
                    <li class='text-center'>-JSL Salon-</li>
                  </ul>
                  <a class='btn btn-default btn-block' href='<%=request.getContextPath()%>/hairJSP/boardlist.jsp'>口コミへ</a>
                </div>
                </div>
			</div>
		</div>
	</div><!-- col-md-4 end -->

        <div class="col-md-4">
          <div class='row'>
            <div class="col-md-12 text-center">
             <p>獲得履歴</p>
              <hr>

              <div class="panel panel-default">
                <div class="container-fluid">
                    <div class="row">
                      <div class="col-md-12">
                        <table class="table table-hover">
                            <tr>
                              <td style = "color:black;">積立日</td>
                              <td style = "color:black;">内容</td>
                              <td style = "color:black;">積立ポイント</td>
                            </tr>
                        </table>
                        <div style = "width : 100%; height:393px; overflow : auto">
                        <table class ="table table-hover">
                          <tbody>
	                           <%point_list = mserv.getPointList();
	                                                
	                           for(int i=0;i < point_list.size();i++){ 
	                        	   if(point_list.get(i).getP_calcul()>0){
	                            %>
	                            <tr class="success">
	                              <td style = "color:black;"><%= point_list.get(i).getP_date() %></td>
	                              <td style = "color:black;"><%= point_list.get(i).getP_cont() %></td>
	                              <td style = "color:black;"><%= point_list.get(i).getP_calcul() %></td>                            
	                            </tr>
	                           <% }} %>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>

              </div>

            </div>
          </div>
        </div>
</div>


        <div class="col-md-4">
          <div class='row'>
            <div class="col-md-12 text-center">
              <p>利用履歴</p>
              <hr>

              <div class="panel panel-default">
                <div class="container-fluid">
                     <div class="row">
                        <div class="col-md-12">
                           <table class="table table-hover">
                                 <tr>
                              <td style = "color:black;">利用日</td>
                                    <td style = "color:black;">内容</td>
                                    <td style = "color:black;">利用金額</td>
                                 </tr>
							</table>
							<div style = "width : 100%; height:393px; overflow : auto">
                              <table class ="table table-hover">
                              <tbody>
                              		<% for(int j=0;j < point_list.size();j++){ 
		                        	   if(point_list.get(j).getP_calcul()<0){
		                               %>
		                            <tr class="danger">
		                              <td style = "color:black;"><%= point_list.get(j).getP_date() %></td>
		                              <td style = "color:black;"><%= point_list.get(j).getP_cont() %></td>
		                             <td style = "color:black;"><%= point_list.get(j).getP_calcul() %></td>                            
		                            </tr>
		                           <% }} %>
                              </tbody>
                           </table>
                        </div>
                        </div>
                        </div>
                     </div>
                  </div>
              </div>

            </div>
          </div>
        </div>
    </div> 