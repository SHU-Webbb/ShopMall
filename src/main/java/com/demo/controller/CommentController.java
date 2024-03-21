package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.ProductComment;
import com.demo.service.CommentService;



@RequestMapping("/comments")
@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping(value="/list")
	public Map<String, Object> commentList(@RequestParam(value="pseq") int pseq) {
		Map<String, Object> commentInfo = new HashMap<>();
		
		List<ProductComment> commentList = commentService.getCommentList(pseq);
		
		commentInfo.put("commentList", commentList);
		
		return commentInfo;
	}
}
