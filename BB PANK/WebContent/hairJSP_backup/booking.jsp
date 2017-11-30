<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
hr{
border-top: 1px dotted #525252;
}
h2,h3,p,label{
color:white;
}
</style>
</head>
<body>
<% if(session.getAttribute("login")== null) {%>
  <script type="text/javascript">
   alert("会員専用ページです。");
   history.go(-1);
  </script>
<%}%>
<div class = "container-fluid text-center" style = "background-color:black">
    <form action="booking.do" method = "post">
    <div class = "row text-center">
      <div class="col-lg-12 text-center" style = "background-color:black">
          <h2>予約ページ</h2>
          <hr>
          <p>JSLサロンへようこそ！</p>
            <div class="form-group">
              <label for="sel1">お店選択 </label>
              <select class="form-control" name="bk_store">
                <option>東京(本店)</option>
                <option>東京(チェーン店)</option>
                <option>大阪</option>
                <option>京都</option>
              </select>
              <br>

              <label for="sel1">デザイナー選択</label>
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
              <br>

              <label for="sel1">スタイル選択</label>
              <select class="form-control" name="bk_gender">
                <option>男性</option>
                <option>女性</option>
              </select>
              <select class="form-control" name="bk_hairStyle">
                <option>カット........1,500円</option>
                <option>パーマ.......4,000円</option>
                <option>カラー......3,500円</option>
              </select>
              <br>

              <label for="sel1">Select Date :</label><br>
              <input type="date" name ="bk_date">
              <br><br>

              <label for="sel1">時間選択</label>
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
              <br>
              <hr>
              <div class = "text-center">
                <button type="button" class="btn btn-primary" >予約</button>
                <button type="button" class="btn btn-danger" >リセット</button>
              </div>
            </div>
        </div>
        </div>
    </form>
    </div>
</body>
</html>