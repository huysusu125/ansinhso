package com.huytd.ansinhso.dto.response;

import com.huytd.ansinhso.constant.FeedbackStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitizenFeedbackResponse {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String title;
    private String content;
    private FeedbackStatus status;
    private List<String> attachmentUrls;
}
