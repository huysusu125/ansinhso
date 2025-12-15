package com.huytd.ansinhso.service;

import com.huytd.ansinhso.constant.FeedbackStatus;
import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.request.RejectFeedback;
import com.huytd.ansinhso.dto.request.ResolveFeedback;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.dto.response.FeedbackCategoryCountResponse;
import com.huytd.ansinhso.dto.response.FeedbackListResponse;
import com.huytd.ansinhso.dto.response.FeedbackStatusCountResponse;
import com.huytd.ansinhso.dto.response.ListResponse;

import java.util.List;

public interface CitizenFeedbackService {
    CitizenFeedbackResponse submitFeedback(CitizenFeedbackRequest request);

    ListResponse<FeedbackListResponse> getAllFeedback(String title, String fullName, String category, FeedbackStatus status, Integer page, Integer size);

    CitizenFeedbackResponse getFeedbackById(String id);

    CitizenFeedbackResponse acceptFeedback(String id);

    CitizenFeedbackResponse resolveFeedback(String id, ResolveFeedback resolveFeedback);

    CitizenFeedbackResponse rejectFeedback(String id, RejectFeedback rejectFeedback);

    ListResponse<FeedbackListResponse> getAllFeedback(Integer page, Integer size);

    List<FeedbackStatusCountResponse> countFeedbackByStatus();

    List<FeedbackCategoryCountResponse> countByCategory();

}
