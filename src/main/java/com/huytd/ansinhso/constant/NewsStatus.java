package com.huytd.ansinhso.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum NewsStatus {

    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    ;

    private final String value;
}