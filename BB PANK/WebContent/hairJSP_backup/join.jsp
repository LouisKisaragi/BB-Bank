<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    <script type="text/javascript" src="hairJS/hairjs.js"></script>
  
  <style>
  hr{
  border-top: 1px dotted #525252;
  }
  h2,p,label{
  color:white;
  }
  </style>
  
  </head>
  <body>

    <div class="container-fluid text-center" style = "background-color:black" >
    
    <form action="<%=request.getContextPath()%>/join.do" method="post" name="frm">
      
        <h2>会員登録 <small>JSL SALON会員登録</small></h2>
      <hr>
      <div class="col-md-6 col-md-offset-3">

        <div class="form-group">
          <label for="reg">ID:</label>
            <div class="input-group">
              <input type="text" class="form-control" name="id" placeholder="IDを入力してください">
                <span class="input-group-btn">
                 <input type="button" value="IDチェック" class="btn btn-success" onclick="idcheck()">
                </span>
            </div>
        </div>

        <div class="form-group">
          <label for="reg">パスワード:</label>
          <input type="text" class="form-control" name="pass" placeholder="パスワードを入力してください">
        </div>

        <div class="form-group">
          <label for="reg">パスワード確認 :</label>
          <input type="text" class="form-control" name="pass_ch" placeholder="パスワードを再入力してください">
          <p style="color:red"> ${pass_message }</p>
        </div>

        <div class="form-group">
          <label for="reg">名前 :</label>
          <input type="text" class="form-control" name="name" placeholder="名前を入力してください">
        </div>

        <div class="form-group">
          <label for="reg">住所 :</label>
          <input type="text" class="form-control" name="address" placeholder="住所を入力してください">
        </div>

        <div class="form-group">
          <label for="reg">連絡先 :</label>
          <input type="text" class="form-control" name="phone" placeholder="-なしで入力してください">
        </div>

        <div class="form-group">
          <div class="col-sm-12 text-center">
            <button class="btn btn-primary" type="submit">決定</button>
            <button class="btn btn-danger" type="reset">リセット</button>
          </div>
        </div>
<br><br><br>
      </div>
 </form>
    </div>
  </body>
</html>