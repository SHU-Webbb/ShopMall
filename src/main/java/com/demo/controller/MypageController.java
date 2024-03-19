package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.Cart;
import com.demo.domain.Member;
import com.demo.domain.OrderDetail;
import com.demo.domain.Orders;
import com.demo.domain.Product;
import com.demo.dto.OrderVO;
import com.demo.service.CartService;
import com.demo.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;

	@PostMapping("/cart_insert")
	public String insertCart(@RequestParam("pseq") int pseq, Cart cartvo, HttpSession session) {
		String url = "";
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			url = "member/login"; // 로그인 화면으로 이동
		} else {
			Product p = new Product();
			p.setPseq(pseq);

			Member m = new Member();
			m.setId(loginUser.getId());

			cartvo.setMember(m);
			cartvo.setProduct(p);
			cartService.insertCart(cartvo);

			url = "redirect:cart_list";
		}
		return url;
	}

	@GetMapping("/cart_list")
	public String cartList(HttpSession session, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동
		} else {
			List<Cart> cartList = cartService.getCartList(loginUser.getId());

			// 장바구니 총액 계산
			int totalAmount = 0;
			for (Cart vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getProduct().getPrice2();
			}

			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
		}

		return "mypage/cartList";
	}

	@PostMapping("/cart_delete")
	public String deleteCart(@RequestParam(value = "cseq") int[] cseq) {
		// 장바구니 항목 삭제
		for (int i = 0; i < cseq.length; i++) {
			cartService.deleteCart(cseq[i]);
		}
		return "redirect:cart_list";
	}
	/*
	 * 장바구니에서 주문한 내역 표시
	 */

	@PostMapping("/order_insert")
	public String orderInsert(HttpSession session, Orders order, RedirectAttributes model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동
		} else {
			// orders 객체에 사용자 정보 설정
			order.setMember(loginUser);

			int oseq = orderService.insertOrder(order);

			model.addAttribute("oseq", oseq);

			return "redirect:order_list";
		}
	}

	@GetMapping("/order_list")
	public String orderListAction(HttpSession session, @RequestParam(value = "oseq") int oseq, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동
		} else {
			// 주문내역 조회
			Orders order = orderService.getListOrderById(loginUser.getId(), oseq);

			// 주문상세내역조회
			// List<OrderDetail> orderList =
			// orderService.getListOrderDetailById(loginUser.getId(), oseq);

			// 주문 총액 계산
			int totalAmount = 0;
			for (OrderDetail vo : order.getOrderDetailList()) {
				totalAmount += vo.getQuantity() * vo.getProduct().getPrice2();
			}

			model.addAttribute("orderList", order.getOrderDetailList());
			model.addAttribute("orderDate", order.getIndate());
			model.addAttribute("totalPrice", totalAmount);
		}
		return "mypage/orderList";
	}

	// 진행중인 사용자 주문내역 요약 조회
	@GetMapping("/mypage")
	public String myPageView(HttpSession session, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동
		} else {
			// (1) 사용자의 진행중인 주문번호 목록 조회
			List<Integer> oseqList = orderService.getSeqOrdering(loginUser.getId(), "1");

			// (2) 각 주문번호에 대해 주문조회 및 요약정보 생성
			List<OrderVO> summaryList = new ArrayList<OrderVO>();
			for (int oseq : oseqList) {
				OrderVO summary = new OrderVO();

				// 주문번호별 주문 조회
				Orders order = orderService.getListOrderById(loginUser.getId(), oseq);

				// 각 주문의 요약정보 생성
				summary.setOseq(order.getOseq());
				summary.setIndate(order.getIndate());
				// 한 주문번호의 상품 갯수
				int detailSize = order.getOrderDetailList().size();
				// 상세 주문 목록에서 첫번째 항목의 상품명
				String summaryPname = order.getOrderDetailList().get(0).getProduct().getName();
				if (detailSize > 1) {
					summary.setPname(summaryPname + " 외" + (detailSize - 1) + "건");
				} else {
					summary.setPname(summaryPname);
				}

				// 각 주문별 합계 금액
				int amount = 0;
				for (int i = 0; i < detailSize; i++) {
					amount += order.getOrderDetailList().get(i).getQuantity()
							* order.getOrderDetailList().get(i).getProduct().getPrice2();
				}
				summary.setPrice2(amount);
				summaryList.add(summary);
			}

			// (3) 주문 정보를 화면에 전달
			model.addAttribute("title", "My Page(진행중인 주문 내역");
			model.addAttribute("orderList", summaryList);

		}
		return "mypage/mypage";
	}

	@GetMapping("/order_detail")
	public String orderDetailView(Orders vo, HttpSession session, Model model, @RequestParam(value = "oseq") int oseq) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동
		} else {
			// 주문내역 조회
			Orders order = orderService.getListOrderById(loginUser.getId(), vo.getOseq());

			// 주문 총액 계산
			int totalAmount = 0;
			for (OrderDetail detail : order.getOrderDetailList()) {
				totalAmount += detail.getQuantity() * detail.getProduct().getPrice2();
			}
			// 화면에 출력할 정보
			model.addAttribute("title", "My Page(주문 상세 정보)");
			model.addAttribute("order", order);
			model.addAttribute("totalPrice", totalAmount);

			return "mypage/orderDetail";
		}

	}

	@GetMapping("/order_all")
	public String orderAllView(HttpSession session, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) { // 로그인을 하지 않은경우
			return "member/login"; // 로그인 화면으로 이동
		} else {
			// (1)주문 번호별 주문 조회
			List<Integer> oseqList = orderService.getSeqOrdering(loginUser.getId(), "");

			// (2)각 주문번호 에 대해 주무조회 및 요약정보 생성
			List<OrderVO> summaryList = new ArrayList<OrderVO>();
			for (int oseq : oseqList) {
				OrderVO summary = new OrderVO();

				// 주문번호별 주문 조회
				Orders order = orderService.getListOrderById(loginUser.getId(), oseq);

				// 각 주문의 요약정보 생성
				summary.setOseq(order.getOseq());
				summary.setIndate(order.getIndate());
				// 한 주문번호의 상품 갯수
				int detailSize = order.getOrderDetailList().size();
				// 상세 주문 목록에서 첫번째 항목의 상품명
				String summaryPname = order.getOrderDetailList().get(0).getProduct().getName();
				if (detailSize > 1) {
					summary.setPname(summaryPname + " 외" + (detailSize - 1) + "건");
				} else {
					summary.setPname(summaryPname);
				}

				// 각 주문별 합계 금액
				int amount = 0;
				for (int i = 0; i < detailSize; i++) {
					amount += order.getOrderDetailList().get(i).getQuantity()
							* order.getOrderDetailList().get(i).getProduct().getPrice2();
				}
				summary.setPrice2(amount);
				summaryList.add(summary);
			}

			// (3) 주문 정보를 화면에 전달
			model.addAttribute("title", "총 주문내역");
			model.addAttribute("orderList", summaryList);

		}
		return "mypage/mypage";

	}
}
