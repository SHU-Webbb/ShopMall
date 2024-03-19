package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.Qna;

public interface QnaRepository extends JpaRepository<Qna, Integer> {
	
	@Query("SELECT q FROM Qna q WHERE q.member.id =?1 ORDER BY q.qseq DESC")
	public List<Qna> getQnaList(String id);
}
