package com.revature.item_management_application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.item_management_application.exceptions.InvalidTrainerException;
import com.revature.item_management_application.models.Trainer;
import com.revature.item_management_application.repositories.ProfessorRepository;
import com.revature.item_management_application.repositories.TrainerRepository;

// for abstraction purposes, all comments will refer to the repository instead of the database
@Service
public class TrainerServiceImpl implements TrainerService {
    private TrainerRepository trainerRepository;
    private ProfessorRepository professorRepository;

    // implicit autowired - sole constructor
    public TrainerServiceImpl(TrainerRepository trainerRepository, ProfessorRepository professorRepository) {
        this.trainerRepository = trainerRepository;
        this.professorRepository = professorRepository;
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
        
        // reject if any of the fields are null
        if(theTrainer.getFirstName() == null || theTrainer.getLastName() == null || theTrainer.getEmail() == null || theTrainer.getRegionalProfessorId() == null)
        {
            throw new InvalidTrainerException("Request missing required fields. Please try again.");
        }

        // reject if either the first name or email fields are empty
        if(theTrainer.getFirstName().trim().isEmpty() || theTrainer.getEmail().trim().isEmpty())
        {
            throw new InvalidTrainerException("Blank fields provided. Please try again.");
        }

        // reject the save if regional professor fields does not exist in the Professor table
        if(!professorRepository.findById(theTrainer.getRegionalProfessorId()).isPresent())
        {
            throw new InvalidTrainerException("Please add a valid regional Id.");
        }

        // reject the save if the email already exists
        if(trainerRepository.findByEmail(theTrainer.getEmail()).isPresent())
        {
            throw new InvalidTrainerException("Trainer email, " + theTrainer.getEmail() + ", already exists in the database.");
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
    public Trainer updateTrainerById(Trainer theTrainer, int theId) {
        // check if the trainer already exists in the database
        Optional<Trainer> dbTrainer = trainerRepository.findById(theId);
        if(!dbTrainer.isPresent())
        {
            throw new InvalidTrainerException("Cannot update. There is no trainer by the id: " + theId);
        }

        // reject if any of the fields are null
        if(theTrainer.getFirstName() == null || theTrainer.getLastName() == null || theTrainer.getEmail() == null || theTrainer.getRegionalProfessorId() == null)
        {
            throw new InvalidTrainerException("Request missing required fields. Please try again.");
        }

        // reject if either the first name or email fields are empty
        if(theTrainer.getFirstName().trim().isEmpty() || theTrainer.getEmail().trim().isEmpty())
        {
            throw new InvalidTrainerException("Blank fields provided. Please try again.");
        }

        // reject the save if regional professor fields does not exist in the Professor table
        if(!professorRepository.findById(theTrainer.getRegionalProfessorId()).isPresent())
        {
            throw new InvalidTrainerException("Please add a valid regional Id.");
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
    public int deleteTrainerById(int theId) {
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

    /**
     * this method is used to get a list of trainers associated with a specific regional professor.
     * @param regProfId the ID of the regional professor whose associated trainers are to be retrieved.
     * @return a list of Trainer objects associated with the given regional professor.
     */
    @Override
    public List<Trainer> getTrainersByRegionalProfessorId(Integer regProfId) {
        return trainerRepository.findByRegionalProfessorId(regProfId);
    }
}
