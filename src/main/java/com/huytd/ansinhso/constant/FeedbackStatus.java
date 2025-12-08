package com.huytd.ansinhso.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackStatus {
    PENDING("Chờ xử lý"),
    IN_PROGRESS("Đang xử lý"),
    RESOLVED("Đã giải quyết"),
    REJECTED("Từ chối");

    private final String value;
}
