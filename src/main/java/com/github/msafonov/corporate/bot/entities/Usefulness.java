package com.github.msafonov.corporate.bot.entities;

import javax.persistence.*;

@Entity
@Table(name = "usefulness")
public class Usefulness extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "answer_yes")
    private int answerYes;
    @Column(name = "answer_no")
    private int answerNo;

    public Usefulness() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswerYes() {
        return answerYes;
    }

    public void setAnswerYes(int answerYes) {
        this.answerYes = answerYes;
    }

    public int getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(int answerNo) {
        this.answerNo = answerNo;
    }
}
