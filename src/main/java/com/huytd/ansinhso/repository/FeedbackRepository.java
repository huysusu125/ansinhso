package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.Feedback;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<Feedback, String>, JpaSpecificationExecutor<Feedback> {
}
