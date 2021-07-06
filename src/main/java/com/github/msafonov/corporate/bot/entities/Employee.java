package com.github.msafonov.corporate.bot.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id", length = 15, unique = true)
    private String userId;
    @Column(name = "fio", length = 200)
    private String fio;
    @Column(name = "phone", length = 13)
    private String phone;
    @Column(name = "email", length = 254)
    private String email;
    @OneToMany(mappedBy = "employee")
    private List<Message> messages;
    @OneToMany(mappedBy = "employee")
    private List<Action> actions;
    @OneToMany(mappedBy = "employee")
    private List<AuthorizationCode> authorizationCodes;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<AuthorizationCode> getAuthorizationCodes() {
        return authorizationCodes;
    }

    public void setAuthorizationCodes(List<AuthorizationCode> authorizationCodes) {
        this.authorizationCodes = authorizationCodes;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
