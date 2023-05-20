package com.tjsystem.pojo;

public class UploadResultInfo {

    private String patientID;
    private String indicatorName;
    private String checkResult;

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    @Override
    public String toString() {
        return "UploadResult{" +
                "patientID='" + patientID + '\'' +
                ", indicatorName='" + indicatorName + '\'' +
                ", checkResult='" + checkResult + '\'' +
                '}';
    }
}
