package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.domain.Admin;
import com.demo.service.AdminService;

@SessionAttributes("adminUser")
@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/admin_login_form")
	public String adminLoginView() {
		return "admin/main";
	}

	// 관리자 로그인 처리 구현
	@PostMapping("/admin_login")
	public String adminLogin(Admin vo, Model model) {
		
		//(1) 관리자 로그인 확인
		//(2) 정상 관리자이면
		// -관리자 정보 조회
		// - 상품 목록으로 이동 (redirect:admin_product_list)
		//(3) 비정상 관리자이면
		//  - 메시지르 설정하고 로그인 페이지로 이동
		//  - 결과 : 0 => 비밀번호를 확인해 주세요
		//  -     -1 => 아이디를 확인해 주세요
		if(adminService.adminCheck(vo) == 1) {
			Admin admin = adminService.getAdmin(vo.getId());
			model.addAttribute("adminUser",admin);
			return "redirect:admin_product_list";
		}else {
			if(adminService.adminCheck(vo) == 0) {
			 model.addAttribute("message", "비밀번호를 확인해 주세요.");
		    }else {
			 model.addAttribute("message", "아이디를 확인해 주세요.");
		     }
		  return "admin/main";
		}

      }
	
	@GetMapping("/admin_product_list")
	public String adminProductList() {
		
		return "admin/productList";
	}
}