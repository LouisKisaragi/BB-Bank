function idCheckSave(){
	if(document.idCheck.id.value == ""){
		alert("아이디를 입력하세요.");
		document.idCheck.id.focus();
		return false;
	}
	if(document.idCheck.rid.value != ""){
		opener.document.join.id.value=document.idCheck.rid.value;
		opener.document.join.idcheck.value=1;
		self.opener=self;
		window.close();
	}	
}
function nickCheckSave(){
	if(document.nickCheck.nick.value == ""){
		alert("닉네임을 입력하세요.");
		document.nickCheck.nick.focus();
		return false;
	}
}
function nickCheckSt(){
	if(document.nickCheck.rnick.value != ""){
		opener.document.join.nickname.value=document.nickCheck.rnick.value;
		opener.document.join.nickcheck.value=1;
		self.opener=self;
		window.close();
	}	
}
function nickCheck(nickname){
	if(nickname==""){
		alert("닉네임을 입력해주세요.");
		document.join.nickname.focus();
	}else {
		
		var popWidth=300;
		var popHeight = 200;
		var winHeight = document.body.clientHeight;
		var winWidth = document.body.clientWidth;
		var winX =window.screenLeft;
		var winY= window.screenTop;
		var popX=winX+(winWidth - popWidth)/2;
		var popY=winY+(winHeight - popHeight)/2;
		
		url="nickCheck.do?nick="+nickname;
		window.open(url, "post", 
				"left="+popX+",top="+popY+",width="+popWidth+",height="+popHeight);
	}
}

function emailCC(){
	opener.document.join.emailcheck.value=1;
	self.opner=self;
	window.close();
}
function EmailCerCheck(){
	if(document.emailCheckSend.certification.value==""){
		alert("인증번호를 입력하세요.");
		return false;
	}
}
function joinSave(){
	if(document.join.nickcheck.value=="0"){
		alert("닉네임중복 체크를 해주세요");
		document.join.nickname.focus();
		return false;
	}
	if(document.join.emailcheck.value=="0"){
		alert("이메일 인증을 해주세요.");
		document.join.email.focus();
		return false;
	}
	if(document.join.id.value == ""){
		alert("아이디를 입력하세요.");
		document.join.id.focus();
		return false;
	}
	if(document.join.nickname.value == ""){
		alert("닉네임을 입력하세요.");
		document.join.nickname.focus();
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
	if(document.join.idcheck.value=="0"){
		alert("아이디 중복체크를 해주세요.")
		document.join.idcheck.focus();
		return false;
	}
	if(document.join.passcheck.value=="0"){
		alert("비밀번호 확인을 해주세요.")
		document.join.pass.focus();
		return false;
	}
	if(document.join.pass.value!=document.join.pass.value){
		alert("입력한 비밀번호와 비밀번호확인이 다릅니다.")
		document.join.pass.focus();
		return false;
	}
}
function passCheck(pass,repass){
	if(pass==repass){
		document.join.passcheck.value=1;
		alert("확인되었습니다.");
	}else{
		alert("비밀번호가 일치하지 않습니다.");
		document.join.passcheck.value=0;
		return false;
	}
}
function emailCheck(email){
	if(email==""){
		alert("이메일을 입력하세요.");
		document.join.email.focus();
	}else{
		var popWidth=300;
		var popHeight = 200;
		var winHeight = document.body.clientHeight;
		var winWidth = document.body.clientWidth;
		var winX =window.screenLeft;
		var winY= window.screenTop;
		var popX=winX+(winWidth - popWidth)/2;
		var popY=winY+(winHeight - popHeight)/2;
		
		url="emailCheck.do?email="+email;
		window.open(url, "post", 
				"left="+popX+",top="+popY+",width="+popWidth+",height="+popHeight);
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
		
		url="idCheck.do?id="+id;
		window.open(url, "post", 
				"left="+popX+",top="+popY+",width="+popWidth+",height="+popHeight);
	}
}
function login(){
	if(document.login.id.value=""){
		alert("아이디를 입력해주세요.");
		document.join.id.focus();
		return false;
	}
	if(document.login.pass.value=""){
		alert("비밀번호를 입력해주세요.");
		document.join.id.focus();
		return false;
	}
}