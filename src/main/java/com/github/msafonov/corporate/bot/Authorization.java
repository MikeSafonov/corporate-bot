package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import com.github.msafonov.corporate.bot.entities.AuthorizationCode;
import com.github.msafonov.corporate.bot.entities.Employee;

public class Authorization {
    private final EntityController entityController;

    public Authorization(EntityController entityController) {
        this.entityController = entityController;
    }

    public void register(Employee employee, AuthorizationCode code) {
        entityController.save(employee);
        code.setEmployee(employee);
        entityController.update(code);
    }

    public boolean isRegistered(Employee employee) {
        //Возможна доп логика
        return employee != null;
    }

    public boolean isAdministrator(String chat_id) {
        ///Будет в AdminProperties
        return true;
    }

    public boolean isFreeCode(AuthorizationCode authorizationCode) {
        return authorizationCode != null && authorizationCode.getUserId() == null;
    }
}
