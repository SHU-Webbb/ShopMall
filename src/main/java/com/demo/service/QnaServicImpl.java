package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Qna;
import com.demo.persistence.QnaRepository;

@Service
public class QnaServicImpl implements QnaService {

	@Autowired
	private QnaRepository qnaRepo;
	
	@Override
	public void insertQna(Qna qna) {
		
		qnaRepo.save(qna);
	}

	@Override
	public Qna getQna(int qseq) {
		
		return qnaRepo.findById(qseq).get();
	}

	@Override
	public List<Qna> getQnaList(String id) {
		
		return qnaRepo.getQnaList(id);
	}

}
