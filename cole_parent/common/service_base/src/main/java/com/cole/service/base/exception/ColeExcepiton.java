package com.cole.service.base.exception;

import com.cole.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author: Cxl
 * @since: 2020-09-10
 **/
@Data
public class ColeExcepiton  extends  RuntimeException{

    private Integer code;

    public ColeExcepiton(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ColeExcepiton(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "ColeExcepiton{" +
                "code=" + code +
                ",message = " + this.getMessage() +
                '}';
    }
}
