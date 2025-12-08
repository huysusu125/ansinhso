package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.service.CitizenFeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CitizenFeedbackServiceImpl implements CitizenFeedbackService {

    @Override
    @Transactional
    public CitizenFeedbackResponse submitFeedback(CitizenFeedbackRequest request, String ipAddress) {
        return null;
    }
}
