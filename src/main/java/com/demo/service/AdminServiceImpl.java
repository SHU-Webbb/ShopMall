package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Admin;
import com.demo.persistence.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	/*
	 * 입력값 : id, pwd
	 * 반환값 : -1 id가 존재하지 않음
	 *         0 비밀번호가 맞지 않음
	 *         1 정상 로그인
	 */
	@Override
	public int adminCheck(Admin vo) {
		int result = -1;
		
		Optional<Admin> admin = adminRepo.findById(vo.getId());
		
		if(admin.isEmpty()) {
			result = -1;
		}else if(vo.getPwd().equals(admin.get().getPwd())) {
			result = 1;
		}else {
			result = 0;
		}
	
		return result;
	}

	
	//관리자 정보 조회
	@Override
	public Admin getAdmin(String id) {
		
		return adminRepo.findById(id).get();
	}

}
