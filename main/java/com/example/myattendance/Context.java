package com.example.myattendance;

import android.app.Application;

import com.example.myattendance.Bean.AttendanceGetSet;
import com.example.myattendance.Bean.GetSetStaff;
import com.example.myattendance.Bean.GetSetStudent;
import com.example.myattendance.Bean.SessionGetSet;

import java.util.ArrayList;

public class Context extends Application {

    private ArrayList<GetSetStudent> getSetStudent;
    private GetSetStaff getSetStaff;
    private SessionGetSet sessionGetSet;
    private ArrayList<AttendanceGetSet> attendanceGetSet;


    public GetSetStaff getGetSetStaff() {
        return getSetStaff;
    }

    public void setGetSetStaff(GetSetStaff getSetStaff) {
        this.getSetStaff = getSetStaff;
    }

    public SessionGetSet getSessionGetSet() {
        return sessionGetSet;
    }

    public void setSessionGetSet(SessionGetSet sessionGetSet) {
        this.sessionGetSet = sessionGetSet;
    }

    public ArrayList<GetSetStudent> getGetSetStudent() {
        return getSetStudent;
    }

    public void setGetSetStudent(ArrayList<GetSetStudent> getSetStudent) {
        this.getSetStudent = getSetStudent;
    }

    public ArrayList<AttendanceGetSet> getAttendanceGetSet() {
        return attendanceGetSet;
    }

    public void setAttendanceGetSet(ArrayList<AttendanceGetSet> attendanceGetSet) {
        this.attendanceGetSet = attendanceGetSet;
    }
}
