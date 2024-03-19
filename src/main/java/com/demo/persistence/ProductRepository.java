package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	//신상품 조회
	@Query(value="SELECT * FROM new_pro_view", nativeQuery = true)
	List<Product> getNewProduct();
	//베스트상품 조회
	@Query(value="SELECT * FROM best_pro_view", nativeQuery = true)
	List<Product> getBestProduct();
	
	//상품 종류별 조회
	List<Product> findProductByKindContaining(String kind);
	
	//이름으로 전체 상품 조회
	List<Product> findProductByNameContainingOrderByName(String name); 
}

