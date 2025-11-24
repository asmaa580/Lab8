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
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonDataBaseManager {
    private static final String USERS_FILE = "users.json";
    private static final String COURSES_FILE = "courses.json";

    public static JSONArray loadJson(String file) throws IOException {
        Path path = Paths.get(file);

        
        if (!Files.exists(path)) {
            Files.writeString(path, "[]");
            return new JSONArray(); 
        }

        String content = Files.readString(path);
        if (content.isEmpty()) content = "[]"; 
        return new JSONArray(content);
    }

    // Save JSONArray to file
    public static void saveJson(String file, JSONArray array) throws IOException {
        Files.writeString(Paths.get(file), array.toString(4));
    }

    // ---------------- Users ----------------
    /*public static ArrayList<Course> loadpendingcourses()throws IOException
    {
     ArrayList<Course> pendingCourses=new ArrayList<>();
     JSONArray coursesArray = loadJson(COURSES_FILE);
     
     for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject obj = coursesArray.getJSONObject(i);
            String status = obj.getString("approval status");

            if ("PENDING".equalsIgnoreCase(status)) {        
            String courseId = obj.getString("courseId");
            String title = obj.getString("title");
            String description = obj.optString("description", "");
            String instructorId = obj.getString("instructorId");

            ArrayList<String> students = new ArrayList<>();
            if (obj.has("students")) {
                JSONArray studentsArray = obj.getJSONArray("students");
                for (int j = 0; j < studentsArray.length(); j++) {
                    students.add(studentsArray.getString(j));
                }
            }

            ArrayList<Lesson> lessons = new ArrayList<>();
if (obj.has("lessons")) {
    JSONArray lessonsArray = obj.getJSONArray("lessons");
    for (int j = 0; j < lessonsArray.length(); j++) {
        JSONObject lessonObj = lessonsArray.getJSONObject(j);
        Lesson lesson = new Lesson(
            lessonObj.getString("lessonId"),
            lessonObj.getString("title")
        );
        /*if (lessonObj.has("quiz")) {
            JSONObject quizObj = lessonObj.getJSONObject("quiz");
            JSONArray questionsArray = quizObj.getJSONArray("questions");

            for (int k = 0; k < questionsArray.length(); k++) {
                JSONObject qObj = questionsArray.getJSONObject(k);
                QuizQuestion question = new QuizQuestion(
                    qObj.getString("question"),
                    qObj.getJSONArray("options").toList(),
                    qObj.getInt("answer")
                );
                lesson.addQuestion(question);
            }
        }
        lessons.add(lesson);}
           }          
            Course course = new Course(courseId, title, description, instructorId, students, lessons,"PENDING");
            pendingCourses.add(course);
        }}
        return pendingCourses;
     }*/
    
    
    public static ArrayList<Course> loadapprovedcourses(String s)throws IOException
    {
     ArrayList<Course> approvedCourses=new ArrayList<>();
     JSONArray coursesArray = loadJson(COURSES_FILE);
     
     for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject obj = coursesArray.getJSONObject(i);
            String status = obj.getString("approval status");

            if (s.equalsIgnoreCase(status)) {        
            String courseId = obj.getString("courseId");
            String title = obj.getString("title");
            String description = obj.optString("description", "");
            String instructorId = obj.getString("instructorId");

            ArrayList<String> students = new ArrayList<>();
            if (obj.has("students")) {
                JSONArray studentsArray = obj.getJSONArray("students");
                for (int j = 0; j < studentsArray.length(); j++) {
                    students.add(studentsArray.getString(j));
                }
            }

            ArrayList<Lesson> lessons = new ArrayList<>();
if (obj.has("lessons")) {
    JSONArray lessonsArray = obj.getJSONArray("lessons");
    for (int j = 0; j < lessonsArray.length(); j++) {
        JSONObject lessonObj = lessonsArray.getJSONObject(j);
        Lesson lesson = new Lesson(
            lessonObj.getString("lessonId"),
            lessonObj.getString("title")
        );
        /*if (lessonObj.has("quiz")) {
            JSONObject quizObj = lessonObj.getJSONObject("quiz");
            JSONArray questionsArray = quizObj.getJSONArray("questions");

            for (int k = 0; k < questionsArray.length(); k++) {
                JSONObject qObj = questionsArray.getJSONObject(k);
                QuizQuestion question = new QuizQuestion(
                    qObj.getString("question"),
                    qObj.getJSONArray("options").toList(),
                    qObj.getInt("answer")
                );
                lesson.addQuestion(question);
            }
        }*/
        lessons.add(lesson);}
           }          
            Course course = new Course(courseId, title, description, instructorId, students, lessons,s);
            approvedCourses.add(course);
        }}
        return approvedCourses;
     }
    public static void addUser(User user) throws IOException {
        JSONArray users = loadJson(USERS_FILE);
        for (int i = 0; i < users.length(); i++) {
            if (users.getJSONObject(i).getString("email").equals(user.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
        }

        JSONObject obj = new JSONObject();
        obj.put("userId", user.getUserId());
        obj.put("username", user.getUsername());
        obj.put("email", user.getEmail());
        obj.put("passwordHash", user.getPasswordHash());
        obj.put("role", user.getRole());
        if (user instanceof Studentt) {
            obj.put("enrolledCourses", new JSONArray());
            obj.put("progress", new JSONObject());
        } else if (user instanceof Instructorr) {
            obj.put("createdCourses", new JSONArray());
        }
        users.put(obj);
        saveJson(USERS_FILE, users);
    }

   /* public static User authenticate(String email, String passwordHash) throws IOException {
        JSONArray users = loadJson(USERS_FILE);
        for (int i = 0; i < users.length(); i++) {
            JSONObject obj = users.getJSONObject(i);
            if (obj.getString("email").equals(email) && obj.getString("passwordHash").equals(passwordHash)) {
                if (obj.getString("role").equals("Student")) return new Studentt(obj.getString("username"), email, passwordHash);
                else return new Instructorr(obj.getString("username"), email, passwordHash);
            }
        }
        return null;
    }*/
    
   public static User authenticate(String email, String passwordHash) throws IOException {
    JSONArray users = loadJson(USERS_FILE);
    for (int i = 0; i < users.length(); i++) {
        JSONObject obj = users.getJSONObject(i);
        if (obj.getString("email").equals(email) && obj.getString("passwordHash").equals(passwordHash)) {
            String userId = obj.getString("userId");
            String username = obj.getString("username");
            String role = obj.getString("role");

            if (role.equals("Student")) {
                Studentt student = new Studentt(username, email, passwordHash);
                student.setUserId(userId); // restore stored ID
                return student;
            } else if(role.equalsIgnoreCase("Instructor")){
                Instructorr instructor = new Instructorr(username, email, passwordHash);
                instructor.setUserId(userId); // restore stored ID
                // also restore createdCourses if present
                if (obj.has("createdCourses")) {
                    JSONArray courses = obj.getJSONArray("createdCourses");
                    for (int j = 0; j < courses.length(); j++) {
                        instructor.addCourse(courses.getString(j));
                    }
                }
                return instructor;
            }
            else {
             Adminn admin= new Adminn(username,email,passwordHash);
             admin.setUserId(userId);
             return admin;
            }
        }
    }
    return null; // authentication failed
} 

    // ---------------- Courses ----------------

    public static void addCourse(Course course) throws IOException {
        JSONArray courses = loadJson(COURSES_FILE);
        JSONObject obj = new JSONObject();
        obj.put("courseId", course.getCourseId());
        obj.put("title", course.getTitle());
        obj.put("description", course.getDescription());
        obj.put("instructorId", course.getInstructorId());
        obj.put("lessons", new JSONArray());
        JSONArray studentsArray = new JSONArray();
    for (String studentId : course.getStudents()) {
        studentsArray.put(studentId);
    }
    obj.put("students", studentsArray);
        obj.put("approval status",course.getApproval_status());
        JSONArray historyArray = new JSONArray();
        for (ApprovalAction action : course.getApprovalHistory()) {
        historyArray.put(action.toJson());
        }
        obj.put("approvalHistory", historyArray);
        courses.put(obj);
        saveJson(COURSES_FILE, courses);
        updateInstructorCourses(course.getInstructorId(), course.getCourseId());
    }
   private static void updateInstructorCourses(String instructorId, String courseId) throws IOException {
    JSONArray users = loadJson(USERS_FILE);
    
    System.out.println("DEBUG: Trying to update instructor courses for ID: " + instructorId);
    
    // First, get the course title from courses.json
    String courseTitle = getCourseTitleById(courseId);
    
    if (courseTitle == null) {
        System.out.println("DEBUG: ERROR - Could not find course title for ID: " + courseId);
        return;
    }
    
    System.out.println("DEBUG: Found course title: " + courseTitle);
    
    // Find ANY instructor and add the course TITLE
    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);
        //if (user.getString("role").equals("Instructor")) 
        if (user.getString("userId").equals(instructorId))
        {
            System.out.println("DEBUG: Found an instructor - ID: " + user.getString("userId"));
            
            JSONArray createdCourses;
            if (user.has("createdCourses")) {
                createdCourses = user.getJSONArray("createdCourses");
            } else {
                createdCourses = new JSONArray();
            }
            
            
            createdCourses.put(courseId);
            user.put("createdCourses", createdCourses);
            System.out.println("DEBUG: Added course TITLE to instructor: " + user.getString("email"));
            break; // Stop after first instructor found
        }
    }
    
    saveJson(USERS_FILE, users);
}

