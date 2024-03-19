package com.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {
	private int odseq;
	private int oseq;
	private String id;
	private Date indate;
	private String mname;
	private String zipnum;
	private String address;
	private String phone;
	private int pseq;
	private String pname;
	private int quantity;
	private int price2;
	private String result;
}