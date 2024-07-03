package com.revature.item_management_application.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.item_management_application.models.Trainer;

// entity type: Trainer
// primary key: Integer
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{
    Optional<Trainer> findByEmail(String email);

    List<Trainer> findByRegionalProfessorId(Integer regProfId);
}
