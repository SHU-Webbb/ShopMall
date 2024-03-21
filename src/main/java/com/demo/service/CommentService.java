package com.demo.service;

import java.util.List;

import com.demo.domain.ProductComment;

public interface CommentService {

	public void saveComment(ProductComment comment);
	
	public List<ProductComment> getCommentList(int pseq);
	
	public int getCountCommnetList(int pseq);
}
