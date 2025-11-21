/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;

import java.util.List;
import java.util.Random;

/**
 *
 * @author u s e r
 */
public class Quiz {
    private String quizId;
    private String lessonId;
    private List<Question> questions;
    static private int passingScore=50;

    public Quiz( String lessonId, List<Question> questions) {
        
        Random rand = new Random();
        this.quizId = String.valueOf(rand.nextInt(10000));
        this.lessonId = lessonId;
        this.questions = questions;
    }
    
    
    public int getTotalPoints()
    {
        return questions.size();
    }
    
    public boolean isPassingScore(int score)
    {
        double perc =(score/passingScore)*100; 
        if(perc>=passingScore)
            return true;
        else 
            return false;
    }
    
    public void addQuestion(Question question)
    {
        questions.add(question);
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }
    
    
    
}
