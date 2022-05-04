package com.example.myattendance.Bean;

public class AttendanceGetSet {
    private int attendanceID;
    private int attendance_studentID;
    private String attendance_status;

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public int getAttendance_studentID() {
        return attendance_studentID;
    }

    public void setAttendance_studentID(int attendance_studentID) {
        this.attendance_studentID = attendance_studentID;
    }

    public String getAttendance_status() {
        return attendance_status;
    }

    public void setAttendance_status(String attendance_status) {
        this.attendance_status = attendance_status;
    }
}
