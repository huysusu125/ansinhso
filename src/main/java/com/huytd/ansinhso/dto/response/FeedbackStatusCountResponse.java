package com.huytd.ansinhso.dto.response;

import com.huytd.ansinhso.constant.FeedbackStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackStatusCountResponse {
    private FeedbackStatus status;
    private Long total;
}
