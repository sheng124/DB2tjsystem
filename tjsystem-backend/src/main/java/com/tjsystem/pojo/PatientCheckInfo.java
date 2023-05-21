package com.tjsystem.pojo;

import java.util.List;

public class PatientCheckInfo {
    private String patient_id;
    private String patient_name;
    private List<CheckInfo> checkInfos;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public List<CheckInfo> getCheckInfos() {
        return checkInfos;
    }

    public void setCheckInfos(List<CheckInfo> checkInfos) {
        this.checkInfos = checkInfos;
    }
}
