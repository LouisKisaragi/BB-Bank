<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jsl.dto.BoardDTO" %>
<%@ page import = "jsl.service.BoardService" %>
<%
BoardDTO dto = new BoardDTO();
BoardService service = new BoardService();
int num = Integer.parseInt(request.getParameter("contentno"));

dto = service.getOneNote(num);
%>
<style>
h2,th{
color : white;
}
</style>
<body style = "background-color : black;">
<div class="col-md-10 col-md-offset-1">
<div class="container-fluid" style = "background-color:black;">
 <h2 align = "center">口コミ</h2>
 <br>
<form action="modify_board" method="post" encType="multipart/form-data">
	<table class="table table-bordered">
	    <tbody>
            <tr>
                <th>タイトル: </th>
                <td><input type="text" name="title" class="form-control" value="<%=dto.getTitle() %>"/></td>
            </tr>
            <tr>
                <th>内容: </th>
                <td height ="300"><textarea cols="10" rows="30" name="contents" class="form-control">
                <%=dto.getContents() %>
                </textarea></td>
            </tr>
            <tr>
                <th>添付ファイル: </th>
                <td><input type="file" name="uploadFile" class="form-control"/>
                	<input type="hidden" name = "image" value="<%=dto.getImage() %>" />
                	<input type="hidden" name = "visiable" value="<%=dto.getVisiable() %>" />
                	<input type="hidden" name = "num" value="<%=dto.getNum()%>" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="修正完了" class="pull-right"/>
                    <input type="reset" value="リーセット" class="pull-left"/>
                    <input type="button" value="リストへ" class="pull-right" onclick="location.href='./FrontController?src=board'"/>
                </td>
            </tr>
	    </tbody>
	</table>
</form>
</div>
</body>
</html>