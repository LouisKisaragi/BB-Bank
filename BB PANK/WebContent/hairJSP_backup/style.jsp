<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "jsl.dto.StyleDTO" %>
<%@ page import = "jsl.service.StyleService" %>

<%
ArrayList<StyleDTO> list = new ArrayList<StyleDTO>();
StyleService service = new StyleService();
list = service.getStyleList();
%>

<style>
img:hover {
opacity:0.8;
filter:alpha(opacity=80);
}

img{
border : 0;
border-radius : 20px;
}
hr{
border-top: 1px dotted #525252;
}
.gallery-title
{
    font-size: 36px;
    color: white;
    text-align: center;
    font-weight: 500;
}

.filter-button
{
    font-size: 18px;
    border: 1px solid #BDBDBD;
    border-radius: 5px;
    text-align: center;
    color: #BDBDBD;
    margin-bottom: 30px;

}
.filter-button:hover
{
    font-size: 18px;
    border: 1px solid #8C8C8C;
    border-radius: 5px;
    text-align: center;
    color: #ffffff;
    background-color: #8C8C8C;

}
.btn-default:active .filter-button:active
{
    background-color: #8C8C8C;
    color: black;
}

.port-image
{
    width: 100%;
}

.gallery_product
{
    margin-bottom: 30px;
}

</style>
<div class="container-fluid" style = "background-color:black">            
        <div class="row">
        <div class="gallery col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <h1 class="gallery-title">Hair Style</h1>
            <hr>
        </div>
        <div align="center">
            <button class="btn btn-default filter-button" data-filter="all">All</button>
            <button class="btn btn-default filter-button" data-filter="long">Long</button>
            <button class="btn btn-default filter-button" data-filter="medium">Medium</button>
            <button class="btn btn-default filter-button" data-filter="short">Short</button>
            <button class="btn btn-default filter-button" data-filter="color">Color</button>
            <button class="btn btn-default filter-button" data-filter="men">Men</button>
        </div>
        <br/>
        <div class = "container-fluid text-center">
			<% for(int i=0; i<list.size();i++) { %>
		        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter <%=list.get(i).getSort() %>">
		        	<div data-toggle="modal" data-target='#<%="style"+list.get(i).getNum()%>'>
		            <img src="hairIMAGE/hairIMG/<%=list.get(i).getImage() %>" class="img-responsive">
		        	</div>
		        </div>
		    <% } %>
		</div>
	</div>
		    
	<!-- Modal page -->
	<% for(int i = 0; i<list.size(); i++) { %>
		<div class="container">
			<div class="modal fade" id="<%="style"+list.get(i).getNum()%>" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./modify_style" enctype="multipart/form-data" method="post">
							<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <input type="text" name="title" value="<%=list.get(i).getTitle()%>" />
					        </div>
					        <div class="modal-body">
					          <textarea rows="3" cols="50" name="contents"><%=list.get(i).getContent() %></textarea><br />
					          スタイルーイメージ : <input type="file" name="uploadFile" />
					          スタイル分類 : <input type="text" name="sort" value="<%=list.get(i).getSort() %>" />
					          <input type="hidden" name="image" value="<%=list.get(i).getImage() %>" />
					          <input type="hidden" name="num" value="<%=list.get(i).getNum() %>" />
					          <input type="hidden" name="visiable" value="<%=list.get(i).getVisible() %>" />
					          <input type="hidden" name="url" value="./AdminFrontController?src=style" />
					        </div>
					        <div class="modal-footer">
					          <input type="submit" class="btn btn-primary" value="転送" />
					          <button type="button"
					          onclick="location.href='./delete_board?num=<%=list.get(i).getNum()%>&url=./AdminFrontController?src=style'"
					          class="btn btn-danger" >削除</button>

					          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
				        </form>
					</div>
				</div>
			</div>
		</div>
		<% } %>
	
		<!-- 등록 Modal -->
		<div class="container">
			<div class="modal fade" id="insertStyle" role="dialog">
				<div class="modal-dialog">
					<!-- Modal body -->
					<div class="modal-content">
						<form action="./insert_style" enctype="multipart/form-data" method="post">
							<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          Title : <input type="text" name="title"/>
					        </div>
					        <div class="modal-body">
					          <p>スタイルの紹介</p>
					          <textarea rows="3" cols="50" name="contents"></textarea><br />
					          スタイルーイメージ : <input type="file" name="uploadFile" />
					          スタイル分類 : <input type="text" name="sort" />
					          <input type="hidden" name="url" value="./AdminFrontController?src=style" />
					        </div>
					        <div class="modal-footer">
					          <input type="submit" class="btn btn-primary" value="転送" />
					          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        </div>
				        </form>
					</div>
				</div>
			</div>
		</div>
</div>