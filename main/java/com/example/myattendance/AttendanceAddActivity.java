package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myattendance.Bean.AttendanceGetSet;
import com.example.myattendance.Bean.GetSetStudent;
import com.example.myattendance.DB.DBHelper;

import java.util.ArrayList;

public class AttendanceAddActivity extends AppCompatActivity {

    ArrayList<GetSetStudent> getStudent;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    int sessionID = 0;
    String status = "Present";
    Button AttendanceSubmit;

    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        sessionID = getIntent().getExtras().getInt("sessionID");
        listView = (ListView) findViewById(R.id.listView1);
        final ArrayList<String> studentBeanList = new ArrayList<>();
        getStudent = ((Context)AttendanceAddActivity.this.getApplicationContext()).getGetSetStudent();
        for (GetSetStudent getSetStudent : getStudent)
        {
            String users = getSetStudent.getStudent_fname()+" "+getSetStudent.getStudent_lname();
            studentBeanList.add(users);
            Log.d("users: ",users);
        }

        listAdapter = new ArrayAdapter<>(this, R.layout.activity_attendance_add, R.id.labelA, studentBeanList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int place, long l) {
                adapterView.getChildAt(place).setBackgroundColor(Color.TRANSPARENT);
                view.setBackgroundColor(334455);
                GetSetStudent getSetStudent = getStudent.get(place);
                Dialog dialog = new Dialog(AttendanceAddActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.trial_layout);

                RadioGroup radioGroup;
                RadioButton present, absent;
                radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                present = (RadioButton) dialog.findViewById(R.id.presentRBtn);
                absent = (RadioButton) dialog.findViewById(R.id.absentRBtn);

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int verify) {
                        if (verify == R.id.presentRBtn)
                        {
                            status = "Present";
                        }
                        else if (verify == R.id.absentRBtn)
                        {
                            status = "Absent";
                        }
                        else{}
                    }
                });
                AttendanceSubmit = (Button) dialog.findViewById(R.id.attendBtnSubmit);
                AttendanceSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AttendanceGetSet attendanceGetSet = new AttendanceGetSet();
                        attendanceGetSet.setAttendanceID(sessionID);
                        attendanceGetSet.setAttendance_studentID(getSetStudent.getStudentID());
                        attendanceGetSet.setAttendance_status(status);

                        DBHelper helper = new DBHelper(AttendanceAddActivity.this);
                        helper.addNewAttendance(attendanceGetSet);

                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }
}