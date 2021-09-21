package com.zup.proposta.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistIdValidator.class})
@Target({ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ExistsId {
    String message() default "Não existe registro com esse campo de identificação";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    Class<?> domainClass();
    String fieldName();
}