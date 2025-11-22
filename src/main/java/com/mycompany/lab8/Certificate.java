/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONObject;
/**
 *
 * @author aseel
 */
public class Certificate {
    private String certificateID;
    private String courseID;
    private LocalDate issueDate;
    private String studentID;

    // Constructor for new certificate
    public Certificate(String studentID, String courseID) {
        this.certificateID = UUID.randomUUID().toString();
        this.courseID = courseID;
        this.issueDate = LocalDate.now();
        this.studentID = studentID;
    }

    // Constructor when loading from JSON
    public Certificate(String certificateID, String courseID, LocalDate issueDate) {
        this.certificateID = certificateID;
        this.courseID = courseID;
        this.issueDate = issueDate;
    }

    public String getCertificateID() { return certificateID; }
    public String getCourseID() { return courseID; }
    public LocalDate getIssueDate() { return issueDate; }
    public String getStudentID() { return studentID; }
}
