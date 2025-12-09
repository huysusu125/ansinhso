package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.entity.Feedback;
import com.huytd.ansinhso.entity.FeedbackAttachment;
import com.huytd.ansinhso.mapper.FeedbackMapper;
import com.huytd.ansinhso.repository.FeedbackAttachmentRepository;
import com.huytd.ansinhso.repository.FeedbackRepository;
import com.huytd.ansinhso.service.CitizenFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
}
