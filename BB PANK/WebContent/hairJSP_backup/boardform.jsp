<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
h2,th{
color : white;
}
</style>
</head>
<body>
<div class="container-fluid" style = "background-color:black;">
 <h2 align = "center">書き込む</h2>
 <br>
<form action="write.do" method="post" encType="multipart/form-data">
<table class="table table-bordered">
    <tbody>
            <tr>
                <th>タイトル: </th>
                <td><input type="text" placeholder="タイトルを入力してください" name="title" class="form-control"/></td>
            </tr>
            <tr>
                <th>内容: </th>
                <td height ="300"><textarea cols="10" rows="30" placeholder="内容を入力してください" name="contents" class="form-control"></textarea></td>
            </tr>
            <tr>
                <th>添付ファイル: </th>
                <td><input type="file" name="uploadFile" class="form-control"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="決定" class="pull-right"/>
                    <input type="reset" value="リーセット" class="pull-left"/>
                    <input type="button" value="リストへ" class="pull-right" onclick="location.href='./FrontController?src=board'"/>
                    <!-- <a class="btn btn-default" onclick="sendData()"> 등록 </a>
                    <a class="btn btn-default" type="reset"> reset </a>
                    <a class="btn btn-default" onclick="javascript:location.href='list.jsp'">글 목록으로</a> -->
                </td>
            </tr>
    </tbody>
</table>
</form>
</div>
</body>
</html>