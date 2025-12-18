package com.huytd.ansinhso.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopUserDTO {
    private String phoneNumber;
    private Integer bestScore;
    private Timestamp lastCompletedAt;
}
