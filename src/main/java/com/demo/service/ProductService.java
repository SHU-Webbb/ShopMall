package com.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.domain.Product;


public interface ProductService {
	
	public void insertProduct(Product vo);
	
	public void updateProduct(Product vo);
	
	public List<Product> getNewProductList();
	
	public List<Product> getBestProductList();
	
	public Product getProduct(int pseq);
	
	public List<Product> getProductListByKind(String kind);
	
	//이름을 조건으로 전체 상품 조회
	public List<Product> getAllProducts(String name);
	
	//페이지별 전체 상품 조회
	//page -- 조회할 페이지 번호
	//size -- 페이지당 게시글 수 
	public Page<Product> getAllProductsByName(String name, int page, int size);
	
	
	
}
