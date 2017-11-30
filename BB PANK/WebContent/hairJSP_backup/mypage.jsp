<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsl.dto.GuestDTO" %>
    <%
       GuestDTO guest = (GuestDTO) session.getAttribute("login");
    %>
<style>
#p{
border-top: 1px solid white;
}
h2,h3,label,p,a,th{
color : white;
}

hr{
border-top: 1px dotted #525252;
}
</style>
<body>
<div style = "background-color:black">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
       <div class="row">
          <div class="col-md-12">
             <ul class="nav nav-tabs">
                <li class="active">
                   <a href="#modify" class="scoll">会員情報修正</a>
                </li>
                <li>
                   <a href="#reservation" class="scroll">予約確認</a>
                </li>
                <li>
                   <a href="#qna" class="scoll">問い合わせ</a>
                </li>
                <li>
                   <a href="#point" class="scoll">ポイント履歴</a>
                </li>
             </ul>       
           </div>
    	</div>
    </div>
  </nav>
<br>
</div>

          <!-- 회원정보수정 폼 -->
<div class="container-fluid text-center" style = "background-color:black" >
	<form action="./modify.do" method="post">
		<h2>登録情報修正 <small>修正する情報を入力してください</small></h2>
           <hr>
        <div class= "col-md-5 col-md-offset-4">
        	<div class="form-group">
                <label for="MyPage">名前 :</label>
                <input type="text" class="form-control" name="name" value="<%= guest.getName() %>">
            </div>

            <div class="form-group">
                <label for="MyPage">パスワード:</label>
                <input type="text" class="form-control" name="pass" placeholder="新しいパスワードを入力してください">
            </div>

            <div class="form-group">
                <label for="MyPage">パスワード確認 :</label>
                <input type="text" class="form-control" name="pass_ch" placeholder="新しいパスワードを再入力してください">
                <p class="help-block">パスワード確認のためもう一回入力してください </p>
                <p class="color">${pass_error } </p>
            </div>

            <div class="form-group">
                <label for="MyPage">住所 :</label>
                <input type="text" class="form-control" name="address" value="<%= guest.getAddress() %>">
            </div>

            <div class="form-group">
                <label for="MyPage">連絡先 :</label>
                <input type="text" class="form-control" name="phone" value="<%= guest.getPhone() %>">
            	<p class="help-block">- なしで入力してください</p>
            </div>

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

        <!-- 예약확인 -->
        <div id="reservation" style = "background-color:black">
        <br>
        </div>
         <div class="container-fluid text-center" style = "background-color:black" >
        <form action="bookedView.do" method="post">
          <div class="page-header">
            <h2>予約案内 <small>予約の確認・変更が可能です。</small></h2>
          </div>
          <div class="col-md-6 col-md-offset-3">

          <div class="container-fluid">

            <!-- 예약이 없을 경우 -->
            <h3>今 <%= guest.getName() %> 様の予約がございません。</h3>
            <hr>
            <!-- 예약 확인 -->
            <h3>予約確認</h3>
            <br>
             <div class="row">
                <div class="col-md-3">
                <label for="sel1">店舗 :</label>
                </div>

                <div class="col-md-9">
                <input type="text" class="form-control" name="name" placeholder="大阪">
                </div>
             </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">デザイナ:</label>
                </div>

                <div class="col-md-9">
                <input type="text" class="form-control" name="name" placeholder="Designer NANASHI 大阪">
                </div>
             </div><br>

            <div class="row">
              <div class="col-md-3">
                <label for="sel1">性別 :</label>
              </div>

              <div class="col-md-9">
                <input type="text" class="form-control" name="name" placeholder="男性">
              </div>
            </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">スタイル:</label>
                </div>

                <div class="col-md-9">
                <input type="text" class="form-control" name="name" placeholder="カット - 1,500円">
                </div>
             </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">予約日にち:</label>
                </div>

                <div class="col-md-9">
                <input type="text" class="form-control" name="name" placeholder="2017.08.10">
                </div>
             </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">予約時間:</label>
                </div>

                <div class="col-md-9">
                <input type="text" class="form-control" name="name" placeholder="10:00">
                </div>
             </div><br>

          </div>
          <br>

          <div class="form-group">
            <div class="col-sm-12 text-center">
              <button class="btn btn-success" type="submit">予約変更</button>
              <button class="btn btn-danger" type="submit">予約キャンセル</button>
            </div>
          </div>
