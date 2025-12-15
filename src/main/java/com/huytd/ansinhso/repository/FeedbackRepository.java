package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.dto.response.FeedbackCategoryCountResponse;
import com.huytd.ansinhso.dto.response.FeedbackStatusCountResponse;
import com.huytd.ansinhso.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, String>, JpaSpecificationExecutor<Feedback> {
    Page<Feedback> findAllByCreatedBy(String createdBy, Pageable pageable);
    @Query("""
        SELECT new com.huytd.ansinhso.dto.response.FeedbackStatusCountResponse(
            f.status,
            COUNT(f)
        )
        FROM Feedback f
        GROUP BY f.status
    """)
    List<FeedbackStatusCountResponse> countByStatus();

    @Query("""
        SELECT new com.huytd.ansinhso.dto.response.FeedbackCategoryCountResponse(
            f.category,
            COUNT(f)
        )
        FROM Feedback f
        GROUP BY f.category
    """)
    List<FeedbackCategoryCountResponse> countByCategory();

}
