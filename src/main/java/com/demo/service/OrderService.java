package com.demo.service;

import java.util.List;

import com.demo.domain.OrderDetail;
import com.demo.domain.Orders;

public interface OrderService {
	
	//다음 저장할 주문 번호
	int getMaxOseq();
	
	//주문 저장후 주문번호 반환
	int insertOrder(Orders vo);
	
	//사용자별 상세 주문 내역
	List<OrderDetail> getListOrderDetailById(String id, int oseq);
	
	//사용자별 주문 내역
	Orders getListOrderById(String id, int oseq);
	
	//사용자별 전체 주문번호 내역
	List<Integer> getSeqOrdering(String id, String result);
	
	//주문결과처리
	void updateOrderResult(int odseq);
	
	//주문 상세정보 저장
	void insertOrderDetail(OrderDetail vo);
	
	
		
}
