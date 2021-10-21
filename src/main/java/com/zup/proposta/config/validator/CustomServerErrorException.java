package com.zup.proposta.config.validator;

public class CustomServerErrorException extends CommonException {
    public CustomServerErrorException(String field, String msg) {
        super(field, msg);
    }
}
