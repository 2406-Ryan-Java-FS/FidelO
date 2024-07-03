package com.revature_project_zero.item_management_application.controller;

import org.springframework.web.bind.annotation.RestController;

import com.revature_project_zero.item_management_application.entity.Trainer;
import com.revature_project_zero.item_management_application.exceptions.InvalidTrainerException;
import com.revature_project_zero.item_management_application.service.TrainerService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/pokemon_wiki")
public class TrainerRestController {
    private TrainerService trainerService;

    // implicit autowired - sole constructor
    public TrainerRestController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    /**
     * handler to get all pokemon trainers.
     * @return a list of trainer objects wrapped in a ResponseEntity as the response body to the HTTP caller.
     */
    @GetMapping("trainers")
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        List<Trainer> allTrainers = trainerService.findAllTrainers();
        return ResponseEntity.ok(allTrainers);
    }

    /**
     * handler to get a trainer by id
     * @param trainerId the id of the trainer to retrieve
     * @return a response entity containing the trainer if found or a 404 error message if not found
     */
    @GetMapping("trainers/{trainerId}")
    public ResponseEntity<?> getTrainerById(@PathVariable int trainerId) {
        try {
            Trainer theTrainer = trainerService.findTrainerById(trainerId);
            return ResponseEntity.ok(theTrainer);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * handler to add a new trainer to the data base
     * @param theTrainer the trainer data that we will add to the database
     * @return  a response entity containing the added trainer or a 400 error message
     */
    @PostMapping("/trainers")
    public ResponseEntity<?> addNewTrainer(@RequestBody Trainer theTrainer) {
        try 
        {
            Trainer newTrainer = trainerService.addNewTrainer(theTrainer);
            return ResponseEntity.ok(newTrainer);
        } catch (InvalidTrainerException e) 
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * handler to update any information for an existing trainer in the database
     * @param trainerId the id of the trainer that we want to update in the database
     * @param theTrainer the trainer data that we will update in the database
     * @return a response entity containing the updated trainer or a 404 error message
     */
    @PutMapping("/trainers/{trainerId}")
    public ResponseEntity<?> updateTrainer(@PathVariable int trainerId, @RequestBody Trainer theTrainer) {
        try 
        {
            // in case the user does not put an ID in the request body - we can use the id from the endpoint request
            // in case the user puts in the wrong id as well - we default to the endpoint id
            theTrainer.setId(trainerId);
            Trainer updatedTrainer = trainerService.updateTrainer(theTrainer, trainerId);
            return ResponseEntity.ok(updatedTrainer);
        } catch (InvalidTrainerException e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } 
    }

    /**
     * handler to delete trainer data
     * @param trainerId the id of the trainer that we want to delete in the database
     * @return a response entity containing 1 if a trainer is deleted or 0 if the trainer is not deleted
     */
    @DeleteMapping("/trainers/{trainerId}")
    public ResponseEntity<?> deleteTrainer(@PathVariable int trainerId)
    {
        int rowsAffected = trainerService.deleteTrainer(trainerId);

        if(rowsAffected == 0)
        {
            return ResponseEntity.ok(rowsAffected + " trainers deleted - no such trainer exists.");
        }
        else
        {
            return ResponseEntity.ok(rowsAffected + " trainer deleted");
        }
    }
}