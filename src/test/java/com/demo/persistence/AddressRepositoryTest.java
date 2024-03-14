package com.demo.persistence;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Address;

@SpringBootTest
public class AddressRepositoryTest {
	
	@Autowired
    private AddressRepository addressRepo;
	
	@Disabled
	@Test
	public void testFindAddress() {
		List<Address> addrList = addressRepo.findByDongContaining("신림");
		
		for (Address addr : addrList) {
            System.out.println(addr.toString());
        }
	}
	
}
