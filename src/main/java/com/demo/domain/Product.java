package com.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pseq;
	private String name;
	private String kind;
	private int price1;
	private int price2;
	private int price3;
	private String content;
	@Column(columnDefinition ="varchar2(255) default 'default.jpg'")
	private String image;
	@Column(columnDefinition ="char(1) default 'y'")
	private String useyn;
	@Column(columnDefinition ="char(1) default 'n'")
	private String bestyn;
	@Temporal(value= TemporalType.TIMESTAMP)
	@ColumnDefault("sysdate")
	private Date regdate;
	
	public Product() {
        
    }
	
	public Product(String name, String kind, int price1, int price2, int price3, String content, String image,
			String useyn, String bestyn, Date regdate) {
		
		super();
		this.name = name;
		this.kind = kind;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.content = content;
		this.image = image;
		this.useyn = useyn;
		this.bestyn = bestyn;
		this.regdate = regdate;
	}
	
	
	
}
