function voteSave(){
	if(document.vote.commentTeam1.value == "" && document.vote.commentTeam2.value==""){
		alert("응원메시지를 입력하세요");
		document.vote.commentTeam1.focus();
		return false;
	}
	if(document.vote.mypoint.value<500){
		alert("보유한 포인트가 적습니다! 최소필요포인트:500");
		return false;
	}
}