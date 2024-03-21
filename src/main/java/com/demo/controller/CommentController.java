package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Member;
import com.demo.domain.Product;
import com.demo.domain.ProductComment;
import com.demo.service.CommentService;

import jakarta.servlet.http.HttpSession;



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
	
	@PostMapping(value="/save")
	public String saveCommentAction(ProductComment vo,
				@RequestParam(value="pseq") int pseq, HttpSession session) {
      
		Member loginUser = (Member) session.getAttribute("loginUser");	
		
		if(loginUser == null) {
    	  return "member/login"; // 로그인 화면으로 이동
      }else {
    	  vo.setMember(loginUser);
    	  Product p = new Product();
    	  p.setPseq(pseq);
    	  vo.setProduct(p);
    	  
    	 commentService.saveComment(vo);
    	 
    	 return "success";
      }
	}
}
