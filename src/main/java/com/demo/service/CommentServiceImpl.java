package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.ProductComment;
import com.demo.persistence.ProductCommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
    private ProductCommentRepository commentRepo;
	
	@Override
	public void saveComment(ProductComment comment) {
		commentRepo.save(comment);
	}

	@Override
	public List<ProductComment> getCommentList(int pseq) {
		
		return commentRepo.findCommentByPseq(pseq);
	}

	@Override
	public int getCountCommnetList(int pseq) {
		return 0;
	}

	@Override
	public int getCommentTotal(int pseq) {
		return commentRepo.getTotalCommentCount(pseq);
	}

}