// ADD THIS NEW METHOD to get course title by ID
private static String getCourseTitleById(String courseId) throws IOException {
    JSONArray courses = loadJson(COURSES_FILE);
    
    for (int i = 0; i < courses.length(); i++) {
        JSONObject course = courses.getJSONObject(i);
        if (course.getString("courseId").equals(courseId)) {
            return course.getString("title");
        }
    }
    return null; // Course not found
} 

private static Quiz loadQuizFromJson(JSONObject quizObj) {
    if (quizObj == null) return null;
    
    //Quiz quiz=new Quiz(,quizObj.getString("lessonId"),new ArrayList<Question>());
     Quiz quiz=new Quiz(quizObj.getString("quizId"),quizObj.getString("lessonId"),new ArrayList<Question>());
    
    // Load questions
    if (quizObj.has("questions")) {
        JSONArray questionsArray = quizObj.getJSONArray("questions");
        for (int i = 0; i < questionsArray.length(); i++) {
            JSONObject qObj = questionsArray.getJSONObject(i);
            String questionText = qObj.getString("text");
            JSONArray optionsArray = qObj.getJSONArray("options");
            ArrayList<String> options = new ArrayList<>();
            for (int j = 0; j < optionsArray.length(); j++) {
                options.add(optionsArray.getString(j));
            }
            int correctIndex = qObj.getInt("correctIndex");
            Question question = new Question(questionText, options, correctIndex);
            quiz.addQuestion(question);
        }
    }
    
    return quiz;
}

