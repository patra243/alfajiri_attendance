package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myattendance.Bean.SessionGetSet;
import com.example.myattendance.DB.DBHelper;

import java.util.ArrayList;

public class TrialActivity extends AppCompatActivity {

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        submit = (Button) findViewById(R.id.Btn2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper helper = new DBHelper(TrialActivity.this);
                SessionGetSet sessionGetSet = new SessionGetSet();
                sessionGetSet.setSession_staffID(1);
                sessionGetSet.setSession_faculty("Science");
                sessionGetSet.setSession_department("Computer Science");
                sessionGetSet.setSession_subject("HCI");
                sessionGetSet.setSession_date("01/01/2000");

                helper.addSession(sessionGetSet);
                Log.d("Add", "Inserted");

                ArrayList<SessionGetSet> sessionList = helper.getAllSession();
                for (SessionGetSet sessionGetSet1 : sessionList)
                {
                    Log.d("For", "In for loop");
                    int session = sessionGetSet1.getSessionID();
                    int faculty = sessionGetSet1.getSession_staffID();
                    String classroom = sessionGetSet1.getSession_faculty();
                    String department = sessionGetSet1.getSession_department();
                    String subject = sessionGetSet1.getSession_subject();
                    String date = sessionGetSet1.getSession_date();

                    Log.d("ID", session+"");
                    Log.d("Faculty", faculty+"");
                    Log.d("Classroom", classroom);
                    Log.d("Department", department);
                    Log.d("Subject", subject);
                    Log.d("Date", date);
                }
            }
        });
    }
}