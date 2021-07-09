package com.github.msafonov.corporate.bot.entities;

import com.github.msafonov.corporate.bot.TypeAction;

import javax.persistence.*;

@Entity
@Table(name = "type_of_actions")
public class TypeOfAction extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_action", length = 30)
    @Enumerated(value = EnumType.STRING)
    private TypeAction typeAction;

    public TypeOfAction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeAction getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(TypeAction typeAction) {
        this.typeAction = typeAction;
    }
}
