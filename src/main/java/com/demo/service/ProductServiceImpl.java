package com.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.demo.domain.Product;
import com.demo.persistence.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public List<Product> getNewProductList() {
		return productRepo.getNewProduct();
	}

	@Override
	public List<Product> getBestProductList() {
		return productRepo.getBestProduct();
	}

	@Override
	public Product getProduct(int pseq) {
		return productRepo.findById(pseq).get();
	}

	@Override
	public List<Product> getProductListByKind(String kind) {
		return productRepo.findProductByKindContaining(kind);
	}

	/*
	 * 전체 상품목록 조회
	 */
	@Override
	public List<Product> getAllProducts(String name) {
		return productRepo.findProductByNameContainingOrderByName(name);
	}
	
	/*
	 * page번호는 0부터 시작
	 */
	@Override
	public Page<Product> getAllProductsByName(String name, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1 , size , Direction.ASC, "name");
		
		return productRepo.findAllproductsByNameContaining(name, pageable);
	}

	@Override
	public void insertProduct(Product vo) {
		productRepo.save(vo);
	}

	@Override
	public void updateProduct(Product vo) {
		Product p = productRepo.findById(vo.getPseq()).get();
		
		vo.setRegdate(p.getRegdate()); //등록일은 기존의 데이터 사용
		productRepo.save(vo);
		
	}




	
}
