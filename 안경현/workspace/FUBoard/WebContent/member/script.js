function idCheckSave(){
	if(document.idCheck.iid.value == ""){
		alert("아이디를 입력하세요.");
		document.idCheck.iid.focus();
		return false;
	}else{
		alert(document.idCheck.iid.value);
		alert(opener.document.join.id.value);
		opener.document.join.id.value=document.idCheck.iid.value;
		window.close();
	}		
}

function joinSave(){
	if(document.join.id.value == ""){
		alert("아이디를 입력하세요.");
		document.join.id.focus();
		return false;
	}
		
	if(document.join.pass.value == ""){
		alert("비밀번호를 입력하세요.");
		document.join.pass.focus();
		return false;
	}
	if(document.join.repass.value == ""){
		alert("비밀번호재확인을 입력하세요.");
		document.join.repass.focus();
		return false;
	}
	if(document.join.name.value == ""){
		alert("이름을 입력하세요.");
		document.join.name.focus();
		return false;
	}
	if(document.join.email.value == ""){
		alert("이메일을 입력하세요.");
		document.join.email.focus();
		return false;
	}
}
function idCheck(id,num,pageNum,bn){
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
		
		url="idCheck.do?id="+id+"&num="+num+"&pageNum="+pageNum+"&bn="+bn;
		window.open(url, "post", 
				"left="+popX+",top="+popY+",width="+popWidth+",height="+popHeight);
	}
}