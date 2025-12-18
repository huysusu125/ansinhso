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
@Table(name = "question_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class QuestionOption extends BaseEntity {

    @Column(name = "question_id")
    private String questionId;

    @Column(name = "option_id")
    private String optionId;

    @Column(name = "content")
    private String content;

    @Column(name = "is_correct")
    private Boolean isCorrect = false;
}