public static ArrayList<Course> getAllCourses1() throws IOException {
    JSONArray coursesArray = loadJson(COURSES_FILE);
    if (coursesArray.length() == 0) 
        return null;

    ArrayList<Course> courses = new ArrayList<>();

    for (int i = 0; i < coursesArray.length(); i++) {
        JSONObject obj = coursesArray.getJSONObject(i);

        // Students
        JSONArray studentsArray = obj.has("students") ? obj.getJSONArray("students") : new JSONArray();
        ArrayList<String> students = new ArrayList<>();
        for (int j = 0; j < studentsArray.length(); j++) {
            students.add(studentsArray.getString(j));
        }

        // Lessons
        JSONArray lessonsArray = obj.has("lessons") ? obj.getJSONArray("lessons") : new JSONArray();
        ArrayList<Lesson> lessons = new ArrayList<>();
        
        for (int j = 0; j < lessonsArray.length(); j++) {
            JSONObject lessonObj = lessonsArray.getJSONObject(j);
            Lesson lesson = new Lesson(
                lessonObj.getString("lessonId"),
                lessonObj.getString("title"), 
                lessonObj.getString("content")
            );

             if (lessonObj.has("quiz")) {
                JSONObject quizObj = lessonObj.getJSONObject("quiz");
                lesson.setQuiz(loadQuizFromJson(quizObj));
            }

            lessons.add(lesson);
        }
         ArrayList<ApprovalAction> approvalHistory = new ArrayList<>();
        if (obj.has("approvalHistory")) {
            JSONArray historyArray = obj.getJSONArray("approvalHistory");
            for (int j = 0; j < historyArray.length(); j++) {
                JSONObject actionObj = historyArray.getJSONObject(j);
                String action = actionObj.getString("action");
                String adminId = actionObj.getString("adminId");
                String timestamp = actionObj.getString("timestamp");
                String reason = actionObj.getString("reason");

                // You may want a constructor in ApprovalAction that accepts timestamp
                ApprovalAction actionObjParsed = new ApprovalAction(action, adminId, reason);
                // If you want to preserve timestamp from JSON, add a constructor like:
                // new ApprovalAction(action, adminId, reason, timestamp);
                approvalHistory.add(actionObjParsed);
            }
        }

        // Course object
        Course course = new Course(
            obj.getString("courseId"),
            obj.getString("title"), 
            obj.getString("description"), 
            obj.getString("instructorId"),
            students,
            lessons,
            obj.getString("approval status")    
        );
        course.getApprovalHistory().addAll(approvalHistory);
        courses.add(course);
    }

    return courses;
}


    
 public static void enrollStudentInCourse(String studentId, String courseId) throws IOException {
    JSONArray courses = loadJson("courses.json");

    for (int i = 0; i < courses.length(); i++) {
        JSONObject course = courses.getJSONObject(i);

        if (course.getString("courseId").equals(courseId)) {
            JSONArray students = course.getJSONArray("students");

            // prevent duplicate enrollment
            if (!students.toList().contains(studentId)) {
                students.put(studentId);
            }

            saveJson("courses.json", courses);
            break;
        }
    }

    // Update student's enrolledCourses
    JSONArray users = loadJson("users.json");
    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);

        if (user.getString("userId").equals(studentId)) {
            JSONArray enrolled = user.getJSONArray("enrolledCourses");

            if (!enrolled.toList().contains(courseId)) {
                enrolled.put(courseId);
            }

            saveJson("users.json", users);
            break;
        }
    }
}

