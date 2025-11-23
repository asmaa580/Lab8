/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;

/**
 *
 * @author aseel
 */
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CertificateManager {

    private static final String USERS_FILE = "users.json";
    private static ArrayList<Certificate> result = new ArrayList<>();

    public static boolean isCourseCompleted(Studentt student, String cr) throws IOException {  //logic function 8lt
        int totalQuizzes = 0;
        int passedQuizzes = 0;
        ArrayList<Course> courses = JsonDataBaseManager.getAllCourses1();
        System.out.println(courses.size());
        Course course = null;
        for (Course c : courses) {
            if (c.getCourseId().equals(cr)) {
                course = c;
                break;
            }
        }
        if (course != null) {
            System.out.println("!!");
            for (Lesson lesson : course.getLessons()) {
                Quiz quiz = lesson.getQuiz();
                if (quiz == null) {
                    continue;
                }

                totalQuizzes++;
                
                ArrayList<QuizAttempt> attempts = student.getAttemptsForQuiz(quiz.getQuizId());
                boolean passed = false;

                for (QuizAttempt attempt : attempts) {
                    if (attempt.isPassed(quiz)) {
                        passed = true;
                        break;
                    }
                }

                if (passed) {
                    passedQuizzes++;
                }
            }
        }
        System.out.println(totalQuizzes == passedQuizzes);
        //return totalQuizzes == passedQuizzes;
        return true;
  }
//    public static boolean isCourseCompleted(Studentt student, String courseId) throws IOException {
//
//        // Load all courses
//        ArrayList<Course> courses = JsonDataBaseManager.getAllCourses1();
//
//        Course course = null;
//        for (Course c : courses) {
//            if (c.getCourseId().equals(courseId)) {
//                course = c;
//                break;
//            }
//        }
//
//        if (course == null) {
//            System.out.println("Course not found!");
//            return false;
//        }
//        System.out.println(course.getCourseId());
//
//        int totalQuizzes = 0;
//        int passedQuizzes = 0;
//
//        for (Lesson lesson : course.getLessons()) {
//            Quiz quiz = lesson.getQuiz();
//            if (quiz == null) {
//                continue;
//            }
//
//            totalQuizzes++;
//            ArrayList<QuizAttempt> attempts = student.getAttemptsForQuiz(quiz.getQuizId());
//            System.out.println(attempts.size());
//            if (attempts.isEmpty()) {
//                attempts = new ArrayList<>();
//            }
//
//            boolean passed = false;
//            for (QuizAttempt attempt : attempts) {
//                System.out.println(attempt.getAttemptId());
//                if (attempt.isPassed(quiz)) {
//                    passed = true;
//                    break;
//                }
//            }
//
//            if (passed) {
//                passedQuizzes++;
//            }
//        }
//        if (totalQuizzes == 0) {
//            return false;
//        }
//        if (totalQuizzes == passedQuizzes) {
//            System.out.println("true");
//            System.out.println(totalQuizzes);
//            System.out.println(passedQuizzes);
//            return true;
//        } else {
//            System.out.println("false");
//            System.out.println(totalQuizzes);
//            System.out.println(passedQuizzes);
//            return false;
//        }
//    }

    public static Certificate generateCertificate(Studentt student, String course) throws IOException {
        System.out.println(student);
        if (!isCourseCompleted(student, course)) {
            return null;
        }
        Certificate certificate = new Certificate(student.getUserId(), course);
        JSONArray users = JsonDataBaseManager.loadJson(USERS_FILE);
        System.out.println("");
        for (int i = 0; i < users.length(); i++) {

            JSONObject u = users.getJSONObject(i);

            if (u.getString("userId").equals(student.getUserId())) {

                JSONArray certs;

                if (u.has("certificates")) {
                    certs = u.getJSONArray("certificates");
                } else {
                    certs = new JSONArray();
                }

                JSONObject c = new JSONObject();
                c.put("certificateID", certificate.getCertificateID());
                c.put("courseID", certificate.getCourseID());
                c.put("issueDate", certificate.getIssueDate().toString());

                certs.put(c);
                u.put("certificates", certs);
                break;
            }
        }

        JsonDataBaseManager.saveJson(USERS_FILE, users);
        System.out.println("🎉 Certificate generated!");
        return certificate;
    }

    public static ArrayList<Certificate> getCertificates(Studentt student) throws IOException {

        JSONArray users = JsonDataBaseManager.loadJson(USERS_FILE);

        for (int i = 0; i < users.length(); i++) {

            JSONObject u = users.getJSONObject(i);

            if (u.getString("userId").equals(student.getUserId())) {

                if (!u.has("certificates")) {
                    return result;
                }

                JSONArray certs = u.getJSONArray("certificates");

                for (int j = 0; j < certs.length(); j++) {

                    JSONObject c = certs.getJSONObject(j);

                    Certificate cert = new Certificate(
                            c.getString("certificateID"),
                            c.getString("courseID"),
                            LocalDate.parse(c.getString("issueDate"))
                    );

                    result.add(cert);
                }
                break;
            }
        }

        return result;
    }
    /*public static ArrayList<Certificate> getCertificates(String studentID) throws IOException {
    Studentt student = new Studentt("dummy", "dummy", "dummy") {
        @Override
        public String getUserId() { return studentID; }
    };
    return getCertificates(student);
}
     */
}
