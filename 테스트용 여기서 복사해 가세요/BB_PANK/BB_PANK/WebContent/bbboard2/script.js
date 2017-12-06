function openView(nick){
	url="../bbmember/viewMemberInformation.do?viewNick="+nick;
	window.open(url,"_blank","width=300,height=300;");
}
function writeSave(){
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
function listSearchSave(){
	if(document.listSearch.search.value==""){
		alert("검색어를 입력하세요.");
		document.listSearch.search.focus();
		return false;
	}
}