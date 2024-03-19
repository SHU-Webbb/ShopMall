package com.demo.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Member;
import com.demo.domain.Qna;

@SpringBootTest
public class QnaRepositoryTest {
	
    @Autowired
    QnaRepository qnaRepo;
    
    @Disabled
    @Test
    public void testInsertQna() {
    
    	Member member = new Member("one" ,"1111", "홍길동", "kimnari@email.com",
				"","","","y",new Date());
    	
    	Qna qna1 = Qna.builder().subject("Qna 테스트")
    			.content("질문 내용1.")
                .reply("답변드립니다.")
                .indate(new Date())
                .member(member)
                .build();
    			
    	qnaRepo.save(qna1);
    	
    	Qna qna2 = Qna.builder().subject("Qna 테스트2")
    			.content("질문 내용2.")
                .indate(new Date())
                .member(member)
                .build();
    			
    	qnaRepo.save(qna2);
    }
    @Disabled
    @Test
    public void testGetQnaLIst() {
    	
    	List<Qna> qnaList = new ArrayList<Qna>();
    	
    	qnaList = qnaRepo.getQnaList("one");
    	
    	for (Qna qna : qnaList) {
    		System.out.println(qna);
    	}
    	
    }
}