public static ArrayList<Course> getEnrolledCourses(String studentId) throws IOException {
    ArrayList<Course> result = new ArrayList<>();

    ArrayList<Course> allCourses = getAllCourses1();
    JSONArray users = loadJson("users.json");

    // Get student's enrolled course IDs
    JSONArray enrolledIds = null;
    for (int i = 0; i < users.length(); i++) {
        JSONObject u = users.getJSONObject(i);
        if (u.getString("userId").equals(studentId)) {
            enrolledIds = u.getJSONArray("enrolledCourses");
            break;
        }
    }

    if (enrolledIds == null)
        return result;

    // Match course IDs to Course objects
    for (Course c : allCourses) {
        if (enrolledIds.toList().contains(c.getCourseId())) {
            result.add(c);
        }
    }

    return result;
}

    public static ArrayList<Studentt> getStudentsForCourse(String courseId) throws IOException {

    ArrayList<Studentt> result = new ArrayList<>();

    JSONArray courses = loadJson(COURSES_FILE);
    JSONArray users = loadJson(USERS_FILE);

    // find the course by ID
    JSONObject targetCourse = null;
    for (int i = 0; i < courses.length(); i++) {
        JSONObject c = courses.getJSONObject(i);
        if (c.getString("courseId").equals(courseId)) {
            targetCourse = c;
            break;
        }
    }

    if (targetCourse == null) {
        return result; // no such course
    }

    // extract student IDs
    JSONArray studentIds = targetCourse.getJSONArray("students");

    // match each student ID with a user in users.json
    for (int i = 0; i < users.length(); i++) {
        JSONObject u = users.getJSONObject(i);

        if (studentIds.toList().contains(u.getString("userId"))) {

            result.add(new Studentt(
                u.getString("username"),
                u.getString("email"),
                u.getString("passwordHash")
            ));
        }
    }

    return result;
}

 
 public static void updateLessonInCourse(Lesson updatedLesson) throws IOException {
JSONArray coursesArray = loadJson(COURSES_FILE);

for (int i = 0; i < coursesArray.length(); i++) {
JSONObject courseObj = coursesArray.getJSONObject(i);
JSONArray lessonsArray = courseObj.has("lessons") ? courseObj.getJSONArray("lessons") : new JSONArray();

for (int j = 0; j < lessonsArray.length(); j++) {
JSONObject lessonObj = lessonsArray.getJSONObject(j);

if (lessonObj.getString("lessonId").equals(updatedLesson.getLessonId())) {
// Keep original order
lessonObj.put("resources", updatedLesson.getResources());
lessonObj.put("title", updatedLesson.getTitle());
lessonObj.put("content", updatedLesson.getContent());

// Add quiz if exists
Quiz q = updatedLesson.getQuiz();
if (q != null) {
JSONObject quizObj = new JSONObject();
quizObj.put("quizId", q.getQuizId());
quizObj.put("lessonId", q.getLessonId());
quizObj.put("passingScore", q.getPassingScore());

JSONArray qList = new JSONArray();
for (Question ques : q.getQuestions()) {
JSONObject qJson = new JSONObject();
qJson.put("text", ques.getText());
qJson.put("options", ques.getOptions());
qJson.put("correctIndex", ques.getCorrectAnswerIndex());
qList.put(qJson);
}

quizObj.put("questions", qList);
lessonObj.put("quiz", quizObj);
}
updatedLesson.setQuiz(q);

// Save JSON back
saveJson(COURSES_FILE, coursesArray);

// Return the updated lesson
// Save back and return immediately
saveJson(COURSES_FILE, coursesArray);
return;
}
}
}}

