package com.tjsystem.pojo;

public class QueueInfo {
    private String Patient_id;
    private String current_department_id;

    public String getPatient_id() {
        return Patient_id;
    }

    public String getCurrent_department_id() {
        return current_department_id;
    }

    public void setCurrent_department_id(String current_department_id) {
        this.current_department_id = current_department_id;
    }

    public void setPatient_id(String patient_id) {
        Patient_id = patient_id;
    }
}
