package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString
@Builder
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //모든멤버를 가진 생성자
@DynamicInsert //필요한 값만 insert. (default 값을 저장)
@DynamicUpdate //필요한 값만 update.
@Entity
public class ProductComment {
	@Id
	@GeneratedValue(strategy=GenerationType	.IDENTITY)
	private int commnet_seq;
	@ManyToOne
	@JoinColumn(name="pseq",nullable=false)
	private Product product;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="name",nullable=false)
	private Member member;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@ColumnDefault("sysdate")
	@Column(updatable=false)
	private Date regdate;
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(insertable=false)
	private Date modifydate;
}
