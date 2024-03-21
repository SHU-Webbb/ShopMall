package com.demo.service;

import java.util.List;

import com.demo.domain.ProductComment;

public interface CommentService {

	public int saveComment();
	
	public List<ProductComment> getCommentList(int pseq);
	
	public int getCountCommnetList(int pseq);
}
