/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author aseel
 */

public class Studentt extends User {
    private ArrayList<String> enrolledCourses;
    private HashMap<String, ArrayList<String>> progress; // courseId -> list of completed lessonIds
    private HashMap<String, ArrayList<QuizAttempt>> quizAttempts;
    

    public Studentt(String username, String email, String passwordHash) {
        super(username, email, passwordHash);
        enrolledCourses = new ArrayList<>();
        progress = new HashMap<>();
    }
    
    public void addQuizAttempt(QuizAttempt attempt) {
        
    ArrayList<QuizAttempt> attempts = quizAttempts.getOrDefault(attempt.getQuizId(), new ArrayList<>());
    attempts.add(attempt);
    quizAttempts.put(attempt.getQuizId(), attempts);
}

    @Override
    public String getRole() { return "Student"; }

    public ArrayList<String> getEnrolledCourses() { return enrolledCourses; }
    public HashMap<String, ArrayList<String>> getProgress() { return progress; }

     
}

