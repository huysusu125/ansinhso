package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.NewsContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsContentRepository extends JpaRepository<NewsContent, String> {

    Optional<NewsContent> findByNewsId(String newsId);

    void deleteByNewsId(String newsId);

}
