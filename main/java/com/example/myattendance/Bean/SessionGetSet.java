package com.example.myattendance.Bean;

public class SessionGetSet {
    private int sessionID;
    private int session_staffID;
    private String session_department;
    private String session_faculty;
    private String session_date;
    private String session_subject;

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getSession_staffID() {
        return session_staffID;
    }

    public void setSession_staffID(int session_staffID) {
        this.session_staffID = session_staffID;
    }

    public String getSession_department() {
        return session_department;
    }

    public void setSession_department(String session_department) {
        this.session_department = session_department;
    }

    public String getSession_faculty() {
        return session_faculty;
    }

    public void setSession_faculty(String session_faculty) {
        this.session_faculty = session_faculty;
    }

    public String getSession_date() {
        return session_date;
    }

    public void setSession_date(String session_date) {
        this.session_date = session_date;
    }

    public String getSession_subject() {
        return session_subject;
    }

    public void setSession_subject(String session_subject) {
        this.session_subject = session_subject;
    }
}
