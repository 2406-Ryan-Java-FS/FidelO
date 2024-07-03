package com.revature.item_management_application.services;

import java.util.List;

import com.revature.item_management_application.models.Trainer;

public interface TrainerService {
    List<Trainer> findAllTrainers();
    Trainer findTrainerById(int trainerId);
    Trainer addNewTrainer(Trainer theTrainer);
    Trainer updateTrainerById(Trainer theTrainer, int theId);
    int deleteTrainerById (int theId);
    List<Trainer> getTrainersByRegionalProfessorId(Integer regProfId);
}
