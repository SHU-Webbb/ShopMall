package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.ProductComment;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {
	
	@Query("SELECT c FROM ProductComment c " +
	       "WHERE c.product.pseq = ?1")
	List<ProductComment> findCommentByPseq(int pseq);
	
	@Query("SELECT COUNT(c) FROM ProductComment c " +
           "WHERE c.product.pseq =?1")
	int getTotalCommentCount(int pseq);
 }
