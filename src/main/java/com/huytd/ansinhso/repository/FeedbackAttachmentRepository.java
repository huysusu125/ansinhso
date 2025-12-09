package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.FeedbackAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackAttachmentRepository extends JpaRepository<FeedbackAttachment, String> {
    List<FeedbackAttachment> findAllByFeedbackId(String feedbackId);
}