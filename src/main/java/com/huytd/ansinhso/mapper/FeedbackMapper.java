package com.huytd.ansinhso.mapper;

import com.huytd.ansinhso.dto.request.CitizenFeedbackRequest;
import com.huytd.ansinhso.dto.response.CitizenFeedbackResponse;
import com.huytd.ansinhso.dto.response.FeedbackListResponse;
import com.huytd.ansinhso.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FeedbackMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "note", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    Feedback toEntity(CitizenFeedbackRequest request);

    @Mapping(target = "attachmentUrls", ignore = true)
    CitizenFeedbackResponse toCitizenFeedbackResponse(Feedback feedback);

    FeedbackListResponse toFeedbackListResponse(Feedback feedback);

    List<FeedbackListResponse> toFeedbackListResponse(List<Feedback> feedback);
}
