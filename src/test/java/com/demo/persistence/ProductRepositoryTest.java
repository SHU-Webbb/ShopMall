package com.demo.persistence;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Product;

@SpringBootTest	
public class ProductRepositoryTest {
	@Autowired
	ProductRepository productRepo;
	
	@Disabled //테스트 제외
	@Test
	public void productInsert() {
		
		Product[] products = {
				
				new Product("크로커다일부츠", "2", 40000, 50000, 10000, "오리지날 크로커다일부츠 입니다.", "w2.jpg", "n", "y", new Date()),
				new Product("롱부츠", "2", 40000, 50000, 10000, "따뜻한 롱부츠 입니다.", "w-28.jpg", "n", "y", new Date()),
				new Product("힐", "1", 10000, 12000, 2000, "여성 전용 힐", "w-26.jpg", "n", "y", new Date()),
				new Product("슬리퍼", "4", 5000, 5500, 500, "편안한 슬리퍼 입니다.", "w-25.jpg", "y", "y", new Date()),
				new Product("회색힐", "1", 10000, 12000, 2000, "여성 전용 힐", "w9.jpg", "n", "y", new Date()),
				new Product("여성 부츠", "2", 12000, 18000, 6000, "여성용 부츠", "w4.jpg", "n", "y", new Date()),
				new Product("핑크샌달", "3", 5000, 5500, 500, "사계절용 샌달 입니다.", "w-10.jpg", "y", "y", new Date()),
				new Product("슬리퍼", "3", 5000, 5500, 500, "편안한 슬리퍼 입니다.", "w11.jpg", "y", "y", new Date()),
				new Product("스니커즈", "4", 15000, 20000, 5000, "활동성이 좋은 스니커즈입니다.", "w1.jpg", "n", "y", new Date()),
				new Product("샌달", "3", 5000, 5500, 500, "사계절용 샌달 입니다.", "w-09.jpg", "n", "y", new Date()),
				new Product("스니커즈", "5", 15000, 20000, 5000, "활동성이 좋은 스니커즈입니다.", "w-05.jpg", "n", "y", new Date()),
			};
		
		for (int i = 0; i <products.length; i++) {
			productRepo.save(products[i]);
		}
	}
	
	/*
	 * 상품 Id를 조건으로 조회
	 */
	@Disabled //테스트 제외
	@Test
	public void testGetProduct() {
		Optional<Product> product = productRepo.findById(1);  //pseq가 1인 제품을 조회
		
		if(product.isPresent()) {
			System.out.println(product.get().toString());
		} else {
			System.out.println("제품이 존재하지 않습니다.");
		}
	}
	/*
	 * 상품 정보 수정
	 */
	@Disabled //테스트 제외
	@Test
	public void testUpdateProduct() {
		
		Product product = productRepo.findById(1).get();
		
		product.setContent("따듯한 크로커다일 부츠입니다.");
		
        productRepo.save(product);
	}
	@Disabled //테스트 제외
	@Test
	public void testNewProductList() {
		List<Product> newProductList = productRepo.getNewProduct();
        
        for (Product p : newProductList) {
        	System.out.println(p.toString());
        }
	}
	
	@Disabled //테스트 제외
	@Test
	public void testBestProductList() {
		List<Product> bestProductList = productRepo.getBestProduct();
        
        for (Product p : bestProductList) {
        	System.out.println(p.toString());
        }
	}
	@Disabled
	@Test
	public void testProductListBtkind() {
		//1:힐 , 2:부츠, 3:샌달, 4:슬리퍼, 5:스니커즈, 6:세일상품
		List<Product> productList = productRepo.findProductByKindContaining("2");
        
        for (Product p : productList) {
            System.out.println(p);
        }
	}
}
