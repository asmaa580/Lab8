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

    /*public static boolean isCourseCompleted(Studentt student, Course course) {

        int totalQuizzes = course.getLessons().size();
        int passedQuizzes = 0;

        for (Lesson lesson : course.getLessons()) {

            Quiz quiz = lesson.getQuiz();

            // Student result:
            QuizResult result = student.getQuizResult(course.getCourseId(), quiz.getQuizId());

            if (result != null && result.isPassed()) {
                passedQuizzes++;
            }
        }

        return passedQuizzes == totalQuizzes;
    }
*/
    
    public static Certificate generateCertificate(Studentt student, Course course) throws IOException {

     //   if (!isCourseCompleted(student, course)) {
       //     return null;
        //}

        Certificate certificate = new Certificate(student.getUserId(), course.getCourseId());

        // Load users.json
        JSONArray users = JsonDataBaseManager.loadJson(USERS_FILE);

        for (int i = 0; i < users.length(); i++) {

            JSONObject u = users.getJSONObject(i);

            if (u.getString("userId").equals(student.getUserId())) {

                JSONArray certs;

                if (u.has("certificates"))
                    certs = u.getJSONArray("certificates");
                else
                    certs = new JSONArray();
                
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

    /**
     * Get all certificates for the student
     */
    public static ArrayList<Certificate> getCertificates(Studentt student) throws IOException {

        ArrayList<Certificate> result = new ArrayList<>();

        JSONArray users = JsonDataBaseManager.loadJson(USERS_FILE);

        for (int i = 0; i < users.length(); i++) {

            JSONObject u = users.getJSONObject(i);

            if (u.getString("userId").equals(student.getUserId())) {

                if (!u.has("certificates")) return result;

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
