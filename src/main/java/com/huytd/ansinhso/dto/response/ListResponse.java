package com.huytd.ansinhso.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ListResponse<T> {
    private Long totalItem;
    private List<T> items;

    public static <T> ListResponse<T> of(List<T> items, Long totalItem) {
        return ListResponse.<T>builder()
                .totalItem(totalItem)
                .items(items)
                .build();
    }
}
