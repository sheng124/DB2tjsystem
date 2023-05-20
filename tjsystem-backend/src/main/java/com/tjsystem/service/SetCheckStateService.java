package com.tjsystem.service;

public interface SetCheckStateService {
    boolean setCheckState(String patient_id, String indicator_name, String doctor_id);
}
