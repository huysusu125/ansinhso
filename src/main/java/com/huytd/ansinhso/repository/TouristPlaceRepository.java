package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.constant.Status;
import com.huytd.ansinhso.entity.TouristPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TouristPlaceRepository extends JpaRepository<TouristPlace, String> {
    List<TouristPlace> findByCategory(String category);

    @Query("SELECT t FROM TouristPlace t WHERE t.displayName LIKE %:keyword% OR t.formattedAddress LIKE %:keyword%")
    List<TouristPlace> searchByKeyword(@Param("keyword") String keyword);

    List<TouristPlace> findAllByStatusOrderByCreatedAtDesc(Status status);


}
