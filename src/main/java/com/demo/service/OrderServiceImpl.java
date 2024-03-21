package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Cart;
import com.demo.domain.OrderDetail;
import com.demo.domain.Orders;
import com.demo.persistence.OrderDetailRepository;
import com.demo.persistence.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Autowired
	private CartService cartService;
	
	@Override
	public int getMaxOseq() {
		return orderRepo.selectMaxOseq();
	}

	@Override
	public int insertOrder(Orders vo) {
		
		//(1)신규 주문번호 할당
		int oseq = getMaxOseq();
		vo.setOseq(oseq);
		System.out.println("insertOrder : vo=" + vo);
		
		//(2)신규 주문을 주문테이블에 저장
		orderRepo.save(vo);
		
		//(3) 장바구니 목록을 읽어와 order_dtail 테이블에 저장
		List<Cart> cartList = cartService.getCartList(vo.getMember().getId());
		
		for(Cart cart : cartList) {
			OrderDetail orderDetail = new OrderDetail();
			
			//주문정보 저장
			orderDetail.setOrder(vo);
			
			//상품정보 저장
			orderDetail.setQuantity(cart.getQuantity());
			orderDetail.setProduct(cart.getProduct());
			
			//주문상세 정보 저장
			insertOrderDetail(orderDetail);
			
			//장바구니 처리결과 업데이트 '1' -> '2'
			cartService.updateCart(cart.getCseq());
		}
		return oseq;
	}

	@Override
	public List<OrderDetail> getListOrderDetailById(String id, int oseq) {
		return orderRepo.getListOrderById(id, oseq, "1");
	}
	
	@Override
	public Orders getListOrderById(String id, int oseq) {
		return orderRepo.getOrderByMemberId(id,oseq);
	}

	@Override
	public List<Integer> getSeqOrdering(String id, String result) {
		return orderRepo.getSeqOrdering(id, result);
	}

	@Override
	public void updateOrderResult(int odseq) {
		//상세 정보 조회
		OrderDetail od = orderDetailRepo.findById(odseq).get();
		
		//처리결과 수정
		od.setResult("2");
		
		//상세정보 수정
		orderDetailRepo.save(od);
	}

	@Override
	public void insertOrderDetail(OrderDetail vo) {
		orderDetailRepo.save(vo);
	}

	@Override
	public List<OrderDetail> getListOrder(String name) {
		return orderRepo.getOrderListByName(name);
	}

	
	

}
