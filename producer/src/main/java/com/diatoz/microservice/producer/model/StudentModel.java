package com.diatoz.microservice.producer.model;


import java.util.Date;

public class StudentModel {
    private Integer studentId;
    private String studentName;

    private String studentLocation;

    private Date StudentJoinYear;

    private boolean graduated;

    private CollegeModel college;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLocation() {
        return studentLocation;
    }

    public void setStudentLocation(String studentLocation) {
        this.studentLocation = studentLocation;
    }

    public Date getStudentJoinYear() {
        return StudentJoinYear;
    }

    public void setStudentJoinYear(Date studentJoinYear) {
        StudentJoinYear = studentJoinYear;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public CollegeModel getCollege() {
        return college;
    }

    public void setCollege(CollegeModel college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentLocation='" + studentLocation + '\'' +
                ", StudentJoinYear=" + StudentJoinYear +
                ", graduated=" + graduated +
                ", college=" + college +
                '}';
    }
}
