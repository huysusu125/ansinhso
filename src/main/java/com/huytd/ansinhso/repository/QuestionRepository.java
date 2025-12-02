package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, String> {

    @Query("SELECT q FROM Question q WHERE " +
            "(:search IS NULL OR LOWER(q.content) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Question> findWithFilters(
            @Param("search") String search,
            Pageable pageable
    );

}
