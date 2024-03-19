package com.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Admin {
	@Id
	private String id;
    private String pwd;
    private String name;
    private String phone;
}
