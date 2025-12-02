package com.huytd.ansinhso.service;

import com.huytd.ansinhso.constant.NewsStatus;
import com.huytd.ansinhso.dto.request.CreateNewsRequest;
import com.huytd.ansinhso.dto.request.UpdateNewsRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.NewsDetailResponse;
import com.huytd.ansinhso.dto.response.NewsListResponse;

public interface NewsService {
    NewsDetailResponse createNews(CreateNewsRequest request);
    NewsDetailResponse updateNews(Long id, UpdateNewsRequest request);
    void deleteNews(Long id);
    NewsDetailResponse getNewsById(Long id);
    ListResponse<NewsListResponse> getAllNews(String title, Long topicId, NewsStatus status, Integer page, Integer size);
    NewsDetailResponse publishNews(Long id);
    NewsDetailResponse unpublishNews(Long id);
    NewsDetailResponse pinNews(Long id);
    NewsDetailResponse unpinNews(Long id);
}
