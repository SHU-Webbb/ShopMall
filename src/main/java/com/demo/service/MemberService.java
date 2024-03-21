package com.demo.service;

import java.util.List;

import com.demo.domain.Address;
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
	
	//동 이름으로 주소 찾기
	public List<Address> getAddressByDong(String dong);
	
	//이름 이메일로 아이디 찾기
	public Member getIdByNameEmail(String name, String email);
	
	//아이디 이름 이메일로 비밀번호 찾기
	public Member getPwdByIdNameEmail(String id, String name, String email);
	
	//비밀번호 변경
	public void changePassword(Member vo);
	
	//회원 목록 조회
	public List<Member> getMemberList(String name);
}
