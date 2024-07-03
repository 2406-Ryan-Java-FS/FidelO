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
@Table(name = "trainers", schema = "professor_trainer_schema")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "regional_professor_id")
    private Integer regionalProfessorId;

    public Trainer() {
    }

    public Trainer(Integer id, String firstName, String lastName, String email, Integer regionalProfessorId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.regionalProfessorId = regionalProfessorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRegionalProfessorId() {
        return regionalProfessorId;
    }

    public void setRegionalProfessorId(Integer regionalProfessorId) {
        this.regionalProfessorId = regionalProfessorId;
    }

    @Override
    public String toString() {
        return "Trainer [id = " + id + ", firstName = " + firstName + ", lastName = " + lastName + ", email = " + email
                + ", regionalProfessorId = " + regionalProfessorId + "]";
    }
}