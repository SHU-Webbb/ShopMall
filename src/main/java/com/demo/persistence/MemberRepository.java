package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Member;
import com.demo.domain.OrderDetail;

public interface MemberRepository extends JpaRepository<Member, String> {

	public Member findByNameAndEmail(String name, String email);

	public Member findByIdAndNameAndEmail(String id, String name, String email);

	@Transactional
	@Modifying
	@Query(value="UPDATE member SET pwd=:pwd WHERE id=:id", nativeQuery=true) //DB의 sql문법을 직접 사용
	public int changePassword(@Param("id") String id,@Param("pwd") String pwd);
	
	public List<Member> findMemberByNameContaining(String name);
}




