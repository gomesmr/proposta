package com.zup.proposta.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
//@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    /**
     * As informações desse bloco são obrigatórias
     * Mensagem padrão caso a validação dê erro
     * @return String Mensagem que será exibida sobre o erro
     */
    String message() default "Este campo precisa ser único";
    /**
     * Define um grupo específico (quase ninguém usa)
     * Não possui retorno padrão
     * @return
     */
    Class<?>[] groups() default { };
    /**
     * mais informações
     * @return
     */
    Class<? extends Payload>[] payload() default { };

    Class<?> domainClass();
    String fieldName();

}
