package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, String> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
    Optional<Topic> findByName(String name);
}
