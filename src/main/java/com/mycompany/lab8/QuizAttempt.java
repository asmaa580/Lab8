/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author USER
 */
public class QuizAttempt {

    private String attemptId;
    private String QuizId;
    private String LessonId;
    private int score;
    private int retryCount;
    private ArrayList<Integer> chosenAnswers;
    private LocalDateTime timestamp;

    public QuizAttempt( String QuizId, String LessonId, int score, int retryCount, ArrayList<Integer> chosenAnswers) {
          Random rand = new Random();
       this.attemptId = String.valueOf(rand.nextInt(10000));
        this.QuizId = QuizId;
        this.LessonId = LessonId;
        this.score = score;
        this.retryCount = retryCount;
        this.chosenAnswers = chosenAnswers;
        this.timestamp = LocalDateTime.now();
    }

    public QuizAttempt() {
    }

    
    public boolean isPassed(Quiz quiz)
    {
        return quiz.isPassingScore(score);
    }

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public String getQuizId() {
        return QuizId;
    }

    public void setQuizId(String QuizId) {
        this.QuizId = QuizId;
    }

    public String getLessonId() {
        return LessonId;
    }

    public void setLessonId(String LessonId) {
        this.LessonId = LessonId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public ArrayList<Integer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(ArrayList<Integer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
