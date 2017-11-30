<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

.copyrights{
margin-top:25px;
}

footer.container-fluid.text-center.foot {
    background: #383838;
}

h3{
color : white;
}

hr{
border-top: 1px dotted #525252;
}

.white-txt{
color: #FFF;
}

em{
color : white;}

}
</style>
</head>
<body>

<!-- footer -->
 
 <div class="container-fluid text-center foot" style = "background-color:black">
 <h3 class="text-center">CONTACT</h3>
  <hr>
  <div class="text-center">
  <a class="up-arrow" href="#myPage" data-toggle="tooltip" title = "TOP">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a>
  <div class="copyright">
    <div class="text-center"> 
    <BR>
    <span><img src="<%=request.getContextPath()%>/hairIMAGE/logo1.png" width="25%" height="25%" alt="logo1"/></span><br><br>
    <em>〒194-0013 Tokyo, Machida, Haramachida, 6 Chome−20−19, STビル<br>
        T.+81 42-123-3456<br/>
      Copyright by 2017 Team JYP All Rights Reserved.</em>
      <br><br>
    </div>
   </div>
</div>

<script>
$(document).ready(function(){
    // Initialize Tooltip
    $('[data-toggle="tooltip"]').tooltip(); 
})
</script>
  
</body>
</html>