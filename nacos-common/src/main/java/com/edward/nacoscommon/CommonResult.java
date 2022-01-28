package com.edward.nacoscommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> CommonResult<T> success (T data) {
        return new CommonResult<>(200, "SUCCESS", data);
    }

    public static <T> CommonResult<T> fail (Integer code, String msg) {
        return new CommonResult<>(code, msg, null);
    }
}
