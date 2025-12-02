package com.huytd.ansinhso.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Topic extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
