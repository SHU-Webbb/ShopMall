package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.Address;

public interface AddressRepository extends JpaRepository<Address, String> {
	//동이름을 조건으로 주소 목록 검색
	public List<Address> findByDongContaining(String dong);
}
