<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

img:hover {
opacity:0.8;
filter:alpha(opacity=80);
}

img{
border:0;
border-radius : 20px;
}

.carousel{
width:auto;
}
h3,p{
color : white;
}
hr{
border-top: 1px dotted #525252;
}
#b{
color:black;
}
</style>
</head>
<body>
<div style = "background-color:black">
<!-- carousel -->
<div id="myCarousel" class="carousel" data-autoSlidemode="true" data-autoSlideDuration="3000" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
    <li data-target="#myCarousel" data-slide-to="3"></li>
    <li data-target="#myCarousel" data-slide-to="4"></li>
    <li data-target="#myCarousel" data-slide-to="5"></li>
  </ol>
  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active" align="center">
      <img src="hairIMAGE/bonjum.jpg" alt="main">
    </div>
    <div class="item"align="center">
      <img src="hairIMAGE/bonjum1-1.jpg" alt="chain1">
    </div>
    <div class="item"align="center">
      <img src="hairIMAGE/bunjum1.jpg" alt="chain1">
    </div>
    <div class="item"align="center">
      <img src="hairIMAGE/bunjum2.jpg" alt="chain2">
    </div>
    <div class="item"align="center">
      <img src="hairIMAGE/bunjum3.jpg" alt="chain3">
    </div>
    <div class="item"align="center">
      <img src="hairIMAGE/bunjum4.jpg" alt="chain3">
    </div>
  </div>
  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">前へ</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">次へ</span>
  </a>
</div>
<br><!-- 여백용입니다 지우면 큰일나요 -->

<!-- intro-->
<div style = "background-color:black">
<div id="intro" class="container-fluid text-center">
  
  <h3>ヘアサロン</h3>
 <hr class = "hr">
 <br>
    <a href = "./FrontController?src=introduce">
    <div class="col-sm-6">
      <img src="hairIMAGE/bonjum1-1.jpg" width = "80%" height = "50%" alt="Random Name">
      <p class="text-center"><strong>東京ヘアサロン(本店)</strong></p>
      <br>
    </div>
    </a>
    <a href = "./FrontController?src=introduce">
     <div class="col-sm-6">
      <img src="hairIMAGE/bunjum2-1.jpg" width = "80%" height = "50%" alt="Random Name">
      <p class="text-center"><strong>東京ヘアサロン(チェーン店)</strong></p>
      <br>
    </div>
    </a>
    <a href = "./FrontController?src=introduce">
     <div class="col-sm-6">
      <img src="hairIMAGE/bunjum3-1.jpg" width = "80%" height = "50%" alt="Random Name">
      <p class="text-center"><strong>大阪ヘアサロン</strong></p>
      <br>
     </div>
     </a>
     <a href = "./FrontController?src=introduce">
     <div class="col-sm-6">
      <img src="hairIMAGE/bunjum4-1.jpg" width = "80%" height = "50%" alt="Random Name">
      <p class="text-center"><strong>京都ヘアサロン</strong></p>
      <br>
     </div>
     </a>
  </div>
</div>
 
<!-- banner  -->
<div style = "background-color:black">
<div id="event" class="container-fluid text-center">
  <h3>BANNER</h3>
  <hr class = "hr">
 <br>

<div class="container-fluid text-center">

<a href="./FrontController?src=event">
<div class="col-md-4">
<div class="thumbnail">
<img src="hairIMAGE/image1.jpg" width = "50%" height = "50%" alt="image1"/>
<div class="caption">
<h3 id = "b">デザイナおすすめ割引イベント</h3>
<p id = "b">20%もお得！<br>この機会を見逃さないでください :)</p>
</div>
</div>
</div>
</a>

<a href="./FrontController?src=event">
<div class="col-md-4">
<div class="thumbnail"> 
<img src="hairIMAGE/image2.jpg" width = "50%" height = "50%" alt="image2"/>
<div class="caption">
<h3 id = "b">クーポンブックイベント</h3>
<p id = "b">JSL SALONのすべての割引券が集まっている！<br>ただし、発行日には利用できません。</p>
</div>
</div>
</div>
</a>

<a href="./FrontController?src=event">
<div class="col-md-4">
<div class="thumbnail"> 
<img src="hairIMAGE/image3.jpg" width = "50%" height = "50%" alt="image3"/>
<div class="caption">
<h3 id = "b">口コミイベント</h3>
<p id = "b">JSL SALON利用後レビューを書いてください！<br>月末に有鬚口コミイベントも!</p>
</div>
</div>
</div>
</a>

</div>

</div>
</div>

</body>
</html>