package com.zup.proposta.config.validator;

public class CustomNotFoundException extends CommonException {
    public CustomNotFoundException(String field, String msg) {
        super(field, msg);
    }
}
