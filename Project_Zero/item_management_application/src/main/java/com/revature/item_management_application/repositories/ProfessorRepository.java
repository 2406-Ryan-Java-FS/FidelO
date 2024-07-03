package com.revature.item_management_application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.item_management_application.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

    Optional<Professor> findByUsername(String username);

}
