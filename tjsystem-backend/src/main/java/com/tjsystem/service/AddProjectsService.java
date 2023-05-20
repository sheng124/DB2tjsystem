package com.tjsystem.service;

import com.tjsystem.pojo.CheckInfo;

import java.util.List;

public interface AddProjectsService {
    List<CheckInfo> getProjectsToAdd(String patient_id,String doctor_id);

    boolean addProject(String patient_id,String project_name);
}
