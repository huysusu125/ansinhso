package com.huytd.ansinhso.service;

import com.huytd.ansinhso.dto.request.CreateTopicRequest;
import com.huytd.ansinhso.dto.request.UpdateTopicRequest;
import com.huytd.ansinhso.dto.response.TopicResponse;

import java.util.List;

public interface TopicService {
    TopicResponse createTopic(CreateTopicRequest request);
    TopicResponse updateTopic(Long id, UpdateTopicRequest request);
    void deleteTopic(Long id);
    TopicResponse getTopicById(Long id);
    List<TopicResponse> getAllTopics();
}
