package com.demo.persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Cart;
import com.demo.domain.Member;
import com.demo.domain.Product;

@SpringBootTest
public class CartRepositoryTest {
	
	@Autowired
    private CartRepository cartRepo;
	
	@Disabled
	@Test
	public void testInsertCart() {
		Member member = new Member("one", "1111", "홍길동", "kimnari@email.com", "", "" ,"", "y", new Date());
		Product product = new Product("크로커다일 부츠", "2", 40000, 50000, 10000, "오리지날 크로커다일", "", "y", "n", new Date());
		
		Cart cart = Cart.builder()
				.member(member)
				.product(product)
				.quantity(1)
				.build();
				
		cartRepo.save(cart);		
			
	}
	@Disabled
	@Test
	public void testSelectCart() {
		Optional<Cart> item = cartRepo.findById(1);
	
		Cart cart = item.get();
		System.out.println(cart);
		System.out.println("장바구니 사용자명 : " + cart.getMember().getName());
		System.out.println("장바구니 제품명: " + cart.getProduct().getName());
	}
	@Disabled
	@Test
	public void testCartList() {
		List<Cart> cartList = cartRepo.getCartList("one");
		
		for (Cart cart : cartList) {
            System.out.println(cart);
        }
	}
}
