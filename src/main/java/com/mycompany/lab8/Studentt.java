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
        quizAttempts = new HashMap<>();
    }

    public Studentt(String id) {
        super("", "", "");
        super.setUserId(id);
        enrolledCourses = new ArrayList<>();
        progress = new HashMap<>();
        quizAttempts = new HashMap<>();

    }

    public void addQuizAttempt(QuizAttempt attempt) {

        ArrayList<QuizAttempt> attempts = quizAttempts.getOrDefault(attempt.getQuizId(), new ArrayList<>());
        attempts.add(attempt);
        quizAttempts.put(attempt.getQuizId(), attempts);
        System.out.println("size" +quizAttempts.size());
        //JsonDataBaseManager.sa

    }

    public ArrayList<QuizAttempt> getAttemptsForQuiz(String quizId) {
        return quizAttempts.getOrDefault(quizId, new ArrayList<>());
    }

// Get the latest score for a specific quiz
    public int getLatestScore(String quizId) {
        ArrayList<QuizAttempt> attempts = quizAttempts.get(quizId);
        if (attempts == null || attempts.isEmpty()) {
            return -1; // means no attempts yet
        }
        // get the last attempt in the list
        QuizAttempt latest = attempts.get(attempts.size() - 1);
        return latest.getScore();
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public HashMap<String, ArrayList<String>> getProgress() {
        return progress;
    }

}
