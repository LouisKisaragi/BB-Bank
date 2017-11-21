function joinSave(){
	if(document.join.id.value == ""){
		alert("아이디를 입력하세요.");
		document.writeForm.writer.focus();
		return false;
	}
		
	if(document.writeForm.subject.value == ""){
		alert("제목을 입력하세요.");
		document.writeForm.subject.focus();
		return false;
	}
	if(document.writeForm.content.value == ""){
		alert("내용을 입력하세요.");
		document.writeForm.content.focus();
		return false;
	}
	if(document.writeForm.pass.value == ""){
		alert("비밀번호를 입력하세요.");
		document.writeForm.pass.focus();
		return false;
	}
}
function idCheck(id){
	if(id==""){
		alert("아이디를 입력해주세요.");
		document.join.id.focus();
	}else {
		
		var popWidth=300;
		var popHeight = 200;
		var winHeight = document.body.clientHeight;
		var winWidth = document.body.clientWidth;
		var winX =window.screenLeft;
		var winY= window.screenTop;
		var popX=winX+(winWidth - popWidth)/2;
		var popY=winY+(winHeight - popHeight)/2;
		
		url="idCheck.jsp?id="+id;
		window.ipen(url, "post", "left="+popX+",top="+popY+",width="+popWidth+",height="+popHeight);
	}
}