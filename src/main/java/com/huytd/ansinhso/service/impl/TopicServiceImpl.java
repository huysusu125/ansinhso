package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.dto.request.CreateTopicRequest;
import com.huytd.ansinhso.dto.request.UpdateTopicRequest;
import com.huytd.ansinhso.dto.response.TopicResponse;
import com.huytd.ansinhso.entity.Topic;
import com.huytd.ansinhso.exception.BusinessException;
import com.huytd.ansinhso.exception.ResourceNotFoundException;
import com.huytd.ansinhso.mapper.TopicMapper;
import com.huytd.ansinhso.repository.NewsRepository;
import com.huytd.ansinhso.repository.TopicRepository;
import com.huytd.ansinhso.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final NewsRepository newsRepository;
    private final TopicMapper topicMapper;

    @Override
    @Transactional
    public TopicResponse createTopic(CreateTopicRequest request) {
        // Kiểm tra trùng tên
        if (topicRepository.existsByName(request.getName())) {
            throw new BusinessException("Topic already exists with name: " + request.getName());
        }

         Topic topic = topicMapper.toEntity(request);
        Topic savedTopic = topicRepository.save(topic);

        return topicMapper.toResponse(savedTopic);
    }

    @Override
    @Transactional
    public TopicResponse updateTopic(Long id, UpdateTopicRequest request) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with id: " + id
                ));

        // Kiểm tra trùng tên với topic khác
        if (topicRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new BusinessException("Topic already exists with name: " + request.getName());
        }

        topicMapper.updateEntityFromRequest(request, topic);
        Topic updatedTopic = topicRepository.save(topic);

        return topicMapper.toResponse(updatedTopic);
    }

    @Override
    @Transactional
    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with id: " + id
                ));

        // Kiểm tra xem topic có đang được sử dụng không
        if (newsRepository.existsByTopicId(id)) {
            throw new BusinessException(
                    "Cannot delete topic with id " + id + " because it is being used by news articles"
            );
        }

        topicRepository.delete(topic);
    }

    @Override
    @Transactional(readOnly = true)
    public TopicResponse getTopicById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with id: " + id
                ));
        return topicMapper.toResponse(topic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopicResponse> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topicMapper.toResponseList(topics);
    }
}