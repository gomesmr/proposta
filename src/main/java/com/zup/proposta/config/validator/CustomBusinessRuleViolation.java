package com.zup.proposta.config.validator;

public class CustomBusinessRuleViolation extends CommonException {
    public CustomBusinessRuleViolation(String field, String msg) {
        super(field, msg);
    }
}
