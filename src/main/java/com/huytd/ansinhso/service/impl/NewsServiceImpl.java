package com.huytd.ansinhso.service.impl;

import com.huytd.ansinhso.constant.NewsStatus;
import com.huytd.ansinhso.dto.request.CreateNewsRequest;
import com.huytd.ansinhso.dto.request.UpdateNewsRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.NewsDetailResponse;
import com.huytd.ansinhso.dto.response.NewsListResponse;
import com.huytd.ansinhso.entity.News;
import com.huytd.ansinhso.entity.NewsContent;
import com.huytd.ansinhso.entity.Topic;
import com.huytd.ansinhso.exception.BusinessException;
import com.huytd.ansinhso.exception.ResourceNotFoundException;
import com.huytd.ansinhso.mapper.NewsMapper;
import com.huytd.ansinhso.repository.NewsContentRepository;
import com.huytd.ansinhso.repository.NewsRepository;
import com.huytd.ansinhso.repository.TopicRepository;
import com.huytd.ansinhso.repository.specification.NewsSpecification;
import com.huytd.ansinhso.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsContentRepository newsContentRepository;
    private final TopicRepository topicRepository;
    private final NewsMapper newsMapper;

    @Override
    @Transactional
    public NewsDetailResponse createNews(CreateNewsRequest request) {
        // Validate topic exists
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with id: " + request.getTopicId()
                ));

        // Save News (without content)
        News news = newsMapper.toEntity(request);
        News savedNews = newsRepository.save(news);

        // Save NewsContent separately
        NewsContent newsContent = NewsContent.builder()
                .newsId(savedNews.getId())
                .content(request.getContent())
                .build();
        newsContentRepository.save(newsContent);

        // Build response with content and topicName
        NewsDetailResponse response = newsMapper.toDetailResponse(savedNews);
        response.setContent(request.getContent());
        response.setTopicName(topic.getName());

        return response;
    }

    @Override
    @Transactional
    public NewsDetailResponse updateNews(Long id, UpdateNewsRequest request) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News not found with id: " + id
                ));

        // Validate topic exists
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with id: " + request.getTopicId()
                ));

        // Update News
        newsMapper.updateEntityFromRequest(request, news);
        News updatedNews = newsRepository.save(news);

        // Update NewsContent
        NewsContent newsContent = newsContentRepository.findByNewsId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News content not found for news id: " + id
                ));
        newsContent.setContent(request.getContent());
        newsContentRepository.save(newsContent);

        // Build response
        NewsDetailResponse response = newsMapper.toDetailResponse(updatedNews);
        response.setContent(request.getContent());
        response.setTopicName(topic.getName());

        return response;
    }

    @Override
    @Transactional
    public void deleteNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News not found with id: " + id
                ));

        // Delete NewsContent first
        newsContentRepository.deleteByNewsId(id);

        // Delete News
        newsRepository.delete(news);
    }

    @Override
    @Transactional(readOnly = true)
    public NewsDetailResponse getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News not found with id: " + id
                ));

        // Get content
        NewsContent newsContent = newsContentRepository.findByNewsId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News content not found for news id: " + id
                ));

        // Get topic name
        Topic topic = topicRepository.findById(news.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with id: " + news.getTopicId()
                ));

        // Build response
        NewsDetailResponse response = newsMapper.toDetailResponse(news);
        response.setContent(newsContent.getContent());
        response.setTopicName(topic.getName());

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public ListResponse<NewsListResponse> getAllNews(String title, Long topicId, NewsStatus status, Integer page, Integer size) {
        Sort sort = Sort.by(
                Sort.Order.desc("isPinned"),
                Sort.Order.desc("createdAt")
        );

        Specification<News> spec = NewsSpecification.withFilters(title, topicId, status);
        Page<News> newsList = newsRepository.findAll(spec, PageRequest.of(page, size, sort));
        return ListResponse.of(enrichWithTopicNames(newsList.getContent()), newsList.getTotalElements());
    }

    @Override
    @Transactional
    public NewsDetailResponse publishNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News not found with id: " + id
                ));

        if (NewsStatus.PUBLISHED.equals(news.getStatus())) {
            throw new BusinessException("News is already published");
        }

        news.setStatus(NewsStatus.PUBLISHED);
        if (news.getPublishAt() == null) {
            news.setPublishAt(new Timestamp(System.currentTimeMillis()));
        }

        News publishedNews = newsRepository.save(news);
        return buildDetailResponse(publishedNews);
    }

    @Override
    @Transactional
    public NewsDetailResponse unpublishNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News not found with id: " + id
                ));

        if (NewsStatus.DRAFT.equals(news.getStatus())) {
            throw new BusinessException("News is already unpublished");
        }

        news.setStatus(NewsStatus.DRAFT);
        News unpublishedNews = newsRepository.save(news);

        return buildDetailResponse(unpublishedNews);
    }

    @Override
    @Transactional
    public NewsDetailResponse pinNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News not found with id: " + id
                ));

        if (Boolean.TRUE.equals(news.getIsPinned())) {
            throw new BusinessException("News is already pinned");
        }

        news.setIsPinned(true);
        News pinnedNews = newsRepository.save(news);

        return buildDetailResponse(pinnedNews);
    }

    @Override
    @Transactional
    public NewsDetailResponse unpinNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News not found with id: " + id
                ));

        if (Boolean.FALSE.equals(news.getIsPinned())) {
            throw new BusinessException("News is not pinned");
        }

        news.setIsPinned(false);
        News unpinnedNews = newsRepository.save(news);

        return buildDetailResponse(unpinnedNews);
    }

    // Helper method: Enrich a news list with topic names
    private List<NewsListResponse> enrichWithTopicNames(List<News> newsList) {
        if (newsList.isEmpty()) {
            return List.of();
        }

        // Get all unique topic IDs
        List<Long> topicIds = newsList.stream()
                .map(News::getTopicId)
                .distinct()
                .collect(Collectors.toList());

        // Fetch all topics in one query
        Map<Long, String> topicNamesMap = topicRepository.findAllById(topicIds).stream()
                .collect(Collectors.toMap(Topic::getId, Topic::getName));

        // Map to response with topic names
        return newsList.stream()
                .map(news -> {
                    NewsListResponse response = newsMapper.toListResponse(news);
                    response.setTopicName(topicNamesMap.get(news.getTopicId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Helper method: Build detail response with content and topic name
    private NewsDetailResponse buildDetailResponse(News news) {
        // Get content
        NewsContent newsContent = newsContentRepository.findByNewsId(news.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "News content not found for news id: " + news.getId()
                ));

        // Get topic name
        Topic topic = topicRepository.findById(news.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Topic not found with id: " + news.getTopicId()
                ));

        // Build response
        NewsDetailResponse response = newsMapper.toDetailResponse(news);
        response.setContent(newsContent.getContent());
        response.setTopicName(topic.getName());

        return response;
    }
}