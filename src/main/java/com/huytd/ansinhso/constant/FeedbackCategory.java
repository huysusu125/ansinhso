package com.huytd.ansinhso.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedbackCategory {

    ILLEGAL_ACTIVITY("Vi phạm pháp luật"),
    TRAFFIC_SAFETY("Vi phạm an toàn giao thông"),
    ENVIRONMENTAL_HYGIENE("Vi phạm vệ sinh môi trường"),
//    INFRASTRUCTURE("Hạ tầng cơ sở"),
//    EDUCATION("Giáo dục"),
//    HEALTHCARE("Y tế"),
//    ENVIRONMENT("Môi trường"),
//    TRAFFIC("Giao thông"),
//    SECURITY("An ninh trật tự"),
//    ADMINISTRATION("Hành chính công"),
    OTHER("Khác"),
    ;

    private final String value;
}
