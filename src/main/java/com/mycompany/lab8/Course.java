/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
/**
 *
 * @author u s e r
 */
public class Course {
    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private ArrayList<Lesson> lessons;
    private ArrayList<String> students;
    private String approval_status=PENDING;
    public static final String PENDING="PENDING";
    public static final String APPROVED="APPROVED";
    public static final String REJECTED="REJECTED";
    private ArrayList<ApprovalAction>approvalHistory;

    
    

    public Course(String courseId, String title, String description, String instructorId,
              ArrayList<String> students, ArrayList<Lesson> lessons, String approval_status) {
    this.courseId = courseId;
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
    this.lessons = lessons;
    this.students = students;
    this.approval_status = approval_status ;
    this.approvalHistory = new ArrayList<>();
}

    /*public Course(String courseId,String title, String description, String instructorId,ArrayList<String> students,ArrayList<Lesson> lessons) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = lessons;
        this.students = students;
        this.approval_status=PENDING;
        this.approvalHistory= new ArrayList<>();
    }
    
    public Course(String title, String description, String instructorId) {
        
        Random rand = new Random();
       this.courseId = String.valueOf(rand.nextInt(10000));
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
        this.approval_status=PENDING;
        this.approvalHistory= new ArrayList<>();
    }
*/
    public Course(String title, String description, String instructorId) {
    Random rand = new Random();
    this.courseId = String.valueOf(rand.nextInt(10000));
    this.title = title;
    this.description = description;
    this.instructorId = instructorId;
    this.lessons = new ArrayList<>();
    this.students = new ArrayList<>(); // only new courses default to pending
    this.approvalHistory = new ArrayList<>();
}

    
    
    public ArrayList<ApprovalAction> getApprovalHistory() {
        return approvalHistory;
    }
 

    public String getApproval_status() {
        return approval_status;
    }

    /*public void setApproval_status(String status,User user,String reason) {
        if(!user.getRole().equalsIgnoreCase("admin"))
        {  throw new SecurityException("Only admin can change course status");
        }
        if(status.equals(REJECTED)||status.equals(APPROVED)||status.equals(PENDING))
        {this.approval_status = status;
        ApprovalAction action=new ApprovalAction(status,user.getUserId(),reason);
         approvalHistory.add(action);
        }
        
        else 
            throw new IllegalArgumentException("Invalid approval status: " + status);
    }*/
    
    public void approve(User user,String reason) {
        if(!user.getRole().equalsIgnoreCase("admin"))
        {  throw new SecurityException("Only admin can change course status");
        }
        this.approval_status = APPROVED;
        ApprovalAction action=new ApprovalAction(this.approval_status,user.getUserId(),reason);
         approvalHistory.add(action);
        
    }
    
    public void reject(User user,String reason) {
        if(!user.getRole().equalsIgnoreCase("admin"))
        {  throw new SecurityException("Only admin can change course status");
        }
        this.approval_status = REJECTED;
        ApprovalAction action=new ApprovalAction(this.approval_status,user.getUserId(),reason);
         approvalHistory.add(action);
        
    }
    
    public boolean ispending()
    {return approval_status.equals(PENDING); }
    
    public boolean isapproved()
    {return approval_status.equals(APPROVED); }
    
    public boolean isrejected()
    {return approval_status.equals(REJECTED); }
    
    
    public String getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getInstructorId() { return instructorId; }
    public ArrayList<Lesson> getLessons() { return lessons; }
    public ArrayList<String> getStudents() { return students; }

    public void addLesson(Lesson lesson) { lessons.add(lesson); }
    public void removeLesson(String lessonId) {
        lessons.removeIf(l -> l.getLessonId().equals(lessonId));
    }

    public void enrollStudent(String studentId) {
        if (!students.contains(studentId)) students.add(studentId);
    }
}
