package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.domain.Product;
import com.demo.service.ProductService;


@Controller
public class Homecontroller {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/main")
	public void mainView(Model model) {
		List<Product> newProductList = productService.getNewProductList();
		List<Product> bestProductList = productService.getBestProductList();
		
		model.addAttribute("newProductList", newProductList);
		model.addAttribute("bestProductList", bestProductList);
	}
}
