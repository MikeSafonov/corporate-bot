package com.github.msafonov.corporate.bot.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "message_text")
    @Type(type = "text")
    private String messageText;
    @Column(name = "date_of_message")
    @Type(type = "timestamp")
    private LocalDateTime dateOfMessage;
    @ManyToOne()
    @JoinColumn(name = "type_id")
    private TypeOfMessage typeOfMessage;

    public Message() {
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

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getDateOfMessage() {
        return dateOfMessage;
    }

    public void setDateOfMessage(LocalDateTime dateOfMessage) {
        this.dateOfMessage = dateOfMessage;
    }

    public TypeOfMessage getTypeOfMessage() {
        return typeOfMessage;
    }

    public void setTypeOfMessage(TypeOfMessage typeOfMessage) {
        this.typeOfMessage = typeOfMessage;
    }
}
