package com.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.domain.Member;
import com.demo.domain.Qna;
import com.demo.service.QnaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;

	@GetMapping("qna_list")
	public String getQnaList(HttpSession session, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동

		} else {
			List<Qna> qnaList = qnaService.getQnaList(loginUser.getId());

			model.addAttribute("qnaList", qnaList);
		}

		return "qna/qnaList";
	}

	@GetMapping("qna_view")
	public String getQnaView(Model model, Qna qna, HttpSession session) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동

		} else {

			Qna vo = qnaService.getQna(qna.getQseq());

			model.addAttribute("qnaVO", vo);

			return "qna/qnaView";
		}
	}

	@GetMapping("qna_write_form")
	public String qnaWriteForm(HttpSession session) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동

		} else {
			return "qna/qnaWrite";
		}
	}

	@PostMapping("qna_write")
	public String insertQna(Model model, Qna qna, HttpSession session, String content) {

		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동

		} else {

			qna.setMember(loginUser);
			qnaService.insertQna(qna);

			return "redirect:qna_list";
		}

	}
}
