package com.revature_project_zero.item_management_application.service;

import java.util.List;

import com.revature_project_zero.item_management_application.entity.Trainer;

public interface TrainerService {
    List<Trainer> findAllTrainers();
    Trainer findTrainerById(int trainerId);
    Trainer addNewTrainer(Trainer theTrainer);
    Trainer updateTrainer(Trainer theTrainer, int theId);
    int deleteTrainer (int theId);
}