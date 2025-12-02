package com.huytd.ansinhso.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionOption extends BaseEntity {

    @Column(name = "question_id")
    private String questionId;

    @Column(nullable = false)
    private String optionId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isCorrect = false;
}
