/**
 * 상품을 장바구니에 담기 위한 스크립트
 */

/**
 * 상품을 장바구니에 담기
 */
function go_cart(){
	if($("#quantity").val() == ""){
		alert("수량을 입력해 주세요.");
		$("#quantity").focus();
		return false;
	}else if ($("#quantity").val() > 100){
		alert("최대 100개까지 입니다.");
        $("#quantity").focus();
        return false;;
	}else {
		$("#theform").attr("action", "cart_insert").submit();
	}
}

/**
 * 장바구니 상품 삭제하기
 */
function go_cart_delete() {
	var count = 0; //체크된 항목의 수
	var len=$("[name='cseq']:checked").length;
	
	//삭제할 항목이 하나인 경우
	if (len == undefined){ //undefined - 값이 없는 경우
		if(document.formm.cseq.checked == true){
			count++;
		}
	}else {
		count = len;
	}
	
	if (len == 0) {
		alert("삭제할 항목을 선택해 주세요.");
	} else { // 삭제할 항목이 1개 이상일 경우 삭제 요청 전송
		$("#theform").attr("action", "cart_delete").submit();
	}
}

/**
 * 장바구니 내역을 주문 테이블에 저장 요청
 */
function go_order_insert() {
	$("#theform").attr("action", "order_insert").submit();
	
}