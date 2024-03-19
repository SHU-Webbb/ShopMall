package com.demo.persistence;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Admin;

@SpringBootTest
public class AdminRepositorytest {

	@Autowired
	private AdminRepository adminRepo;
	
	@Disabled
	@Test
	public void testInsertAdmin() {
		Admin account = Admin.builder()
		           .id("admin")
		           .pwd("admin")
		           .name("관리자")
		           .phone("010-1234-2222")
		           .build();
		    adminRepo.save(account);
	    
		    Admin account2 = Admin.builder()
			        .id("admin2")
			        .pwd("admin")
			        .name("관리자2")
			        .phone("010-1234-1111")
			        .build();
			adminRepo.save(account2);    
		    
	}
}
