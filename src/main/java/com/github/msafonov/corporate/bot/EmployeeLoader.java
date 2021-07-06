package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import com.github.msafonov.corporate.bot.entities.Action;
import com.github.msafonov.corporate.bot.entities.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class EmployeeLoader {
    private EntityController entityController;

    public EmployeeLoader(EntityController entityController) {
        this.entityController = entityController;
    }

    public Employee getEmployee(String chat_id) {
        var equals = new HashMap<String, Object>();
        equals.put("userId", chat_id);
        var c = entityController.getWhereEqual(Employee.class, equals);
        return entityController.querySingle(c);
    }

    public Action getLastAction(Employee employee) {
        var list = employee.getActions();
        if(list == null || list.size() == 0)
            return null;
        return Collections.max(list, Comparator.comparing(Action::getDateOfAction));
    }
}
