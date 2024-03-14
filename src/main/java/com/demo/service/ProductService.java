package com.demo.service;


import java.util.List;

import com.demo.domain.Product;


public interface ProductService {
	
	public List<Product> getNewProductList();
	
	public List<Product> getBestProductList();
	
	public Product getProduct(int pseq);
	
	public List<Product> getProductListByKind(String kind);
}
