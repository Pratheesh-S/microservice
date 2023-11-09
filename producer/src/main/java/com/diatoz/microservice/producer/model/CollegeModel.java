package com.diatoz.microservice.producer.model;


public class CollegeModel {
    private Integer collegeId;
    private String collegeName;

    private String collegeLocation;

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeLocation() {
        return collegeLocation;
    }

    public void setCollegeLocation(String collegeLocation) {
        this.collegeLocation = collegeLocation;
    }

    @Override
    public String toString() {
        return "CollegeModel{" +
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", collegeLocation='" + collegeLocation + '\'' +
                '}';
    }
}
