package com.example.auth.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "TaskUser")
public class TaskUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String email;

    public TaskUser(int id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public TaskUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
