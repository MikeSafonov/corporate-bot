package com.github.msafonov.corporate.bot.entities;

import com.github.msafonov.corporate.bot.TypeAction;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "typeOfAction")
    private List<Action> actions;

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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
