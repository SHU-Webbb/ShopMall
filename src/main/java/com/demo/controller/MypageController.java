package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.domain.Cart;
import com.demo.domain.Member;
import com.demo.domain.Product;
import com.demo.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {
	
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
	public String cartList() {
		
		return "mypage/cartList";
	}
}
