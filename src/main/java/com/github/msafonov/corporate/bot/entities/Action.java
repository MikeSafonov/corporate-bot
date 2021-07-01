package com.github.msafonov.corporate.bot.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "actions")
public class Action extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "date_of_action")
    @Type(type = "timestamp")
    private LocalDateTime dateOfAction;
    @ManyToOne()
    @JoinColumn(name = "type_id")
    private TypeOfAction typeOfAction;

    public Action() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDateTime getDateOfAction() {
        return dateOfAction;
    }

    public void setDateOfAction(LocalDateTime dateOfAction) {
        this.dateOfAction = dateOfAction;
    }

    public TypeOfAction getTypeOfAction() {
        return typeOfAction;
    }

    public void setTypeOfAction(TypeOfAction typeOfAction) {
        this.typeOfAction = typeOfAction;
    }
}
