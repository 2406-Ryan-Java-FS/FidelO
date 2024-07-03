package com.revature_project_zero.item_management_application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.revature_project_zero.item_management_application.entity.Trainer;

// entity type: Trainer
// primary key: Integer
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

    Optional<Trainer> findByEmail(String email);
}