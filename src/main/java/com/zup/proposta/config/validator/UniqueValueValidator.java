package com.zup.proposta.config.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klazz;
    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(UniqueValue uv) {
        domainAttribute = uv.fieldName();
        klazz = uv.domainClass();
    }


    /**
     * Critério é o valor contido no atributo da classe de domínio
     */
    @Override
    public boolean isValid(Object criterio, ConstraintValidatorContext contexto) {
        Query query = manager.createQuery("select 1 from "+
                klazz.getName()+" where "+domainAttribute+"=:criterio");
        query.setParameter("criterio", criterio);
        List<?> list = query.getResultList();
        //Lança Illegal State Exception | Nunca pode ter mais que um no sistema
        //Assert.state(list.size() == 0, "Foi encontrado mais de um "+klazz+" com ");

        return list.isEmpty();
    }


}
