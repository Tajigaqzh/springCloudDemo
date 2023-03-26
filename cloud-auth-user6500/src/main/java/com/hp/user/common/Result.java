package com.hp.user.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tony
 * @version 1.0
 * @date 2023-03-24 22:01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result {
    /** 返回状态码*/
    private int code;

    /**返回描述信息*/
    private String msg;

    /**返回token信息 令牌*/
    private String token;
}
