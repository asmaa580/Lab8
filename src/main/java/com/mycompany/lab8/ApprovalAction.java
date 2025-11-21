/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;

/**
 *
 * @author MALAK
 */
public class ApprovalAction {
    private String action;
    private String adminId;
    private String time;
    private String reason;

    public ApprovalAction(String action, String adminId, String reason) {
        this.action = action;
        this.adminId = adminId;
        LocalDateTime now= LocalDateTime.ofInstant(Instant.now(),ZoneId.of("Africa/Cairo"));//coordinated universal time to egypt time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.time = now.format(formatter);
        this.reason = reason;
    }
    
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("action", action);
        obj.put("adminId", adminId);
        obj.put("timestamp", time);
        obj.put("reason", reason);
        return obj;}
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

  
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
}
