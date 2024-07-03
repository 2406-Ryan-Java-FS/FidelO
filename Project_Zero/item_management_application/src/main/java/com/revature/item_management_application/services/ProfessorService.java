package com.revature.item_management_application.services;

import com.revature.item_management_application.models.Professor;

public interface ProfessorService {
    public Professor registerProfessorAccount(Professor account);
    public String logIntoProfessorAccount(Professor account);
}
