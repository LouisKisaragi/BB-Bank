<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
h2,th{
color : white;
}
</style>
</head>
<body style = "background-color:black;">
<div class="col-md-10 col-md-offset-1">
<div class="container-fluid" style = "background-color:black;">
 <h2 align = "center">작성 폼</h2>
 <br>
<form action="write.do" method="post" encType="multipart/form-data">
<table class="table table-bordered">
    <tbody>
            <tr>
                <th>제목: </th>
                <td><input type="text" placeholder="제목을 입력해 주십시오" name="title" class="form-control"/></td>
            </tr>
            <tr>
                <th>내용: </th>
                <td height ="300"><textarea cols="10" rows="30" placeholder="내용을 입력해 주세요" name="contents" class="form-control"></textarea></td>
            </tr>
            <tr>
                <th>첨부파일: </th>
                <td><input type="file" name="uploadFile" class="form-control"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="작성완료" class="pull-right"/>
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