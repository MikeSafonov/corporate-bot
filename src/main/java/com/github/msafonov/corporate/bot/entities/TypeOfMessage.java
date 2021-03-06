package com.github.msafonov.corporate.bot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_of_message")
public class TypeOfMessage extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type_message", length = 30)
    private String typeMessage;
    @OneToMany(mappedBy = "typeOfMessage")
    private List<Message> messages;

    public TypeOfMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
