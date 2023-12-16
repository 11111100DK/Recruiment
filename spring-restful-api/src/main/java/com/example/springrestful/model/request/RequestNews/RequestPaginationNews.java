package com.example.springrestful.model.request.RequestNews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPaginationNews {
    private int limit;
    private int offset;
}
