package com.diatoz.microservice.producer.entity;

import jakarta.persistence.*;


import java.util.Date;


@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String studentName;
    private String studentLocation;

    @Temporal(TemporalType.DATE)
    private Date StudentJoinYear;

    private boolean graduated;

    @ManyToOne
    @JoinColumn(name = "collegeId", referencedColumnName = "collegeId")
    private CollegeEntity college;

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

    public CollegeEntity getCollege() {
        return college;
    }

    public void setCollege(CollegeEntity college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentLocation='" + studentLocation + '\'' +
                ", StudentJoinYear=" + StudentJoinYear +
                ", graduated=" + graduated +
                ", college=" + college +
                '}';
    }
}
