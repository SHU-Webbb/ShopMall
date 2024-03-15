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