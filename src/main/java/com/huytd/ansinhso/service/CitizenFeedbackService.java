package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;

public interface CitizenFeedbackService {
    CitizenFeedbackResponse submitFeedback(CitizenFeedbackRequest request);
}
