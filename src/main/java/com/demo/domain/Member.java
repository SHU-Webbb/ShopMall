package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
public class Member {
	@Id
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String zip_num;
	private String address;
	private String phone;
	@ColumnDefault("'y'")
	private String useyn;
	@Temporal(value = TemporalType.TIMESTAMP)
	@ColumnDefault("sysdate")
	private Date regdate;
	
}
