package com.huytd.ansinhso.dto.zalo;

import lombok.Data;

@Data
public class ZaloPhoneNumberResponse {
    private int error;
    private String message;
    private PhoneNumberData data;
}
