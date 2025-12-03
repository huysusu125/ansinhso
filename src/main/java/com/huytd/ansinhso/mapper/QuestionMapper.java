package com.huytd.ansinhso.mapper;

import com.huytd.ansinhso.dto.request.OptionRequest;
import com.huytd.ansinhso.dto.request.QuestionRequest;
import com.huytd.ansinhso.dto.response.OptionResponse;
import com.huytd.ansinhso.dto.response.QuestionListResponse;
import com.huytd.ansinhso.dto.response.QuestionResponse;
import com.huytd.ansinhso.entity.Question;
import com.huytd.ansinhso.entity.QuestionOption;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface QuestionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Question toEntity(QuestionRequest request);

    QuestionResponse toResponse(Question question, List<QuestionOption> options);

    QuestionListResponse toListResponse(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(QuestionRequest request, @MappingTarget Question question);

    // QuestionOption mappings
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionId", source = "questionId")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    QuestionOption toOptionEntity(OptionRequest request, String questionId);

    OptionResponse toOptionResponse(QuestionOption option);

    List<OptionResponse> toOptionResponseList(List<QuestionOption> options);
}
