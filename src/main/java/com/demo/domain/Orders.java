package com.demo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude ="orderDetailList") //Order 의 ToStirng 실행시 oderDetailList 멤버 제외 시킴
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Orders {

	@Id
	private int oseq;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	private Member member;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(columnDefinition="DATE default sysdate")
	private Date indate;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
	
}
