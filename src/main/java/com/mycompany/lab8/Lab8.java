/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author USER
 */
public class Lab8 {

    public static void main(String[] args) {
        try {
            // --- Step 1: Create a Quiz ---
            ArrayList<String> options = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
            ArrayList<Question> questions = new ArrayList<>();
            questions.add(new Question("Q1", options, 1));
            questions.add(new Question("Q2", options, 2));
            questions.add(new Question("Q3", options, 0));
            questions.add(new Question("Q4", options, 3));
            questions.add(new Question("Q5", options, 1));

            Quiz quiz = new Quiz("lesson1", questions);
            quiz.setPassingScore(50); // require 50% to pass

            // --- Step 2: Simulate a student attempt ---
            ArrayList<Integer> chosenAnswers = new ArrayList<>(Arrays.asList(1, 2, 0, 3, 1)); // 3 correct
            QuizAttempt attempt = new QuizAttempt();
            attempt.setAttemptId("att1");
            attempt.setQuizId(quiz.getQuizId());
            attempt.setLessonId("lesson1");
            attempt.setScore(5); // student got 3 correct
            attempt.setRetryCount(0);
            attempt.setChosenAnswers(chosenAnswers);
            attempt.setTimestamp(LocalDateTime.now());

            // --- Step 3: Save attempt in JSON ---
            JsonDataBaseManager.addQuizAttempt("1585", attempt, quiz);

            System.out.println("✅ Quiz attempt saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
