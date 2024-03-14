package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.domain.Product;
import com.demo.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/product_detail")
    public String productDetailView(Product vo, Model model){
		//상품 상세 정보 조회
		Product product = productService.getProduct(vo.getPseq());
		
        model.addAttribute("productVO", product);
        
        return "product/productDetail";
	}
	
	@GetMapping("/category")
	public String productKindAction(Product vo, Model model) {
		List<Product> kindList = productService.getProductListByKind(vo.getKind());
        
        model.addAttribute("productKindList", kindList);
        
        return "product/productKind";
	}
}
