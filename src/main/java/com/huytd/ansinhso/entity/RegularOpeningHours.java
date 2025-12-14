package com.huytd.ansinhso.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegularOpeningHours {
    private List<OpeningHour> weekdayDescriptions;
}
