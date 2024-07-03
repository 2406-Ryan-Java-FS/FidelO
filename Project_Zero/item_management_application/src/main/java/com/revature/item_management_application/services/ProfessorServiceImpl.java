package com.revature.item_management_application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.item_management_application.exceptions.DuplicateAccountException;
import com.revature.item_management_application.exceptions.InvalidAccountException;
import com.revature.item_management_application.models.Professor;
import com.revature.item_management_application.repositories.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService{
    private ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor registerProfessorAccount(Professor account) {
        // that none of the details are null or empty
        if (account.getUsername() == null || account.getUsername().trim().isEmpty() || 
        account.getPassword()== null || account.getPassword().trim().isEmpty()) {
            throw new InvalidAccountException("Invalid account details. Please try again.");
        }

        // Check if the account already exists based on username
        if (professorRepository.findByUsername(account.getUsername()).isPresent()) {
            // Throw an exception if the username is already taken
            throw new DuplicateAccountException("Username already exists in the database. Please try again.");
        }

        // Save the new account in the database
        return professorRepository.save(account);
    }

    @Override
    public String logIntoProfessorAccount(Professor account) {
        Optional<Professor> existingAccount = professorRepository.findByUsername(account.getUsername());

        if (existingAccount.isPresent() && existingAccount.get().getPassword().equals(account.getPassword())) {
            return "Logged in Successfully.";
        } else {
            throw new InvalidAccountException("Login attempt failed. Invalid username or password. Please try again.");
        }
    }
}