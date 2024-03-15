package com.demo.service;

import java.util.List;

import com.demo.domain.Cart;

public interface CartService {
	
	void insertCart(Cart vo);
	
	List<Cart> getCartList(String id);
	
	void deleteCart(int cseq);
	
	void updateCart(int cseq);
}
