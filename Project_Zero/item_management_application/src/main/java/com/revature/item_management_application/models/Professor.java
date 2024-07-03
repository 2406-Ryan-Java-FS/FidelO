package com.revature.item_management_application.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// entities in JPA are POJOs representing data that can be persisted in the database
// the table name must match the spelling in the database
@Entity
@Table(name = "professors", schema = "professor_trainer_schema")
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Integer id;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    
    public Professor() {
    }

    public Professor(Integer id, String name, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Professor [id = " + id + ", username = " + username + ", password = " + password + "]";
    }
}