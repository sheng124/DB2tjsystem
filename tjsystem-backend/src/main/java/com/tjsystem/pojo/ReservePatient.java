package com.tjsystem.pojo;

public class ReservePatient {
    private String Patient_id;
    private String Patient_name;
    private String Patient_gender;
    private String Patient_phone;
    private String Reserve_date;

    public String getId()
    {
        return Patient_id;
    }
    public String getName()
    {
        return Patient_name;
    }
    public String getGender()
    {
        return Patient_gender;
    }
    public String getPhone()
    {
        return Patient_phone;
    }
    public String getDate()
    {
        return Reserve_date;
    }
    public void setReserve_id(String patient_id)
    {
        this.Patient_id = patient_id;
    }
    public void setPatient_name(String patientName)
    {
        this.Patient_name = patientName;
    }
    public void setPatient_gender(String patientGender)
    {
        this.Patient_gender = patientGender;
    }
    public void setPatient_phone(String patientPhone)
    {
        this.Patient_phone = patientPhone;
    }
    public void setReserve_date(String reserveDate)
    {
        this.Reserve_date = reserveDate;
    }



}
