<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- LOGIN MODAL -->
<div class="loginmodal" id="loginmodal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4><span class="glyphicon glyphicon-lock"></span> ログイン</h4>
      </div>
      <div class="modal-body">
      
        <form role="form" method="post" action = "./login.do">
          <div class="form-group">
            <label for="id"><span class="glyphicon glyphicon-user"></span> ID : </label>
            <input type="text" class="form-control" name="id" placeholder="IDを入力してください">
          </div>
          <div class="form-group">
            <label for="usrname"><span class="glyphicon glyphicon-user"></span> パスワード : </label>
            <input type="password" class="form-control" name="pass" placeholder="パスワードを入力してください">
          </div>
          <div>
          <span class="glyphicon glyphicon-ok"></span>
          <input type="submit" value="ログイン" class="btn btn-block"> 
          </div>
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
          <span class="glyphicon glyphicon-remove"></span> 戻る
        </button>
      </div>
    </div>
  </div>
</div>