<br>
          <!-- 예약 변경을 눌렀을 때 -->

<div class="container-fluid" style = "background-color:black" >
<hr>

            <h3>予約変更</h3>
            <br>
             <div class="row">
                <div class="col-md-3">
                <label for="sel1">店舗 :</label>
                </div>

                <div class="col-md-9">
                <select class="form-control" name="bk_store">
                <option>東京(本店)</option>
                <option>東京(チェーン店)</option>
                <option>大阪</option>
                <option>京都</option>
                </select>
                </div>
             </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">デザイナー : </label>
                </div>

                <div class="col-md-9">
                <select class="form-control" name="bk_designer">
                <!-- 東京(本店) -->
                <option>Designer LEE 東京(本店)</option>
                <option>Designer HA 東京(本店)</option>
                <option>Designer SIM 東京(本店)</option>
                <option>Designer SHIN 東京(本店)</option>
                <!-- 東京(チェーン店) -->
                <option>Designer HA 東京(チェーン店)</option>
                <option>Designer YOON 東京(チェーン店)</option>
                <option>Designer LEE 東京(チェーン店)</option>
                <option>Designer KIM 東京(チェーン店)</option>
                <!-- 大阪 -->
                <option>Designer NANASHI 大阪</option>
                <option>Designer DOKOUDA 大阪</option>
                <option>Designer NANDA 大阪</option>
                <option>Designer KOREWA 大阪</option>
                <!-- 京都 -->
                <option>Designer NANAYA 京都</option>
                <option>Designer SAKATA 京都</option>
                <option>Designer NINNIKU 京都</option>
                <option>Designer NINJIN 京都</option>
                </select>
                </div>
             </div><br>

            <div class="row">
              <div class="col-md-3">
                <label for="sel1">性別 :</label>
              </div>

              <div class="col-md-9">
                <select class="form-control" name="bk_gender">
                 <option>男性</option>
                 <option>女性</option>
                </select>
              </div>
            </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">スタイル選択 :</label>
                </div>

                <div class="col-md-9">
                <select class="form-control" name="bk_hairStyle">
                  <option>カット - 1,500円</option>
                  <option>パーマ - 4,000円</option>
                  <option>カラー - 3,500円</option>
                </select>
                </div>
             </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">予約日にち:</label>
                </div>

                <div class="col-md-9">
                <input type="date" name ="bk_date">
                </div>
             </div><br>

            <div class="row">
                <div class="col-md-3">
                <label for="sel1">予約時間:</label>
                </div>

                <div class="col-md-9">
                <select class="form-control" name="bk_time">
                <option>10:00</option>
                <option>11:00</option>
                <option>12:00</option>
                <option>13:00</option>
                <option>14:00</option>
                <option>15:00</option>
                <option>16:00</option>
                <option>17:00</option>
                <option>18:00</option>
                <option>19:00</option>
                <option>20:00</option>
                <option>21:00</option>
                <option>22:00</option>
                </select>
                </div>
             </div><br>

          </div>
          <br>

          <div class="form-group">
            <div class="col-sm-12 text-center">
              <button class="btn btn-primary" type="submit">決定</button>
              <button class="btn btn-success" type="reset">リセット</button>
              <button class="btn btn-danger" type="reset">予約キャンセル</button>
            </div>
          </div>



          </div>
        </form>
      </div>


      <!-- 1 : 1 문의 -->
      <div id="qna" style = "background-color:black">
      <br>
      </div>
       <div class="container-fluid text-center" style = "background-color:black" >
          <h2>問い合わせ <small>確認次第お返しいたします。</small></h2>
          <hr>
          <h3 align = "center">Q&A</h2>
          <br>
        <form action="qna.do" method="post" encType="multiplart/form-data">
           <table class="table table-bordered">
            <tbody>
      
            <tr>
                <th>タイトル: </th>
                <td><input type="text" placeholder="タイトルを入力してください" name="subject" class="form-control"/></td>
            </tr>
            <tr>
                <th>内容: </th>
                <td height ="300"><textarea cols="10" rows="30" placeholder="内容を入力してください" name="content" class="form-control"></textarea></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="決定" onclick="sendData()" class="pull-right"/>
                    <input type="reset" value="リセット" class="pull-left"/>
                </td>
            </tr>
        </form>
    </tbody>
