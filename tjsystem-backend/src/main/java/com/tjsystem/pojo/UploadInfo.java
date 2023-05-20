package com.tjsystem.pojo;

public class UploadInfo {
    private String patientID;
    private String patientName;
    private String indicatorName;
    private String projectName;
    private boolean uploadState;

    UploadInfo(){
        uploadState =false;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isUploadState() {
        return uploadState;
    }

    public void setUploadState(boolean uploadState) {
        this.uploadState = uploadState;
    }

    @Override
    public String toString() {
        return "UploadInfo{" +
                "patientID='" + patientID + '\'' +
                ", patientName='" + patientName + '\'' +
                ", indicatorName='" + indicatorName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", isUpload=" + uploadState +
                '}';
    }
}
