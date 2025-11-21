/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;

/**
 *
 * @author MALAK
 */
public class Adminn extends User {
    
    public Adminn(String username, String email, String passwordHash) {
        super(username, email, passwordHash);
        
     }
        @Override
        public String getRole() 
        {
          return "Admin";
        }
    
    public void approve(Course course, String reason)
        {
            
         if(!course.ispending())
         { throw new IllegalStateException("can only approve pending courses");
         }
         course.approve(this, reason);
        }
    
     public void reject(Course course, String reason)
        {
         if(!course.ispending())
         { throw new IllegalStateException("can only reject pending courses");
         }
         course.reject(this,reason);
        }
    
}
