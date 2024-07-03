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

    /**
    * this method is used to register a new professor account.
    * it first checks if the account details are valid and not empty.
    * if the username already exists in the database, it throws a DuplicateAccountException.
    * if the account details are valid and the username is unique, it saves the new account in the database.
    * @param account a Professor object containing the account details to be registered.
    * @return the registered Professor object if successful.
    * @throws InvalidAccountException if the account details are invalid.
    * @throws DuplicateAccountException if the username already exists in the database.
    */
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

    /**
    * this method is used to log in a professor account.
    * it checks if the account exists in the database and if the password matches the one in the database.
    * if the account exists and the password is correct, it returns a success message.
    * if the account does not exist or the password is incorrect, it throws an InvalidAccountException.
    * @param account a Professor object containing the account details for login.
    * @return a success message if login is successful.
    * @throws InvalidAccountException if the account does not exist or the password is incorrect.
 */
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