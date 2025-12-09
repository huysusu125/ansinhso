package com.huytd.ansinhso.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "feedback_attachment")
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class FeedbackAttachment extends BaseEntity {

    @Column(name = "feedback_id")
    private String feedbackId;

    @Column(name = "url")
    private String url;

}
