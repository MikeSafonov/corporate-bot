package com.github.msafonov.corporate.bot.controllers;

import com.github.msafonov.corporate.bot.entities.AuthorizationCode;
import com.github.msafonov.corporate.bot.entities.BaseEntity;
import org.glassfish.grizzly.compression.lzma.impl.Base;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityController {

    private final EntityManager entityManager;

    public EntityController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(BaseEntity entity) {
        entityManager.getTransaction().begin();
        //пробуем выполнить транзакцию
        try {
            entityManager.persist(entity);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public <T extends BaseEntity> T read(Class<T> entityClass, int id) {
        if (entityClass == BaseEntity.class)
            return null;
        T o = entityManager.find(entityClass, id);
        if (o == null)
            return null;
        entityManager.detach(o);
        return o;
    }

    public void update(BaseEntity entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(entity);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void delete(BaseEntity entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(entity);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public <T extends BaseEntity> List<T> query(CriteriaQuery<T> criteria) {
        List<T> resultList = null;
        entityManager.getTransaction().begin();
        try {
            resultList = entityManager.createQuery(criteria).getResultList();
            for (var o : resultList)
                entityManager.detach(o);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return resultList;
    }

    public <T extends BaseEntity> T querySingle(CriteriaQuery<T> criteria) {
        var res = query(criteria);
        return res == null || res.size() == 0 ? null : res.get(0);
    }

    public <T extends BaseEntity> CriteriaQuery<T> getWhereEqual(Class<T> entityClass, Map<String, Object> equals) {
        if (equals == null || equals.size() == 0)
            return null;
        CriteriaBuilder cb = getCriteriaBuilder();

        List<Predicate> list = new ArrayList<>();

        CriteriaQuery<T> tCriteriaQuery = cb.createQuery(entityClass);
        Root<T> tRoot = tCriteriaQuery.from(entityClass);
        for (Map.Entry<String, Object> entry : equals.entrySet()) {
            // get field
            String field = entry.getKey();
            // get value
            Object value = entry.getValue();
            list.add(cb.equal(tRoot.get(field), value));
        }
        Predicate[] arr = list.toArray(new Predicate[0]);
        return tCriteriaQuery.select(tRoot).where(cb.and(arr));
    }

    public <T extends BaseEntity> CriteriaQuery<T> getAll(Class<T> entityClass) {
        var cb = getCriteriaBuilder();
        CriteriaQuery<T> tCriteriaQuery = cb.createQuery(entityClass);
        Root<T> tRoot = tCriteriaQuery.from(entityClass);
        return tCriteriaQuery.select(tRoot);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }
}
