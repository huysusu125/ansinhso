package com.huytd.ansinhso.repository.specification;

import com.huytd.ansinhso.constant.NewsStatus;
import com.huytd.ansinhso.entity.News;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class NewsSpecification {

    public static Specification<News> withFilters(String title, String topicId, NewsStatus status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by title (case-insensitive partial match)
            if (title != null && !title.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                ));
            }

            // Filter by topicId
            if (topicId != null) {
                predicates.add(criteriaBuilder.equal(root.get("topicId"), topicId));
            }

            // Filter by status
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}