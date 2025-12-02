package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {
    boolean existsByTopicId(Long topicId);

    List<News> findByTopicId(Long topicId);

    @Query("SELECT n FROM News n ORDER BY n.isPinned DESC, n.createdAt DESC")
    List<News> findAllOrderByPinnedAndCreatedAt();

    @Query("SELECT n FROM News n WHERE n.topicId = :topicId ORDER BY n.isPinned DESC, n.createdAt DESC")
    List<News> findByTopicIdOrderByPinnedAndCreatedAt(Long topicId);

    @Query("SELECT n FROM News n WHERE n.status = :status ORDER BY n.isPinned DESC, n.createdAt DESC")
    List<News> findByStatusOrderByPinnedAndCreatedAt(String status);
}