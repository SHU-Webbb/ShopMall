package com.demo.service;

import com.demo.domain.Member;

public interface MemberService {
	//회원가입처리
	public void insertMember(Member vo);
	
	//회원정보 상세조회
	public Member getMember(String id);
	
	//회원 로그인
	public int loginID(Member vo);
	
	//회원 인증
	public int confirmID(String id);
	
}
