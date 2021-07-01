package com.github.msafonov.corporate.bot.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "types_of_actions")
public class TypeOfAction extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_action", length = 30)
    private String typeAction;
    @OneToMany(mappedBy = "typeOfAction")
    private Set<Action> actions;

    public TypeOfAction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
}
