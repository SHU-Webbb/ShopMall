package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.Cart;
import com.demo.domain.Member;
import com.demo.domain.OrderDetail;
import com.demo.domain.Orders;
import com.demo.domain.Product;
import com.demo.service.CartService;
import com.demo.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart_insert")
	public String insertCart(@RequestParam("pseq") int pseq,
							 Cart cartvo, HttpSession session) {
		String url = "";
		Member loginUser = (Member)session.getAttribute("loginUser");
		if (loginUser == null) { //로그인을 하지 않은경우
			url = "member/login"; //로그인 화면으로 이동
		}else {
			Product p = new Product();
			p.setPseq(pseq);
			
			Member m = new Member();
			m.setId(loginUser.getId());
			
			cartvo.setMember(m);
			cartvo.setProduct(p);
			cartService.insertCart(cartvo);
			
			url = "redirect:cart_list";
		}
        return url;
	}
	
	@GetMapping("/cart_list")
	public String cartList(HttpSession session, Model model){
		Member loginUser = (Member)session.getAttribute("loginUser");
		if (loginUser == null) { //로그인을 하지 않은경우
			return "member/login"; //로그인 화면으로 이동
		}else {
			List<Cart> cartList = cartService.getCartList(loginUser.getId());
			
			//장바구니 총액 계산
			int totalAmount = 0;
			for(Cart vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getProduct().getPrice2();
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
		}
		
		return "mypage/cartList";
	}
	
	@PostMapping("/cart_delete")
    public String deleteCart(@RequestParam(value="cseq") int[] cseq) {
		//장바구니 항목 삭제
		for(int i=0; i<cseq.length; i++) {
			cartService.deleteCart(cseq[i]);
		}
		return "redirect:cart_list"; 
	}
	/*
	 * 장바구니에서 주문한 내역 표시
	 */
	
	@PostMapping("/order_insert")
	public String orderInsert(HttpSession session, Orders order, RedirectAttributes model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		if (loginUser == null) { //로그인을 하지 않은경우
			return "member/login"; //로그인 화면으로 이동
		}else {
			//orders 객체에 사용자 정보 설정
			order.setMember(loginUser);
			
			int oseq = orderService.insertOrder(order);
			
			model.addAttribute("oseq", oseq);
			
			return "redirect:order_list";
		}
	}
	
	@GetMapping("/order_list")
	public String orderListAction(HttpSession session,@RequestParam(value="oseq") int oseq, Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		if (loginUser == null) { //로그인을 하지 않은경우
			return "member/login"; //로그인 화면으로 이동
		}else {
			List<OrderDetail> orderList = orderService.getListOrderById(loginUser.getId(), oseq);
			
			model.addAttribute("orderList", orderList);
		}
		return "mypage/orderList";
	}
}
