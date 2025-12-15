package com.huytd.ansinhso.mapper;

import com.huytd.ansinhso.dto.request.CreateTouristPlaceRequest;
import com.huytd.ansinhso.dto.response.TouristPlaceResponse;
import com.huytd.ansinhso.entity.Location;
import com.huytd.ansinhso.entity.OpeningHour;
import com.huytd.ansinhso.entity.RegularOpeningHours;
import com.huytd.ansinhso.entity.TouristPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TouristPlaceMapper {

    // Map from Request DTO to Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "views", ignore = true)
    @Mapping(target = "status", defaultValue = "DRAFT")
    TouristPlace toEntity(CreateTouristPlaceRequest request);

    // Map from Entity to Response DTO
    TouristPlaceResponse toResponse(TouristPlace entity);

    // Map list of entities to list of responses
    List<TouristPlaceResponse> toResponseList(List<TouristPlace> entities);

    // Map Location DTO to Location
    Location toLocation(CreateTouristPlaceRequest.LocationDto locationDto);

    // Map RegularOpeningHours DTO to RegularOpeningHours
    RegularOpeningHours toRegularOpeningHours(CreateTouristPlaceRequest.RegularOpeningHoursDto dto);

    // Map OpeningHour DTO to OpeningHour
    OpeningHour toOpeningHour(CreateTouristPlaceRequest.OpeningHourDto dto);

    // Update entity from request (for PUT operations)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "views", ignore = true)
    void updateEntityFromRequest(CreateTouristPlaceRequest request, @MappingTarget TouristPlace entity);
}

