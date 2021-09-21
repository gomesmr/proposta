package com.zup.proposta.config.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String domainAttribute;
    private Class<?> klazz;
    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(ExistsId eid) {
        domainAttribute = eid.fieldName();
        klazz = eid.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from "+ klazz.getName() +" t where t.id=:value");
        query.setParameter("value", value);
        return query.getResultList().size() > 0;
    }
}

