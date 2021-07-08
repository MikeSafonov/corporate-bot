package com.github.msafonov.corporate.bot;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class Queries {
    private final EntityManager entityManager;
    Queries(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    public List<String> uniqueCodeQuery(String code){
        Query q=entityManager.createQuery("select code from authorization_code where code=:code");
        q.setParameter("code",code);

   return q.getResultList();
    }
}
