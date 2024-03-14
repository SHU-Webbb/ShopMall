package com.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
}
