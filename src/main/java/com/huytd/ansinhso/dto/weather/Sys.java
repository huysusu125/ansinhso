package com.huytd.ansinhso.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sys {
    private Integer type;
    private Integer id;
    private String country;
    private Long sunrise;
    private Long sunset;
}
