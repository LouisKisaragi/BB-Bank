function writeSave() {
	if(document.writeForm.writer.value == ""){
		alert("작성자를 입력하세요.");
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
	if(document.writeForm.preface.value == ""){
		alert("말머리를 선택하세요")
		document.writeForm.preface.focus();
		return false;
	}
}

function deleteSave() {
	if(document.delForm.pass.value == ""){
		alert("비밀번호를 입력하세요.");
		document.delForm.pass.focus();
		return false;
	}
}

function contentSave(){
	if(document.content.cwriter.value==""){
		alert("작성자를 입력하세요.");
		document.content.cwriter.focus();
		return false;
	}
	if(document.content.cpass.value==""){
		alert("비밀번호를 입력하세요.");
		document.content.cpass.focus();
		return false;
	}
	if(document.content.ccomment.value==""){
		alert("내용을 입력하세요.");
		document.content.ccomment.focus();
		return false;
	}
}
function openView(nick){
	url="../bbmember/viewMemberInformation.do?viewNick="+nick;
	window.open(url,"_blank","width=300,height=300, screenX=550, screenY=300;");
}

function logInSave(){
	if(document.logIn.id.value==""){
		alert("아이디를 입력 해주세요");
		document.logIn.id.focus();
		return false;
	}
	if(document.logIn.pass.value==""){
		alert("비밀번호를 입력해주세요.");
		document.logIn.pass.focus();
		return false;
	}
}

