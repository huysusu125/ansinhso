package com.huytd.ansinhso.service;

import com.huytd.ansinhso.constant.Status;
import com.huytd.ansinhso.dto.request.CreateTouristPlaceRequest;
import com.huytd.ansinhso.dto.response.LocationTopView;
import com.huytd.ansinhso.dto.response.TouristPlaceResponse;
import com.huytd.ansinhso.entity.TouristPlace;
import com.huytd.ansinhso.exception.ResourceNotFoundException;
import com.huytd.ansinhso.mapper.TouristPlaceMapper;
import com.huytd.ansinhso.repository.TouristPlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TouristPlaceService {

    private final TouristPlaceRepository repository;
    private final TouristPlaceMapper mapper;

    public TouristPlaceResponse createTouristPlace(CreateTouristPlaceRequest request) {
        log.info("Creating tourist place: {}", request.getDisplayName());

        TouristPlace place = mapper.toEntity(request);

        if (place.getStatus() == null) {
            place.setStatus(Status.DRAFT);
        }

        TouristPlace savedPlace = repository.save(place);
        log.info("Tourist place created successfully with ID: {}", savedPlace.getId());

        return mapper.toResponse(savedPlace);
    }

    public TouristPlaceResponse getTouristPlace(String id) {
        log.info("Fetching tourist place with ID: {}", id);
        TouristPlace place = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist place not found with id: " + id));
        return mapper.toResponse(place);
    }

    public TouristPlaceResponse getTouristPlaceApp(String id) {
        log.info("Fetching tourist place with ID: {}", id);
        TouristPlace place = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist place not found with id: " + id));
        place.setViews(place.getViews() + 1);
        repository.save(place);
        return mapper.toResponse(place);
    }

    public List<TouristPlaceResponse> getAllTouristPlaces() {
        log.info("Fetching all tourist places");
        List<TouristPlace> places = repository.findAll();
        return mapper.toResponseList(places);
    }

    public List<TouristPlaceResponse> getTouristPlacesByCategory(String category) {
        log.info("Fetching tourist places by category: {}", category);
        List<TouristPlace> places = repository.findByCategory(category);
        return mapper.toResponseList(places);
    }

    public List<TouristPlaceResponse> searchTouristPlaces(String keyword) {
        log.info("Searching tourist places with keyword: {}", keyword);
        List<TouristPlace> places = repository.searchByKeyword(keyword);
        return mapper.toResponseList(places);
    }

    public TouristPlaceResponse updateTouristPlace(String id, CreateTouristPlaceRequest request) {
        log.info("Updating tourist place with ID: {}", id);

        TouristPlace existingPlace = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist place not found with id: " + id));

        // Use MapStruct to update existing entity
        mapper.updateEntityFromRequest(request, existingPlace);

        TouristPlace updatedPlace = repository.save(existingPlace);
        log.info("Tourist place updated successfully with ID: {}", updatedPlace.getId());

        return mapper.toResponse(updatedPlace);
    }

    public void deleteTouristPlace(String id) {
        log.info("Deleting tourist place with ID: {}", id);
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tourist place not found with id: " + id);
        }
        repository.deleteById(id);
        log.info("Tourist place deleted successfully with ID: {}", id);
    }

    public List<TouristPlaceResponse> getAllPublishedTouristPlaces() {
        List<TouristPlace> places = repository.findAllByStatusOrderByCreatedAtDesc(Status.PUBLISHED);
        return mapper.toResponseList(places);
    }

    public List<LocationTopView> getTop10Viewed() {
        return repository.findTop10ByOrderByViewsDescUpdatedAtDesc()
                .stream()
                .map(location -> LocationTopView
                        .builder()
                        .displayName(location.getDisplayName())
                        .category(location.getCategory())
                        .views(location.getViews())
                        .build())
                .toList();
    }
}