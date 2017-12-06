<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "bb.dto.BoardDTO" %>
<%@ page import = "bb.board.BoardService" %>
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
 <h2 align = "center">개발 이슈 개시판</h2>
 <br>
<form action="modify_board" method="post" encType="multipart/form-data">
	<table class="table table-bordered">
	    <tbody>
            <tr>
                <th>title: </th>
                <td><input type="text" name="title" class="form-control" value="<%=dto.getTitle() %>"/></td>
            </tr>
            <tr>
                <th>내용: </th>
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
                    <input type="submit" value="수정완료" class="pull-right"/>
                    <input type="reset" value="reset" class="pull-left"/>
                    <input type="button" value="목록으로" class="pull-right" onclick="location.href='./FrontController?src=board'"/>
                </td>
            </tr>
	    </tbody>
	</table>
</form>
</div>
</body>
</html>