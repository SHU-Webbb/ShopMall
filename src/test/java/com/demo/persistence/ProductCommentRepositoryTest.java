package com.demo.persistence;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Member;
import com.demo.domain.Product;
import com.demo.domain.ProductComment;

@SpringBootTest
public class ProductCommentRepositoryTest {
	
	@Autowired
	ProductCommentRepository productCommentRepo;
	@Disabled
	@Test
	public void testInsertComment() {

				Member member = new Member("one", "1111", "홍길동", "kimnari@email.com", "", "" ,"", "y", new Date());
				Product product = new Product(1,"크로커다일 부츠", "2", 40000, 50000, 10000, "오리지날 크로커다일", "", "y", "n", new Date());
				
				ProductComment productComment = ProductComment.builder()
				.product(product)
                .content("진짜 악어 가죽임...")
                .member(member)
                .build();
				
				productCommentRepo.save(productComment);
	}
}
