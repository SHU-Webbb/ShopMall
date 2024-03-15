package com.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.demo.domain.Address;
import com.demo.domain.Member;
import com.demo.service.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//약정화면 표시
	@GetMapping("/contract")
	public String contractView() {
		return "member/contract";
	}
	//회원가입 화면 표시
	
	@PostMapping("/join_form")
	public String joinView() {
        return "member/join";
    }
	
	//로그인 화면 표시
	@GetMapping("/login_form")
    public String loginView() {
        
        return "member/login";
    }
	
	//사용자 인증(로그인) 
	@PostMapping("/login")
	public String loginAction(Member vo, Model model) {
		String url = "";
		
		if(memberService.loginID(vo)==1) { //정상 사용자
			model.addAttribute("loginUser", memberService.getMember(vo.getId()));
			
			url = "redirect:main";
		}else {
			url = "member/login_fail";
		}
		return url;
	}
	
	//로그아웃 처리
	@GetMapping("/logout")
    public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "member/login";
	}
	
	//id 중복 확인 처리
	@GetMapping("/id_check_form")
    public String idCheckView(Member vo, Model model) {
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message",result);
		model.addAttribute("id",vo.getId());
		
		return "member/idcheck";
	}
	
	//회원가입 처리
	@PostMapping("/join")
    public String joinAction(@RequestParam(value="addr1", defaultValue="") String addr1,
    						 @RequestParam(value="addr2", defaultValue="") String addr2,
    						 	Member vo) {
		vo.setAddress(addr1+ " " + addr2);
		memberService.insertMember(vo);
		
        return "member/login";
	}
	
	//주소찾기 화면
	@GetMapping("/find_zip_num")
	public String findZipNumView() {
        return "member/findZipNum";
    }
	
	//동이름으로 주소 찾기 처리
	@PostMapping("/find_zip_num")
    public String findZipNumAction(Address vo, Model model) {
		List<Address> addrList = memberService.getAddressByDong(vo.getDong());
		model.addAttribute("addressList", addrList);
		
		return "member/findZipNum";
	}
	
}
