package com.huytd.ansinhso.mapper;

import com.huytd.ansinhso.dto.request.CreateNewsRequest;
import com.huytd.ansinhso.dto.request.UpdateNewsRequest;
import com.huytd.ansinhso.dto.response.NewsDetailResponse;
import com.huytd.ansinhso.dto.response.NewsListResponse;
import com.huytd.ansinhso.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NewsMapper {

    @Mapping(target = "isPinned", constant = "false")
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(target = "views", constant = "0L")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publishAt", ignore = true)
    News toEntity(CreateNewsRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isPinned", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "views", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "publishAt", ignore = true)
    void updateEntityFromRequest(UpdateNewsRequest request, @MappingTarget News news);

    @Mapping(target = "topicName", ignore = true)
    NewsListResponse toListResponse(News news);

    List<NewsListResponse> toListResponseList(List<News> newsList);

    @Mapping(target = "content", ignore = true)
    @Mapping(target = "topicName", ignore = true)
    NewsDetailResponse toDetailResponse(News news);
}
