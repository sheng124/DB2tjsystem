package com.tjsystem.pojo;

public class ReservePatient {
    private int Reserve_id;
    private String Patient_name;
    private String Patient_gender;
    private String Patient_phone;
    private String Reserve_date;

    public int getId()
    {
        return Reserve_id;
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
    public void setReserve_id(int reserveId)
    {
        this.Reserve_id = reserveId;
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
