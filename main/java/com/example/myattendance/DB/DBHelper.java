package com.example.myattendance.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myattendance.Bean.AttendanceGetSet;
import com.example.myattendance.Bean.GetSetStaff;
import com.example.myattendance.Bean.GetSetStudent;
import com.example.myattendance.Bean.SessionGetSet;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db.alfajiri";


    private static final String STAFF_TABLE = "staff";
    private static final String STUDENT_TABLE = "student";
    private static final String SESSION_TABLE = "session";
    private static final String ATTENDANCE_TABLE = "attendance";

    private static final String STAFF_ID = "staffID";
    private static final String STAFF_FNAME = "staff_fname";
    private static final String STAFF_LNAME = "staff_lname";
    private static final String STAFF_PHONE = "staff_phone";
    private static final String STAFF_EMAIL = "staff_email";
    private static final String STAFF_PASSWORD = "staff_password";

    private static final String STUDENT_ID = "studentID";
    private static final String STUDENT_FNAME = "student_fname";
    private static final String STUDENT_LNAME = "student_lname";
    private static final String STUDENT_PHONE = "student_phone";
    private static final String STUDENT_EMAIL = "student_email";
    private static final String STUDENT_DEPARTMENT = "student_department";
    private static final String STUDENT_FACULTY = "student_faculty";
    private static final String STUDENT_PASSWORD = "student_password";

    private static final String SESSION_ID = "sessionID";
    private static final String SESSION_STAFF_ID = "session_staffID";
    private static final String SESSION_DEPARTMENT = "session_department";
    private static final String SESSION_FACULTY = "session_faculty";
    private static final String SESSION_DATE = "session_date";
    private static final String SESSION_SUBJECT = "session_subject";

    private static final String ATTENDANCE_ID = "attendanceID";
    private static final String ATTENDANCE_STUDENT_ID = "attendance_studentID";
    private static final String ATTENDANCE_STATUS = "attendance_status";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String staff = "CREATE TABLE " + STAFF_TABLE + " (" +
                STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STAFF_FNAME + " TEXT, " +
                STAFF_LNAME + " TEXT, " +
                STAFF_PHONE + " TEXT, " +
                STAFF_EMAIL + " TEXT," +
                STAFF_PASSWORD + " TEXT " + ")";
        Log.d("staff", staff);

        String students = "CREATE TABLE " + STUDENT_TABLE + " (" +
                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENT_FNAME + " TEXT, " +
                STUDENT_LNAME + " TEXT, " +
                STUDENT_PHONE + " TEXT, " +
                STUDENT_EMAIL + " TEXT," +
                STUDENT_DEPARTMENT + " TEXT," +
                STUDENT_FACULTY + " TEXT," +
                STUDENT_PASSWORD + " TEXT " + ")";
        Log.d("students", students);

        String sessions = "CREATE TABLE " + SESSION_TABLE + " (" +
                SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SESSION_STAFF_ID + " INTEGER, " +
                SESSION_DEPARTMENT + " TEXT, " +
                SESSION_FACULTY + " TEXT, " +
                SESSION_DATE + " DATE," +
                SESSION_SUBJECT + " TEXT" + ")";
        Log.d("sessions", sessions);

        String attendance = "CREATE TABLE " + ATTENDANCE_TABLE + " (" +
                ATTENDANCE_ID + " INTEGER, " +
                ATTENDANCE_STUDENT_ID + " INTEGER, " +
                ATTENDANCE_STATUS + " TEXT " + ")";
        Log.d("attendance", attendance);


        try {
            DB.execSQL(staff);
            DB.execSQL(students);
            DB.execSQL(sessions);
            DB.execSQL(attendance);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        String staff = "CREATE TABLE " + STAFF_TABLE + " (" +
                STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STAFF_FNAME + " TEXT, " +
                STAFF_LNAME + " TEXT, " +
                STAFF_PHONE + " TEXT, " +
                STAFF_EMAIL + " TEXT," +
                STAFF_PASSWORD + " TEXT " + ")";
        Log.d("staff", staff);

        String students = "CREATE TABLE " + STUDENT_TABLE + " (" +
                STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENT_FNAME + " TEXT, " +
                STUDENT_LNAME + " TEXT, " +
                STUDENT_PHONE + " TEXT, " +
                STUDENT_EMAIL + " TEXT," +
                STUDENT_DEPARTMENT + " TEXT," +
                STUDENT_FACULTY + " TEXT," +
                STUDENT_PASSWORD + " TEXT " + ")";
        Log.d("students", students);

        String sessions = "CREATE TABLE " + SESSION_TABLE + " (" +
                SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SESSION_STAFF_ID + " INTEGER, " +
                SESSION_DEPARTMENT + " TEXT, " +
                SESSION_FACULTY + " TEXT, " +
                SESSION_DATE + " TEXT," +
                SESSION_SUBJECT + " TEXT" + ")";
        Log.d("sessions", sessions);

        String attendance = "CREATE TABLE " + ATTENDANCE_TABLE + " (" +
                ATTENDANCE_ID + " INTEGER, " +
                ATTENDANCE_STUDENT_ID + " INTEGER, " +
                ATTENDANCE_STATUS + " TEXT " + ")";
        Log.d("attendance", attendance);


        try {
            DB.execSQL(staff);
            DB.execSQL(students);
            DB.execSQL(sessions);
            DB.execSQL(attendance);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    //STAFF
    public void insertStaff(GetSetStaff getSetStaff) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "INSERT INTO faculty_table (faculty_firstname,faculty_Lastname,faculty_phonenumber,faculty_email,faculty_password) values ('" +
                getSetStaff.getStaff_fname() + "', '" +
                getSetStaff.getStaff_lname() + "', '" +
                getSetStaff.getStaff_phone() + "', '" +
                getSetStaff.getStaff_email() + "', '" +
                getSetStaff.getStaff_password() + "')";
        Log.d("query", query);
        DB.execSQL(query);
        DB.close();
    }

    public GetSetStaff validateStaff(String eMail, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "SELECT * FROM staff where staff_email = '" + eMail + "' and staff_password = '" + password + "'";
        Cursor cursor = DB.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            GetSetStaff getSetStaff = new GetSetStaff();
            getSetStaff.setStaffID(Integer.parseInt(cursor.getString(0)));
            getSetStaff.setStaff_fname(cursor.getString(1));
            getSetStaff.setStaff_lname(cursor.getString(2));
            getSetStaff.setStaff_phone(cursor.getString(3));
            getSetStaff.setStaff_email(cursor.getString(4));
            getSetStaff.setStaff_password(cursor.getString(5));
            return getSetStaff;
        }
        return null;
    }

    public ArrayList<GetSetStaff> getAllStaff() {
        Log.d("in get all", "in get all");
        ArrayList<GetSetStaff> array = new ArrayList<GetSetStaff>();

        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM staff";
        Cursor cursor = DB.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                GetSetStaff getSetStaff = new GetSetStaff();
                getSetStaff.setStaffID(Integer.parseInt(cursor.getString(0)));
                getSetStaff.setStaff_fname(cursor.getString(1));
                getSetStaff.setStaff_lname(cursor.getString(2));
                getSetStaff.setStaff_phone(cursor.getString(3));
                getSetStaff.setStaff_email(cursor.getString(4));
                getSetStaff.setStaff_password(cursor.getString(5));
                array.add(getSetStaff);

            } while (cursor.moveToNext());
        }
        return array;
    }

    public void deleteFaculty(int staff_ID) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "DELETE FROM faculty_table WHERE staffID=" + staff_ID;

        Log.d("query", query);
        DB.execSQL(query);
        DB.close();
    }


    //STUDENT
    public void insertStudent(GetSetStudent getSetStudent) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "INSERT INTO student (student_fname,student_lname,student_phone,student_email,student_department,student_faculty,student_password) " +
                "values ('" +
                getSetStudent.getStudent_fname() + "', '" +
                getSetStudent.getStudent_lname() + "','" +
                getSetStudent.getStudent_phone() + "', '" +
                getSetStudent.getStudent_email() + "', ''" +
                getSetStudent.getStudent_department() + "', '" +
                getSetStudent.getStudent_faculty() + "', '" +
                getSetStudent.getStudent_password() + "')";
        Log.d("query", query);
        DB.execSQL(query);
        DB.close();
    }

    public GetSetStudent validateStudent(String eMail, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "SELECT * FROM student where student_email='" + eMail + "' and student_password='" + password + "'";
        Cursor cursor = DB.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            GetSetStudent getSetStudent = new GetSetStudent();
            getSetStudent.setStudentID(Integer.parseInt(cursor.getString(0)));
            getSetStudent.setStudent_fname(cursor.getString(1));
            getSetStudent.setStudent_lname(cursor.getString(2));
            getSetStudent.setStudent_phone(cursor.getString(3));
            getSetStudent.setStudent_email(cursor.getString(4));
            getSetStudent.setStudent_department(cursor.getString(5));
            getSetStudent.setStudent_faculty(cursor.getString(6));
            getSetStudent.setStudent_password(cursor.getString(7));
            return getSetStudent;
        }
        return null;
    }

    public ArrayList<GetSetStudent> getAllStudent() {
        ArrayList<GetSetStudent> array = new ArrayList<GetSetStudent>();

        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM student";
        Cursor cursor = DB.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                GetSetStudent getSetStudent = new GetSetStudent();
                getSetStudent.setStudentID(Integer.parseInt(cursor.getString(0)));
                getSetStudent.setStudent_fname(cursor.getString(1));
                getSetStudent.setStudent_lname(cursor.getString(2));
                getSetStudent.setStudent_phone(cursor.getString(3));
                getSetStudent.setStudent_email(cursor.getString(4));
                getSetStudent.setStudent_department(cursor.getString(5));
                getSetStudent.setStudent_faculty(cursor.getString(6));
                getSetStudent.setStudent_password(cursor.getString(7));
                array.add(getSetStudent);
            } while (cursor.moveToNext());
        }
        return array;
    }

    public ArrayList<GetSetStudent> getAllStudentBy(String faculty, String department) {
        ArrayList<GetSetStudent> array = new ArrayList<GetSetStudent>();

        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM student where student_facutly='" + faculty + "' and student_department='" + department + "'";
        Cursor cursor = DB.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                GetSetStudent getSetStudent = new GetSetStudent();
                getSetStudent.setStudentID(Integer.parseInt(cursor.getString(0)));
                getSetStudent.setStudent_fname(cursor.getString(1));
                getSetStudent.setStudent_lname(cursor.getString(2));
                getSetStudent.setStudent_phone(cursor.getString(3));
                getSetStudent.setStudent_email(cursor.getString(4));
                getSetStudent.setStudent_department(cursor.getString(5));
                getSetStudent.setStudent_faculty(cursor.getString(6));
                getSetStudent.setStudent_password(cursor.getString(7));
                array.add(getSetStudent);
            } while (cursor.moveToNext());
        }
        return array;
    }

    public GetSetStudent getStudentById(int student_ID) {
        GetSetStudent getSetStudent = new GetSetStudent();
        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM student where studentID=" + student_ID;
        Cursor cursor = DB.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                getSetStudent.setStudentID(Integer.parseInt(cursor.getString(0)));
                getSetStudent.setStudent_fname(cursor.getString(1));
                getSetStudent.setStudent_lname(cursor.getString(2));
                getSetStudent.setStudent_phone(cursor.getString(3));
                getSetStudent.setStudent_email(cursor.getString(4));
                getSetStudent.setStudent_department(cursor.getString(5));
                getSetStudent.setStudent_faculty(cursor.getString(6));
                getSetStudent.setStudent_password(cursor.getString(7));

            } while (cursor.moveToNext());
        }
        return getSetStudent;
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "DELETE FROM student WHERE studentID=" + studentId;

        Log.d("query", query);
        DB.execSQL(query);
        DB.close();
    }

    //SESSION
    public int addSession(SessionGetSet sessionGetSet) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "INSERT INTO attendance_session_table (session_staffID, session_department, session_faculty, session_date, session_subject) values ('" +
                sessionGetSet.getSession_staffID() + "', '" +
                sessionGetSet.getSession_department() + "','" +
                sessionGetSet.getSession_faculty() + "', '" +
                sessionGetSet.getSession_date() + "', '" +
                sessionGetSet.getSession_subject() + "')";
        Log.d("query", query);
        DB.execSQL(query);

        String query1 = "select max(sessionID) from session";
        Cursor cursor = DB.rawQuery(query1, null);

        if (cursor.moveToFirst()) {
            int sessionID = Integer.parseInt(cursor.getString(0));
            return sessionID;
        }
        DB.close();
        return 0;
    }

    public ArrayList<SessionGetSet> getAllSession()
    {
        ArrayList<SessionGetSet> array = new ArrayList<SessionGetSet>();

        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM session";
        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                SessionGetSet sessionGetSet = new SessionGetSet();
                sessionGetSet.setSessionID(Integer.parseInt(cursor.getString(0)));
                sessionGetSet.setSession_staffID(Integer.parseInt(cursor.getString(1)));
                sessionGetSet.setSession_department(cursor.getString(2));
                sessionGetSet.setSession_subject(cursor.getString(3));
                sessionGetSet.setSession_date(cursor.getString(4));
                sessionGetSet.setSession_subject(cursor.getString(5));
                array.add(sessionGetSet);
            }while(cursor.moveToNext());
        }
        return array;
    }

    public void deleteSession(int sessionId) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "DELETE FROM session WHERE sessionID = "+sessionId ;

        Log.d("query", query);
        DB.execSQL(query);
        DB.close();
    }

    //ATTENDANCE
    public void addNewAttendance(AttendanceGetSet attendanceGetSet) {
        SQLiteDatabase DB = this.getWritableDatabase();

        String query = "INSERT INTO attendance values ("+
                attendanceGetSet.getAttendanceID()+", "+
                attendanceGetSet.getAttendance_studentID()+", '"+
                attendanceGetSet.getAttendance_status()+"')";
        Log.d("query", query);
        DB.execSQL(query);
        DB.close();
    }

    public ArrayList<AttendanceGetSet> getAttendanceByID(SessionGetSet sessionGetSet)
    {
        int sessionID = 0;
        ArrayList<AttendanceGetSet> array = new ArrayList<AttendanceGetSet>();

        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM session where session_staffID="+sessionGetSet.getSession_staffID()+""
                +" AND session_department='"+sessionGetSet.getSession_department()+"' AND session_faculty='"+sessionGetSet.getSession_faculty()+"'" +
                " AND session_date='"+sessionGetSet.getSession_date()+"' AND session_subject='"+sessionGetSet.getSession_subject()+"'";
        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                sessionID=(Integer.parseInt(cursor.getString(0)));
            }while(cursor.moveToNext());
        }

        String query1="SELECT * FROM attendance where attendanceID=" + sessionID +" order by attendance_studentID";
        Cursor cursor1 = DB.rawQuery(query1, null);
        if(cursor1.moveToFirst())
        {
            do{
                AttendanceGetSet attendanceGetSet = new AttendanceGetSet();
                attendanceGetSet.setAttendanceID(Integer.parseInt(cursor1.getString(0)));
                attendanceGetSet.setAttendance_studentID(Integer.parseInt(cursor1.getString(1)));
                attendanceGetSet.setAttendance_status(cursor1.getString(2));
                array.add(attendanceGetSet);

            }while(cursor1.moveToNext());
        }
        return array;
    }

    public ArrayList<AttendanceGetSet> getTotalAttendanceBySessionID(SessionGetSet sessionGetSet)
    {
        int sessionId = 0;
        ArrayList<AttendanceGetSet> array = new ArrayList<AttendanceGetSet>();

        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM session where session_staffID="+sessionGetSet.getSession_staffID()+""
                +" AND session_department='"+sessionGetSet.getSession_department()+"' AND session_faculty = '"+sessionGetSet.getSession_faculty()+"'" +
                " AND session_subject = '"+sessionGetSet.getSession_subject()+"'";
        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                sessionId=(Integer.parseInt(cursor.getString(0)));

                String query1="SELECT * FROM attendance where attendanceID = " + sessionId+" order by attendance_studentID";
                Cursor cursor1 = DB.rawQuery(query1, null);
                if(cursor1.moveToFirst())
                {
                    do{
                        AttendanceGetSet attendanceGetSet = new AttendanceGetSet();
                        attendanceGetSet.setAttendanceID(Integer.parseInt(cursor1.getString(0)));
                        attendanceGetSet.setAttendance_studentID(Integer.parseInt(cursor1.getString(1)));
                        attendanceGetSet.setAttendance_status(cursor1.getString(2));
                        array.add(attendanceGetSet);

                    }while(cursor1.moveToNext());
                }

                AttendanceGetSet attendanceGetSet = new AttendanceGetSet();
                attendanceGetSet.setAttendanceID(0);
                attendanceGetSet.setAttendance_status("Date: " + cursor.getString(4));
                array.add(attendanceGetSet);

            }while(cursor.moveToNext());
        }


        return array;
    }

    public ArrayList<AttendanceGetSet> getAllAttendanceByStudent()
    {
        ArrayList<AttendanceGetSet> array = new ArrayList<AttendanceGetSet>();

        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT attendance_studentID,count(*) FROM attendance where attendance_status='P' group by attendance_studentID";

        Log.d("query", query);

        Cursor cursor = DB.rawQuery(query, null);



        if(cursor.moveToFirst())
        {
            do{
                Log.d("studentId","studentId:"+cursor.getString(0)+", Count:"+cursor.getString(1));
                AttendanceGetSet attendanceGetSet = new AttendanceGetSet();
                attendanceGetSet.setAttendance_studentID(Integer.parseInt(cursor.getString(0)));
                attendanceGetSet.setAttendanceID(Integer.parseInt(cursor.getString(1)));
                array.add(attendanceGetSet);

            }while(cursor.moveToNext());
        }
        return array;
    }
}
