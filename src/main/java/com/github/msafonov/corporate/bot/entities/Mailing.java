package com.github.msafonov.corporate.bot.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mailing")
public class Mailing extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id", length = 15)
    private String userId;
    @Column(name = "mail_text")
    @Type(type = "text")
    private String mailText;
    @Column(name = "date_of_mailing")
    @Type(type = "timestamp")
    private LocalDateTime dateOfMailing;

    public Mailing() {
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

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public LocalDateTime getDateOfMailing() {
        return dateOfMailing;
    }

    public void setDateOfMailing(LocalDateTime dateOfMailing) {
        this.dateOfMailing = dateOfMailing;
    }
}