public void updateCourse(Course updatedCourse) throws IOException {
    // Load all courses from file
    JSONArray coursesArray = loadJson(COURSES_FILE);

    for (int i = 0; i < coursesArray.length(); i++) {
        JSONObject obj = coursesArray.getJSONObject(i);

        // Find the course by ID
        if (obj.getString("courseId").equals(updatedCourse.getCourseId())) {
            
            obj.put("title", updatedCourse.getTitle());
            obj.put("description", updatedCourse.getDescription());
            obj.put("instructorId", updatedCourse.getInstructorId());

            // Lessons
            JSONArray lessonsArray = new JSONArray();
            for (Lesson lesson : updatedCourse.getLessons()) {
                JSONObject lessonObj = new JSONObject();
                lessonObj.put("lessonId", lesson.getLessonId());
                lessonObj.put("title", lesson.getTitle());
                lessonObj.put("content", lesson.getContent());
                lessonObj.put("resources", new JSONArray(lesson.getResources()));

                // --- Add quiz if exists ---
                Quiz quiz = lesson.getQuiz();
                if (quiz != null) {
                    JSONObject quizObj = new JSONObject();
                    quizObj.put("quizId", quiz.getQuizId());
                    quizObj.put("lessonId", quiz.getLessonId());
                    quizObj.put("passingScore", quiz.getPassingScore());

                    // Add questions
                    JSONArray questionsArray = new JSONArray();
                    for (Question q : quiz.getQuestions()) {
                        JSONObject qObj = new JSONObject();
                        qObj.put("text", q.getText());
                        qObj.put("options", q.getOptions());
                        qObj.put("correctIndex", q.getCorrectAnswerIndex());
                        questionsArray.put(qObj);
                    }
                    quizObj.put("questions", questionsArray);

                    // Attach quiz to lesson
                    lessonObj.put("quiz", quizObj);
                }

                lessonsArray.put(lessonObj);
            }
            obj.put("lessons", lessonsArray);

            // Students
            JSONArray studentsArray = new JSONArray();
            for (String studentId : updatedCourse.getStudents()) {
                studentsArray.put(studentId);
            }
            obj.put("students", studentsArray);

            // Approval status
            obj.put("approval status", updatedCourse.getApproval_status());

            // Approval history
            JSONArray historyArray = new JSONArray();
            for (ApprovalAction action : updatedCourse.getApprovalHistory()) {
                historyArray.put(action.toJson());
            }
            obj.put("approvalHistory", historyArray);

            // Replace course object in array
            coursesArray.put(i, obj);
            break;
        }
    }

    // Save back to file
    saveJson(COURSES_FILE, coursesArray);
}
public static HashMap<String, ArrayList<String>> getUserProgress(String studentId) throws IOException {
    HashMap<String, ArrayList<String>> progressMap = new HashMap<>();

    // Load users.json
    JSONArray users = loadJson(USERS_FILE);

    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);

        if (user.getString("userId").equals(studentId)) {
            if (user.has("progress")) {
                JSONObject progressObj = user.getJSONObject("progress");

                // Iterate over courseIds
                Iterator<String> keys = progressObj.keys();
                while (keys.hasNext()) {
                    String courseId = keys.next();
                    JSONArray lessonsArray = progressObj.getJSONArray(courseId);

                    ArrayList<String> lessonsList = new ArrayList<>();
                    for (int j = 0; j < lessonsArray.length(); j++) {
                        lessonsList.add(lessonsArray.getString(j));
                    }

                    progressMap.put(courseId, lessonsList);
                }
            }
            break;
        }
    }

    return progressMap;
}


