package com.raaflahar.maybank.util;

import org.springframework.data.domain.Page;

import com.raaflahar.maybank.payload.response.CommonResponse;
import com.raaflahar.maybank.payload.response.PaginationResponse;

public class ResponseUtil {

    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.success(data);
    }

    public static <T> CommonResponse<PaginationResponse<T>> paged(Page<T> page) {
        PaginationResponse<T> pagination = PaginationResponse.<T>builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber() + 1)
                .pageSize(page.getSize())
                .build();

        return CommonResponse.success(pagination);
    }
}