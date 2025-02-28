package com.revature.item_management_application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.item_management_application.exceptions.DuplicateAccountException;
import com.revature.item_management_application.exceptions.InvalidAccountException;
import com.revature.item_management_application.models.Professor;
import com.revature.item_management_application.services.ProfessorService;

@RestController
@RequestMapping("/admin_console")
public class SessionRestController {
    private ProfessorService professorService;

    // implicit autowired - sole constructor
    public SessionRestController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
    * handler to register a new professor account.
    * it handles the POST request at the "/register" endpoint.
    * @param account a Professor object containing the account details to be registered.
    * @return a ResponseEntity that contains the registered Professor object if successful,
    *         or an error message if a DuplicateAccountException or InvalidAccountException is thrown.
    */
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody Professor account) {
        try 
        {
            Professor response = professorService.registerProfessorAccount(account);
            // return the account in the response body if all goes well 
            return ResponseEntity.ok(response);
        } 
        catch (DuplicateAccountException e) 
        {
            // return error code 409 if a duplicate account exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } 
        catch (InvalidAccountException e)
        {
            // return error code 400 for all other errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
    * handler to log in a professor account.
    * it handles the POST request at the "/login" endpoint.
    * @param account a Professor object containing the account details for login.
    * @return a ResponseEntity that contains a success message if login is successful,
    *         or an error message if an InvalidAccountException is thrown.
    */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Professor account) 
    {
        try
        {
            String response = professorService.logIntoProfessorAccount(account);
            return ResponseEntity.ok(response);
        } 
        catch (InvalidAccountException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}