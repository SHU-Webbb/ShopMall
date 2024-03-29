/**
 * 약관 동의 여부
 */

function go_next(){
	if($(".agree")[0].checked == true) {
		$("#join").attr("action", "join_form").submit();
	}else if($(".agree")[1].checked == true){
		alert("동의해라")
	}
}

/*
 * 회원 가입시, 필수 입력 항목 확인
 */
function go_save() {
	if ($("#id").val() == "") {
		alert("아이디를 입력해 주세요!");
		$("#id").focus();
		return false;
	} else if($("#id").val() != $("#reid").val()) {
		alert("아이디 중복 체크를 해주세요!");
		$("#id").focus();
		return false;
	} else if ($("#pwd").val() == "") {
		alert("비밀번호를 입력해 주세요!");
		$("#pwd").focus();
		return false;
	} else if($("#pwd").val() != $("#pwdCheck").val()) {
		alert("비밀번호가 일치하지 않습니다!");
		$("#pwd").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("이름을 입력해 주세요!");
		return false;
	} else if ($("#email").val() == "") {
		alert("email을 입력해 주세요!");
		return false;
	}else {
		// 회원 가입 요청
		$("#join").attr("action", "join").submit();
	}
}

/**
 * id 증복체크 화면 출력
 */

 function idcheck() {
	 if($("#id").val() == "") {
		 alert("아이디를 입력해 주세요!");
         $("#id").focus();
         return false;;
	 }
	 
	 // id가 입력되었으면 id중복확인 윈도우 오픈
	var url = "id_check_form?id="+$("#id").val();
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
			"resizable=yes, width=350, height=200");
	 
 }
 
 function post_zip() {
	var url = "find_zip_num";
	
	window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
	"resizable=yes, width=500, height=350");
}

/**
 * 아이디, 비밀번호 찾기 화면 요청
 * 
 */
function find_id_form() {
	var url = "find_id_form";
    
    window.open(url, "_blank_", "toolbar=no, menubar=no, scrollbars=no, " +
    "resizable=yes, width=550, height=450");
}

function findMemberId() {
	if ($("#name").val() == "") {
		alert("이름을 입력해 주세요.");
		$("#name").focus();
		return false;
	}else if ($("#email").val() == "") {
		alert("email을 입력해 주세요.");
        $("#email").focus();
        return false;
	}else {
		var form=$("#findId");
		form.action = "find_id"; //컨트롤러 요청 url
		form.submit(); //컨트롤러로 전송
	}
}

function findPassword() {
	if ($("#id2").val() == "") {
		alert("id을 입력해 주세요.");
        $("#2").focus();
        return false;
	}else if ($("#name2").val() == "") {
		alert("이름을 입력해 주세요.");
        $("#name2").focus();
        return false;
    }else if ($("#email2").val() == "") {
		alert("이메일을 입력해 주세요.");
        $("#email2").focus();
        return false;
     }else{
		 var form=$("#findPW");
		 form.action = "find_pwd"; 
         form.submit();
	 }
}

/*
** 비밀번호 변경
*/
function changePassword() {
	if ($("#pwd").val() == "") {
		alert("비밀번호를 입력해 주세요.");
		$("#pwd").focus();
		return false;
	} else if ($("#pwd").val() != $("#pwdcheck").val()) {
		$("#pwd").focus();
		alert("비밀번호가 맞지 않습니다. 다시 입력해 주세요");
	} else {
		$("#pwd_form").action = "change_pwd";
		$("#pwd_form").submit();
	}
}

