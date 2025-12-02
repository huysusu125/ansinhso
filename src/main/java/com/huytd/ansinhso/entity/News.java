package com.huytd.ansinhso.entity;

import com.huytd.ansinhso.constant.NewsStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class News extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "is_pinned")
    private Boolean isPinned = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NewsStatus status;

    @Column(name = "publish_at")
    private Timestamp publishAt;

    @Column(name = "views")
    private Long views = 0L;
}
