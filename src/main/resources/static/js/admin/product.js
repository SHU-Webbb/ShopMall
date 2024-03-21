/**
 * 
 */

/*
 * 상품 이름으로 검색 요청
*/

 function go_search() {
	 $("#prod_form").attr("action","admin_product_list").submit();
 }
 
 /**
  * 전체 상품 보기 버튼 처리
  */
 function go_total() {
	 $("#key").val("");
	 $("#prod_form").attr("action","admin_product_list").submit();
 }
 
 /**
  * 상품등록 페이지 표시
  */
 function go_wrt() {
	 $("#prod_form").attr("action","admin_product_write_form").submit();
 }
 
 /**
  * 순익 필드(price3)입력
  */
 function go_ab() {
	 var price1 = $("#price1").val().replace(/,/g, ''); //repace() -> 콤마를 빈문자열로 대
	 var price2 = $("#price2").val().replace(/,/g, '');
	 var result = price2 - price1;
	 
	 $("#price3").val(result);
 }
 
 
 /**
  * 숫자 3자리 마다 콤마 찍기
  */
 
 function NumFormat(t) {
 
    num = t.value;
    // 숫자 이외의 문자 제거
    num = num.replace(/\D/g, '');
    // 숫자 3자리 마다 콤마 추가
    len = num.length - 3;
    while (len > 0) {
        num = num.substr(0, len) + "," + num.substr(len);
        len -= 3;
    }
   
    t.value = num;
   
    return t;
}

/*
** 상품 등록 요청
*/
function go_save() {
	
	if ($("#kind").val() == "") {
		alert("상품 종류를 입력하세요.");
		$("#kind").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("상품명을 입력하세요.");
		$("#name").focus();
		return false;
	} else if ($("#price1").val() == "") {
		alert("상품원가를 입력하세요.");
		$("#price1").focus();
		return false;
	} else if ($("#price2").val() == "") {
		alert("판매가를 입력하세요.");
		$("#price2").focus();
		return false;
	} else if ($("#price3").val() == "") {
		alert("상품순익를 입력하세요.");
		$("#price3").focus();
		return false;
	} else if ($("#content").val() == "") {
		alert("상품설명을 입력하세요.");
		$("#content").focus();
		return false;
	} else {
		var price1 = $("#price1");
		var price2 = $("#price2");
		var price3 = $("#price3");
		
		price1.val(remove_comma(price1));
		price2.val(remove_comma(price2));
		price3.val(remove_comma(price3));
		
		var theForm = $("#write_form");
		theForm.attr("enctype", "multipart/form-data");
		theForm.attr("action", "admin_product_write");
		theForm.submit();
    }
}

/**
 * 숫자 데이터에서 콤마 제거
 */
function remove_comma(data) {
	
	return data.val().replace(/,/g, "");
}


/**
 * 제품 상세보기
 */
function go_detail(pseq){
    $("#prod_form").attr("action","admin_product_detail?pseq=" + pseq);
    $("#prod_form").submit();
}

/**
 * 제품 수정
 */
function go_mod(pseq){
	$("#detail_form").attr("action","admin_product_update_form?pseq=" + pseq);;
    $("#detail_form").submit();
}

function go_mod_save(){
	
	if ($("#kind").val() == "") {
		alert("상품 종류를 입력하세요.");
		$("#kind").focus();
		return false;
	} else if ($("#name").val() == "") {
		alert("상품명을 입력하세요.");
		$("#name").focus();
		return false;
	} else if ($("#price1").val() == "") {
		alert("상품원가를 입력하세요.");
		$("#price1").focus();
		return false;
	} else if ($("#price2").val() == "") {
		alert("판매가를 입력하세요.");
		$("#price2").focus();
		return false;
	} else if ($("#price3").val() == "") {
		alert("상품순익를 입력하세요.");
		$("#price3").focus();
		return false;
	} else if ($("#content").val() == "") {
		alert("상품설명을 입력하세요.");
		$("#content").focus();
		return false;
	} else {
		var price1 = $("#price1");
		var price2 = $("#price2");
		var price3 = $("#price3");
		
		price1.val(remove_comma(price1));
		price2.val(remove_comma(price2));
		price3.val(remove_comma(price3));
		
		var theForm = $("#update_form");
		theForm.attr("enctype", "multipart/form-data");
		theForm.attr("action", "admin_product_update");
		theForm.submit();
    }
    
}

