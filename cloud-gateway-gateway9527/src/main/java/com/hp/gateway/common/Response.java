package com.hp.gateway.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-21 06:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private Integer code;
    private String message;
}
