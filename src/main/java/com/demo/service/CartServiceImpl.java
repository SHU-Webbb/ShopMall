package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Cart;
import com.demo.persistence.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public void insertCart(Cart vo) {
		cartRepo.save(vo);
	}

	/*
	 * 회원 id별 장바구니 목록 조회
	 */
	@Override
	public List<Cart> getCartList(String id) {
		return cartRepo.getCartList(id);
	}

	@Override
	public void deleteCart(int cseq) {
		cartRepo.deleteById(cseq);
	}
	/*
	 * 장바구니 처리결과 업데이트
	 * 
	 */
	@Override
	public void updateCart(int cseq) {
		Cart cart = cartRepo.findById(cseq).get();
		
		cart.setResult("2");
		
		cartRepo.save(cart);
	}

}
