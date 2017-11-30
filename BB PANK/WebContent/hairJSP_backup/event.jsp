<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jsl.service.EventService" %>
<%@ page import = "java.util.*" %>
<%@ page import = "jsl.dto.EventDTO" %>
<%
ArrayList<EventDTO> list = null;
EventService service = new EventService();
list = service.loadEventpage();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

img:hover {
opacity:0.8;
filter:alpha(opacity=80);
}

img{
border-radius : 20px;
}
h3{
color : white;
}
p{
color : #BDBDBD;
}
hr{
border-top: 1px dotted #525252;
}
</style>
</head>
<body>


    <!-- Page Content -->
    <div class="container-fluid" style = "background-color:black" >

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 style = "color:white";>EVENT</h2>
                <h4 style = "color:#BDBDBD">イベント情報</h4>
            </div>
        </div>
        <hr>
        <!-- /.row -->
        
        <!-- Project Loading -->
<% if(list != null) {
     for(int i = 0; i<list.size(); i++) { %>
       <div class="container-fluid text-center">
            <div class="col-md-6">
                <a href="./FrontController?src=<%=list.get(i).getLinkvalue() %>">
                    <img src="hairIMAGE/<%=list.get(i).getImage() %>" width = "50%" height = "50%" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3><%=list.get(i).getTitle() %></h3>
                <%
                	if(list.get(i).getVisiable() == 1)
                	{
                		out.print("イベント中");
                	} else out.print("イベント終了");
                %>
                <br>
                <p>          
                	<%out.print(list.get(i).getContent()); %>
                </p>
            </div>
        </div>
        <!-- /.row -->

        <hr>
        
        <% }
        } else {%>
        
        
        <% } %>
        
<!-- 
        
        <-- Project One
       <div class="container-fluid text-center">
            <div class="col-md-6">
                <a href="./FrontController?src=style">
                    <img src="hairIMAGE/image1.jpg" width = "50%" height = "50%" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3>デザイナおすすめ割引イベント</h3>
                <br>
                <p>          
                                   　　　デザイナおすすめヘアースタイル(パーマの中１)をするお客様には20％の割引が！<br><br>                
                                    　　イベント開催店舗：JSL SALON本店、JSL SALON秋葉原店<br><br>
                                 　　   イベント参加方法：当店舗からご参加してください<br><br>  
                </p>
            </div>
        </div>
        <-- /.row

        <hr>

        <-- Project Two
         <div class="container-fluid text-center">
            <div class="col-md-6">
                <a href="./FrontController?src=booking">
                    <img src="hairIMAGE/image2.jpg" width = "50%" height = "50%" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3>クーポンイベント</h3>
                <br>
                <p>
               	ホームページから予約していらっしゃるお客様には <br>各種サービスを提供するクーポンブックを差し上げます。<br><br>                 
                                 　　イベント開催店舗：JSL SALON全支店<br><br>
                                 　　イベント参加方法：ホームページから予約ページをスクリーンショットで撮って<br>スタッフに見せてください。クーポンブックを差し上げます<br><br>  
                * クーポンブックは発行した当日には使えません。ご了承ください。 *<br>   
                </p>
            </div>
        </div>
        <-- /.row

        <hr>

        <-- Project Three
         <div class="container-fluid text-center">
            <div class="col-md-6">
                <a href="./FrontController?src=board">
                    <img src="hairIMAGE/image3.jpg" width = "50%" height = "50%" alt="">
                </a>
            </div>
            <div class="col-md-5">
                <h3>口コミイベント</h3>
                <br>
                <p>
                JSL SALONの利用後、口コミを書いてください！<br>口コミを書いてくださった方にはポイント1000点が！<br><br>              
                                   　 イベント開催店舗：JSL SALON全支店<br><br>
                                 　　イベント参加方法：JSL SALONを利用した後<br>会員登録後口コミにレジューを書いてください。<br><br> 
                * 月末に口コミイベントもございますのでどうかご参加してください。 *<br>   
                </p>
            </div>
        </div>
        <-- /.row

        <hr>
        <-- /.row
        <br>
         -->
</div>
</body>
</html>