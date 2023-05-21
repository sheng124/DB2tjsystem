package com.tjsystem.pojo;

import java.util.List;

public class CheckInfo {
    private String project_name;

    private double project_price;
    private List<String> Indicators;

    public List<String> getIndicators() {
        return Indicators;
    }

    public void setIndicators(List<String> Indicators) {
        this.Indicators = Indicators;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public double getProject_price() {
        return project_price;
    }

    public void setProject_price(double project_price) {
        this.project_price = project_price;
    }
}
