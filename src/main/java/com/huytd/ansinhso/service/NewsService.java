package com.huytd.ansinhso.service;

import com.huytd.ansinhso.constant.NewsStatus;
import com.huytd.ansinhso.dto.request.CreateNewsRequest;
import com.huytd.ansinhso.dto.request.UpdateNewsRequest;
import com.huytd.ansinhso.dto.response.ListResponse;
import com.huytd.ansinhso.dto.response.NewsDetailResponse;
import com.huytd.ansinhso.dto.response.NewsListResponse;

public interface NewsService {
    NewsDetailResponse createNews(CreateNewsRequest request);
    NewsDetailResponse updateNews(String id, UpdateNewsRequest request);
    void deleteNews(String id);
    NewsDetailResponse getNewsById(String id);
    ListResponse<NewsListResponse> getAllNews(String title, String topicId, NewsStatus status, Integer page, Integer size);
    NewsDetailResponse publishNews(String id);
    NewsDetailResponse unpublishNews(String id);
    NewsDetailResponse pinNews(String id);
    NewsDetailResponse unpinNews(String id);
}
