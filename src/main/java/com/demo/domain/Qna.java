package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
public class Qna {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qseq;
	private String subject;
	private String content;
	private String reply;
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	private Member member;
	@ColumnDefault("1")
	private String rep;
	@ColumnDefault("sysdate")
	private Date indate;
}