public static void addQuizAttempt(String studentId, QuizAttempt attempt, Quiz quiz, String courseId) throws IOException {
    // --- Update users.json ---
    JSONArray users = loadJson(USERS_FILE);

    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);
        if (user.getString("userId").equals(studentId)) {

            // Ensure quizAttempts field exists
            JSONObject quizAttempts = user.has("quizAttempts") ? user.getJSONObject("quizAttempts") : new JSONObject();

            // Get attempts list for this quiz
            JSONArray attemptsArray = quizAttempts.has(attempt.getQuizId())
                    ? quizAttempts.getJSONArray(attempt.getQuizId())
                    : new JSONArray();

            // Build JSON manually
            JSONObject attObj = new JSONObject();
            attObj.put("attemptId", attempt.getAttemptId());
            attObj.put("quizId", attempt.getQuizId());
            attObj.put("lessonId", attempt.getLessonId());
            attObj.put("score", attempt.getScore());
            attObj.put("retryCount", attempt.getRetryCount());
            attObj.put("chosenAnswers", attempt.getChosenAnswers());
            attObj.put("passed", attempt.isPassed(quiz));
            attObj.put("timestamp", attempt.getTimestamp().toString());

            // Add new attempt
            attemptsArray.put(attObj);
            quizAttempts.put(attempt.getQuizId(), attemptsArray);
            user.put("quizAttempts", quizAttempts);

            // --- Update progress if passed ---
            if (attempt.isPassed(quiz)) {
                JSONObject progress = user.has("progress") ? user.getJSONObject("progress") : new JSONObject();

                // Get lessons completed for this courseId
                JSONArray lessons = progress.has(courseId)
                        ? progress.getJSONArray(courseId)
                        : new JSONArray();

                if (!lessons.toList().contains(attempt.getLessonId())) {
                    lessons.put(attempt.getLessonId());
                }
                progress.put(courseId, lessons);
                user.put("progress", progress);
            }
            break;
        }
    }
    saveJson(USERS_FILE, users);

    // --- Update courses.json stats ---
    updateCourseStats(attempt.getQuizId(), attempt.getLessonId());
}

