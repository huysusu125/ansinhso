package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, String> {

    List<QuestionOption> findByQuestionId(String questionId);

    void deleteByQuestionId(String questionId);
}
