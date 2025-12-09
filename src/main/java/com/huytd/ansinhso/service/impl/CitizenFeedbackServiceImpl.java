package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.constant.FeedbackStatus;
import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.dto.response.FeedbackListResponse;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.entity.Feedback;
import com.huytd.ansinhso.entity.FeedbackAttachment;
import com.huytd.ansinhso.exception.BusinessException;
import com.huytd.ansinhso.exception.ResourceNotFoundException;
import com.huytd.ansinhso.mapper.FeedbackMapper;
import com.huytd.ansinhso.repository.FeedbackAttachmentRepository;
import com.huytd.ansinhso.repository.FeedbackRepository;
import com.huytd.ansinhso.repository.specification.FeedbackSpecification;
import com.huytd.ansinhso.service.CitizenFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CitizenFeedbackServiceImpl implements CitizenFeedbackService {

    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackAttachmentRepository feedbackAttachmentRepository;

    @Override
    @Transactional
    public CitizenFeedbackResponse submitFeedback(CitizenFeedbackRequest request) {
        Feedback feedback = feedbackMapper.toEntity(request);
        feedbackRepository.save(feedback);
        CitizenFeedbackResponse citizenFeedbackResponse = feedbackMapper.toCitizenFeedbackResponse(feedback);
        if (!CollectionUtils.isEmpty(request.getAttachmentUrls())) {
            feedbackAttachmentRepository.saveAll(request
                    .getAttachmentUrls()
                    .stream()
                    .map(attachment -> FeedbackAttachment
                            .builder()
                            .url(attachment)
                            .feedbackId(feedback.getId())
                            .build())
                    .toList());
            citizenFeedbackResponse.setAttachmentUrls(request.getAttachmentUrls());
        }
        return citizenFeedbackResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public ListResponse<FeedbackListResponse> getAllFeedback(String title, String fullName, String category,
                                                             FeedbackStatus status, Integer page, Integer size) {
        Sort sort = Sort.by(
                Sort.Order.desc("updatedAt")
        );
        Specification<Feedback> spec = FeedbackSpecification.withFilters(title, fullName, category, status);
        Page<Feedback> feedbacks = feedbackRepository.findAll(spec, PageRequest.of(page, size, sort));
        return ListResponse.of(feedbackMapper.toFeedbackListResponse(feedbacks.getContent()), feedbacks.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public CitizenFeedbackResponse getFeedbackById(String id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));

        return enrichCitizenFeedbackResponseWithAttachment(feedback);
    }

    private CitizenFeedbackResponse enrichCitizenFeedbackResponseWithAttachment(Feedback feedback) {
        CitizenFeedbackResponse citizenFeedbackResponse = feedbackMapper.toCitizenFeedbackResponse(feedback);

        List<FeedbackAttachment> feedbackAttachments = feedbackAttachmentRepository.findAllByFeedbackId(feedback.getId());
        if (!CollectionUtils.isEmpty(feedbackAttachments)) {
            citizenFeedbackResponse.setAttachmentUrls(feedbackAttachments.stream().map(FeedbackAttachment::getUrl).toList());
        }
        return citizenFeedbackResponse;
    }

    @Override
    @Transactional
    public CitizenFeedbackResponse acceptFeedback(String id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
        if (!FeedbackStatus.PENDING.equals(feedback.getStatus())) {
            throw new BusinessException("Feedback status is not PENDING");
        }
        feedback.setStatus(FeedbackStatus.IN_PROGRESS);
        feedbackRepository.save(feedback);
        return enrichCitizenFeedbackResponseWithAttachment(feedback);
    }

    @Override
    public CitizenFeedbackResponse resolveFeedback(String id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
        if (!FeedbackStatus.IN_PROGRESS.equals(feedback.getStatus())) {
            throw new BusinessException("Feedback status is not IN_PROGRESS");
        }
        feedback.setStatus(FeedbackStatus.RESOLVED);
        feedbackRepository.save(feedback);
        return enrichCitizenFeedbackResponseWithAttachment(feedback);
    }

    @Override
    @Transactional
    public CitizenFeedbackResponse rejectFeedback(String id) {

        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));

        validateRejection(feedback);

        feedback.setStatus(FeedbackStatus.REJECTED);
        feedbackRepository.save(feedback);

        return enrichCitizenFeedbackResponseWithAttachment(feedback);
    }

    private void validateRejection(Feedback feedback) {

        FeedbackStatus current = feedback.getStatus();

        switch (current) {
            case REJECTED -> throw new BusinessException("Feedback is already REJECTED");
            case RESOLVED -> throw new BusinessException("Resolved feedback cannot be rejected");
            default -> {
                // PENDING, IN_PROGRESS → hợp lệ
            }
        }
    }
}
