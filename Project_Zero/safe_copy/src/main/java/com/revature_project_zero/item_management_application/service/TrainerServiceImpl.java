package com.revature_project_zero.item_management_application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature_project_zero.item_management_application.entity.Trainer;
import com.revature_project_zero.item_management_application.exceptions.InvalidTrainerException;
import com.revature_project_zero.item_management_application.repository.TrainerRepository;

// for abstraction purposes, all comments will refer to the repository instead of the database
@Service
public class TrainerServiceImpl implements TrainerService{
    private TrainerRepository trainerRepository;

    // implicit autowired - sole constructor
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    /**
     * retrieves all trainer entities from the repository.
     * @return a list of all trainer entities.
     */
    @Override
    public List<Trainer> findAllTrainers() {
        return trainerRepository.findAll();
    }

    /**
     * retrieves a trainer entitiy from the repository
     * @param trainerId the id of the trainer we want to find
     * @return a trainer object if it exists in the database
     * else it returns null
     */
    @Override
    public Trainer findTrainerById(int trainerId) {
        Optional<Trainer> theTrainer = trainerRepository.findById(trainerId);
        if(theTrainer.isPresent())
        {
            return theTrainer.get();
        }
        else
        {
            throw new RuntimeException("Trainer not found with id: " + trainerId);
        }
        
    }

    /**
     * adds a new trainer to the repository
     * @param theTrainer the trainer data we want to add
     * @return the saved trainer object if the addition is successful
     * or exceptions if the trainer data is invalid
     */
    @Override
    public Trainer addNewTrainer(Trainer theTrainer) {
        // handle situations where the user sets an id
        if(theTrainer.getId() != null)
        {
            theTrainer.setId(null);
        }

        // reject the save if the email already exists
        if(trainerRepository.findByEmail(theTrainer.getEmail()).isPresent())
        {
            throw new InvalidTrainerException("Trainer email, " + theTrainer.getEmail() + ", already exists in the database.");
        }

        // reject the save if the first name and email fields are empty
        if(theTrainer.getFirstName().isEmpty() || theTrainer.getEmail().isEmpty())
        {
            throw new InvalidTrainerException("Invalid account details. Please try again.");
        }

        Trainer dbTrainer =  trainerRepository.save(theTrainer);
        return dbTrainer;
    }

    /**
     * adds a new trainer to the repository
     * @param theTrainer the trainer data we want to update
     * @return the updated trainer object if the update is successful
     * or exceptions if the trainer data is invalid
     */
    @Override
    public Trainer updateTrainer(Trainer theTrainer, int theId) {
        // check if the trainer to be updated exists
        Optional<Trainer> dbTrainer = trainerRepository.findById(theId);
        if(!dbTrainer.isPresent())
        {
            throw new InvalidTrainerException("Cannot update. There is no trainer by the id: " + theId);
        }

        // reject the save if the first name and email fields are empty
        if(theTrainer.getFirstName().isEmpty() || theTrainer.getEmail().isEmpty())
        {
            throw new InvalidTrainerException("Invalid account details. Please try again.");
        }

        // else - save since it exists
        Trainer updatedEmployee = trainerRepository.save(theTrainer);
        return updatedEmployee;
    }

    /**
     * deletes a trainer from the repository
     * @param theId the id of the trainer data that we want to delete
     * @return the number of affected table rows
     */
    @Override
    public int deleteTrainer(int theId) {
        // if the account doesn't exist
        if(trainerRepository.findById(theId).isPresent())
        {
            trainerRepository.deleteById(theId);
            return 1;
        }
        else
        {
            return 0;
        }
    }
}