</table>
<br>
</div>
</div>



<!-- 마일리지 페이지 -->

    <!-- grid form -->
      <div id="point" style = "background-color:black">
      <br>
      </div>
     <div class="container-fluid text-center" style = "background-color:black" >
      <form action="mileage.do" method="post">
          <h2>ポイント <small>ポイント履歴</small></h2>
          <hr id = "p">        
        <div class="col-md-4">
          <div class='row'>
            <div class="col-md-12 text-center">
              <p>ポイント残高</p>
              <hr>
              <div class="panel panel-default">
                <img src="hairIMAGE/img2.jpg" class="img-responsive" style='display: width :450px; height=150px;'>
                <img src="hairIMAGE/img1.png" class="img-circle" style='display: block; width: 120px; margin: -60px auto 0;'>
                <div class="panel-body">
                  <p class='lead text-center'>ポイント : 2000점</p>
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
        </div>

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
                          <thead>
                            <tr>
                              <td>積立日</td>
                              <td>内容</td>
                              <td>積立ポイント</td>
                            </tr>
                          </thead>
                          <tbody>
                            <tr class="success">
                              <td>2017-07-30</td>
                              <td>후기 작성</td>
                              <td>1000</td>
                            </tr>
                            <tr class="success">
                              <td>2017-07-30</td>
                              <td>후기 작성</td>
                              <td>1000</td>
                            </tr>
                            <tr class="success">
                              <td>2017-07-30</td>
                              <td>후기 작성</td>
                              <td>1000</td>
                            </tr>
                            <tr class="success">
                              <td>2017-07-30</td>
                              <td>후기 작성</td>
                              <td>1000</td>
                            </tr>
                            <tr class="success">
                              <td>2017-07-30</td>
                              <td>후기 작성</td>
                              <td>1000</td>
                            </tr>
                          </tbody>
                        </table>
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
                              <thead>
                                 <tr>
                              <td>사용일</td>
                                    <td>내용</td>
                                    <td>사용금액</td>
                                 </tr>
                              </thead>
                              <tbody>
                                 <tr class="danger">
                                    <td>2017-07-30</td>
                                    <td>컷트</td>
                                    <td>1000</td>
                                 </tr>
                                 <tr class="danger">
                              <td>2017-07-30</td>
                                    <td>파마</td>
                                    <td>1000</td>
                                 </tr>
                                 <tr class="danger">
                              <td>2017-07-30</td>
                                    <td>컷트</td>
                                    <td>1000</td>
                                 </tr>
                                 <tr class="danger">
                              <td>2017-07-30</td>
                                    <td>염색</td>
                                    <td>1000</td>
                                 </tr>
                                 <tr class="danger">
                              <td>2017-07-30</td>
                                    <td>컷트</td>
                                    <td>1000</td>
                                 </tr>
                              </tbody>
                           </table>
                        </div>
                     </div>
                  </div>

              </div>

            </div>
          </div>
        </div>

      </form>
    </div>

  </div>
    </div>
</body>
</html>