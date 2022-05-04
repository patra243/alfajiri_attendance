package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myattendance.Bean.AttendanceGetSet;
import com.example.myattendance.DB.DBHelper;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView card1, card2, card3, card4, card5, card6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        card1 = (CardView) findViewById(R.id.btnAdd);
        card2 = (CardView) findViewById(R.id.btnFac);
        card3 = (CardView) findViewById(R.id.btnView);
        card4 = (CardView) findViewById(R.id.btnViewFac);
        card5 = (CardView) findViewById(R.id.btnLogout);
        card6 = (CardView) findViewById(R.id.btnAttendance);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.btnAdd :
                i = new Intent(this,StudentAddActivity.class);
                startActivity(i);
                break;

            case R.id.btnView :
                i = new Intent(this,ViewStudentActivity.class);
                startActivity(i);
                break;

            case R.id.btnFac :
                i = new Intent(this,FacultyAddActivity.class);
                startActivity(i);
                break;

            case R.id.btnViewFac :
                i = new Intent(this,FacultyViewActivity.class);
                startActivity(i);
                break;

            case R.id.btnLogout :
                i = new Intent(this,LoginActivity.class);
                startActivity(i);
                break;

            case R.id.btnAttendance :
                DBHelper helper = new DBHelper(MenuActivity.this);
                ArrayList<AttendanceGetSet> attendanceList = helper.getAllAttendanceByStudent();
                ((Context)MenuActivity.this.getApplicationContext()).setAttendanceGetSet(attendanceList);

                i = new Intent(this,ViewAttendanceStudent.class);
                startActivity(i);
                break;
        }
    }
}