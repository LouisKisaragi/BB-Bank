﻿// 글쓰기 함수
function writeSave() {
	// document.* 하는 경로는 java나 jsp파일 이름이 아니라 Form 이름이다. writeForm의 writer 값......
	// 먼저 입력한 순서대로 반응한다. 여기서는 이름->제목->내용->패스워드 순으로 반응한다.
	// 글쓴이 값이 없을때
	if(document.writeForm.writer.value == "") {
		alert("글쓴이 이름을 입력하시오");
		document.writeForm.writer.focus();
		return false;
	}
	// 제목 값이 없을때
	if(document.writeForm.subject.value == "") {
		alert("제목을 입력하시오");
		document.writeForm.subject.focus();
		return false;
	}
	// 내용이 하나도 없을때
	if(document.writeForm.content.value == "") {
		alert("내용을 입력하시오");
		document.writeForm.content.focus();
		return false;
	}
	// 패스워드 값이 없을때
	if(document.writeForm.pass.value == "") {
		alert("패스워드 설정은 필수입니다");
		document.writeForm.pass.focus();
		return false;
	}
}

function searchGuard() {
	// 검색 값이 없을때
	if(document.search.keywords.value == "") {
		alert("검색어를 입력하고 검색 버튼을 누르십시오");
		document.writeForm.keywords.focus();
		return false;
	}
}