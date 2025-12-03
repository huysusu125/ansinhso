package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.CreateTopicRequest;
import com.huytd.ansinhso.dto.request.UpdateTopicRequest;
import com.huytd.ansinhso.dto.response.TopicResponse;

import java.util.List;

public interface TopicService {
    TopicResponse createTopic(CreateTopicRequest request);
    TopicResponse updateTopic(String id, UpdateTopicRequest request);
    void deleteTopic(String id);
    TopicResponse getTopicById(String id);
    List<TopicResponse> getAllTopics();
}