private static void updateCourseStats(String quizId, String lessonId) throws IOException {
JSONArray users = loadJson(USERS_FILE);

    int totalScore = 0;
    int attemptCount = 0;
    int passedCount = 0;

    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);

        if (user.has("quizAttempts")) {
            JSONObject attempts = user.getJSONObject("quizAttempts");

            if (attempts.has(quizId)) {
                JSONArray quizAttempts = attempts.getJSONArray(quizId);

                for (int j = 0; j < quizAttempts.length(); j++) {
                    JSONObject att = quizAttempts.getJSONObject(j);
                    totalScore += att.getInt("score");
                    attemptCount++;
                    if (att.getBoolean("passed")) {
                        passedCount++;
                    }
                }
            }
        }
    }

    double averageScore = attemptCount > 0 ? (double) totalScore / attemptCount : 0.0;
    double completionRate = attemptCount > 0 ? (double) passedCount / attemptCount : 0.0;

    // --- 2. Load courses.json ---
    JSONArray courses = loadJson(COURSES_FILE);

    for (int i = 0; i < courses.length(); i++) {

        JSONObject course = courses.getJSONObject(i);
        JSONArray lessons = course.getJSONArray("lessons");

        for (int j = 0; j < lessons.length(); j++) {
            JSONObject lesson = lessons.getJSONObject(j);

            // Only update if:
            // 1) lessonId matches
            // 2) lesson actually has a quiz object
            if (lesson.getString("lessonId").equals(lessonId) && lesson.has("quiz")) {

                JSONObject stats = new JSONObject();
                stats.put("averageScore", averageScore);
                stats.put("completionRate", completionRate);

                lesson.put("quizStats", stats);
                lessons.put(j, lesson);
                break;
            }
        }
    }

    saveJson(COURSES_FILE, courses);}

/*private static void updateCourseStats(String quizId, String lessonId) throws IOException {
    // --- Step 1: Collect attempts from users.json ---
    JSONArray users = loadJson(USERS_FILE);

    int totalScore = 0;
    int attemptCount = 0;
    int passedCount = 0;

    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);

        if (user.has("quizAttempts")) {
            JSONObject quizAttempts = user.getJSONObject("quizAttempts");

            if (quizAttempts.has(quizId)) {
                JSONArray attempts = quizAttempts.getJSONArray(quizId);

                for (int j = 0; j < attempts.length(); j++) {
                    JSONObject att = attempts.getJSONObject(j);
                    totalScore += att.getInt("score");
                    attemptCount++;
                    if (att.getBoolean("passed")) {
                        passedCount++;
                    }
                }
            }
        }
    }

    double averageScore = attemptCount > 0 ? (double) totalScore / attemptCount : 0.0;
    double completionRate = attemptCount > 0 ? (double) passedCount / attemptCount : 0.0;

    // --- Step 2: Update courses.json ---
    JSONArray courses = loadJson(COURSES_FILE);

    for (int i = 0; i < courses.length(); i++) {
        JSONObject course = courses.getJSONObject(i);
        JSONArray lessons = course.getJSONArray("lessons");

        for (int j = 0; j < lessons.length(); j++) {
            JSONObject lesson = lessons.getJSONObject(j);

            if (lesson.getString("lessonId").equals(lessonId)) {
                JSONObject stats = new JSONObject();
                stats.put("averageScore", averageScore);
                stats.put("completionRate", completionRate);

                lesson.put("quizStats", stats);
                lessons.put(j, lesson); // replace lesson object
                break;
            }
        }
    }

    saveJson(COURSES_FILE, courses);
<<<<<<< Updated upstream
}*/

public static boolean hasPassedQuiz(String studentId, String quizId) throws IOException {
    JSONArray users = loadJson(USERS_FILE);

    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);
        if (user.getString("userId").equals(studentId)) {
            if (user.has("quizAttempts")) {
                JSONObject quizAttempts = user.getJSONObject("quizAttempts");
                if (quizAttempts.has(quizId)) {
                    JSONArray attempts = quizAttempts.getJSONArray(quizId);
                    for (int j = 0; j < attempts.length(); j++) {
                        JSONObject att = attempts.getJSONObject(j);
                        if (att.getBoolean("passed")) {
                            return true;
                        }
                    }
                }
            }
        }
    }
    return false;
}

public static boolean canRetry(String studentId, String quizId, int maxRetries) throws IOException {
    JSONArray users = loadJson(USERS_FILE);

    for (int i = 0; i < users.length(); i++) {
        JSONObject user = users.getJSONObject(i);
        if (user.getString("userId").equals(studentId)) {
            if (user.has("quizAttempts")) {
                JSONObject quizAttempts = user.getJSONObject("quizAttempts");
                if (quizAttempts.has(quizId)) {
                    JSONArray attempts = quizAttempts.getJSONArray(quizId);
                    return attempts.length() < maxRetries;
                }
            }
        }
    }
    return true; // no attempts yet
}


}