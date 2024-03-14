package com.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Address {
	
	private String zip_num;
	private String sido;
	private String gugun;
	private String dong;
	@Id
	private String zipcode;
	private String bunji;
	
}
