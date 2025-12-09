package com.huytd.ansinhso.entity;

import com.huytd.ansinhso.constant.NewsStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Table(name = "new")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class News extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "topic_id")
    private String topicId;

    @Column(name = "is_pinned")
    @Builder.Default
    private Boolean isPinned = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private NewsStatus status = NewsStatus.DRAFT;

    @Column(name = "publish_at")
    private Timestamp publishAt;

    @Column(name = "views")
    @Builder.Default
    private Long views = 0L;
}
