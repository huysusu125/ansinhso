package com.huytd.ansinhso.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wind {
    private Double speed;
    private Integer deg;
    private Double gust;
}
