package com.github.msafonov.corporate.bot.controllers;

import com.github.msafonov.corporate.bot.entities.BaseEntity;

import javax.persistence.*;

public class EntityController {

    private EntityManager entityManager;

    public EntityController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(BaseEntity entity) {
        entityManager.getTransaction().begin();
        //пробуем выполнить транзакцию
        try {
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            //если ошибка, то отменяем транзакцию
            entityManager.getTransaction().rollback();
        }
    }

    public BaseEntity read(Class<? extends BaseEntity> entityClass, int id) {
        if (entityClass == BaseEntity.class)
            return null;
        BaseEntity o = entityManager.find(entityClass, id);
        if (o == null)
            return null;
        entityManager.detach(o);
        return o;
    }

    public void update(BaseEntity entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    public void delete(BaseEntity entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }
}
