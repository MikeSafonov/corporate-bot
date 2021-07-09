package com.github.msafonov.corporate.bot.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "authorization_code")
public class AuthorizationCode extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code", length = 6, unique = true)
    private String code;
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "user_id", length = 15)
    private String userId;
    @Column(name = "date_of_generation")
    private LocalDateTime dateOfGeneration;

    public AuthorizationCode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateOfGeneration() {
        return dateOfGeneration;
    }

    public void setDateOfGeneration(LocalDateTime dateOfGeneration) {
        this.dateOfGeneration = dateOfGeneration;
    }
}
