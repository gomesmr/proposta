package com.zup.proposta.config.validator;

import java.time.LocalDateTime;

public class CommonException extends RuntimeException {
    private String field;
    private String msg;
    private LocalDateTime throwTime = LocalDateTime.now();

    public CommonException(String field, String msg) {
        this.field = field;
        this.msg = msg;
        this.throwTime = throwTime;
    }

    public String getField() {
        return field;
    }

    public String getMsg() {
        return msg;
    }
}
