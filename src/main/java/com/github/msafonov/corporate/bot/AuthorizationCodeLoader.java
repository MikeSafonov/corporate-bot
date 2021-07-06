package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import com.github.msafonov.corporate.bot.entities.AuthorizationCode;

import java.util.HashMap;

public class AuthorizationCodeLoader {
    private EntityController entityController;

    public AuthorizationCodeLoader(EntityController entityController) {
        this.entityController = entityController;
    }

    public AuthorizationCode getAuthorizationCode(String code) {
        if (code.length() != 6)
            return null;
        var equals = new HashMap<String, Object>();
        equals.put("code", code);
        var c = entityController.getWhereEqual(AuthorizationCode.class, equals);
        return entityController.querySingle(c);

    }
}
