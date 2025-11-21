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
public class Question {
    private String questionId;
    private String text;
    private List<String> options;
    private String correctAnswer; 

    public Question(String text, List<String> options, String correctAnswer) {
        Random rand = new Random();
       this.questionId = String.valueOf(rand.nextInt(10000));
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
    
    
    public boolean isCorrectAnswer(String answer)
    {
        return answer.equals(correctAnswer);
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    
}
