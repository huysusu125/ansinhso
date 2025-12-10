package com.huytd.ansinhso.service;

import com.huytd.ansinhso.constant.FeedbackStatus;
import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.dto.response.FeedbackListResponse;
import com.huytd.ansinhso.dto.response.ListResponse;

public interface CitizenFeedbackService {
    CitizenFeedbackResponse submitFeedback(CitizenFeedbackRequest request);

    ListResponse<FeedbackListResponse> getAllFeedback(String title, String fullName, String category, FeedbackStatus status, Integer page, Integer size);

    CitizenFeedbackResponse getFeedbackById(String id);

    CitizenFeedbackResponse acceptFeedback(String id);

    CitizenFeedbackResponse resolveFeedback(String id);

    CitizenFeedbackResponse rejectFeedback(String id);

    ListResponse<FeedbackListResponse> getAllFeedback(Integer page, Integer size);
}
