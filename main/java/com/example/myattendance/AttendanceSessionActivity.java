package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myattendance.Bean.AttendanceGetSet;
import com.example.myattendance.Bean.GetSetStaff;
import com.example.myattendance.Bean.GetSetStudent;
import com.example.myattendance.Bean.SessionGetSet;
import com.example.myattendance.DB.DBHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class AttendanceSessionActivity extends AppCompatActivity {

    Button add_attendance, view_attendance;
    Button dateButton;
    Spinner spinner_fac, spinner_dep, spinner_sub;

    private DatePickerDialog datePickerDialog;

    String faculty, department, subject;

    ArrayList<String> arrayList_faculty;
    ArrayAdapter<String> arrayAdapter_faculty;

    ArrayList<String> arrayList_science, arrayList_commerce, arrayList_arts;
    ArrayAdapter<String> arrayAdapter_department;

    ArrayList<String> arrayList_computer, arrayList_actuarial, arrayList_chemist
            , arrayList_nursing, arrayList_accounting, arrayList_finance, arrayList_marketing
            , arrayList_hrm, arrayList_politic, arrayList_relation, arrayList_philo
            , arrayList_dev;
    ArrayAdapter<String> arrayAdapter_subject;

    AttendanceSessionActivity attendanceSessionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_session);

        add_attendance = (Button) findViewById(R.id.btnAddAttend);
        view_attendance = (Button) findViewById(R.id.btnViewAttend);
        spinner_fac = (Spinner) findViewById(R.id.spinnerfac);
        spinner_dep = (Spinner) findViewById(R.id.spinnerdep);
        spinner_sub = (Spinner) findViewById(R.id.spinnersub);

        dateButton = (Button) findViewById(R.id.datePicker);
        openDatePicker();
        dateButton.setText(getTodaysDate());

        //Faculty Spinner
        arrayList_faculty = new ArrayList<>();
        arrayList_faculty.add("Science");
        arrayList_faculty.add("Commerce");
        arrayList_faculty.add("Arts & Social Science");

        arrayAdapter_faculty = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item,arrayList_faculty);
        spinner_fac.setAdapter(arrayAdapter_faculty);

        //Department Spinner
        //Science Spinner
        arrayList_science = new ArrayList<>();
        arrayList_science.add("Actuarial Science");
        arrayList_science.add("Chemistry");
        arrayList_science.add("Computer Science");
        arrayList_science.add("Nursing");

        //Commerce Spinner
        arrayList_commerce = new ArrayList<>();
        arrayList_commerce.add("Accounting");
        arrayList_commerce.add("Finance");
        arrayList_commerce.add("Marketing");
        arrayList_commerce.add("Human Resources Management");

        //Arts Spinner
        arrayList_arts = new ArrayList<>();
        arrayList_arts.add("Political Science");
        arrayList_arts.add("International Relations");
        arrayList_arts.add("Psychology");
        arrayList_arts.add("Development Studies");

        //Subject Spinner
        //science Spinner
        //Actuarial Science Spinner
        arrayList_actuarial = new ArrayList<>();
        arrayList_actuarial.add("Integral Calculus");
        arrayList_actuarial.add("Probability and Statistics");

        //Chemistry Spinner
        arrayList_chemist = new ArrayList<>();
        arrayList_chemist.add("Analytical Chemistry");
        arrayList_chemist.add("Applied Organic Chemistry");
        arrayList_chemist.add("Environmental Chemistry");

        //Computer Science Spinner
        arrayList_computer = new ArrayList<>();
        arrayList_computer.add("Human Computer Interface");
        arrayList_computer.add("Computer Architecture");
        arrayList_computer.add("Multi Media Systems");
        arrayList_computer.add("Object Oriented Programming 1");

        //Nursing Spinner
        arrayList_nursing = new ArrayList<>();
        arrayList_nursing.add("Community Health Nursing 1");
        arrayList_nursing.add("Medical Surgical 2");

        arrayList_accounting = new ArrayList<>();
        arrayList_accounting.add("Financial Accounting");
        arrayList_accounting.add("Elements of Accounting");

        arrayList_marketing = new ArrayList<>();
        arrayList_marketing.add("Global Marketing Strategy");
        arrayList_marketing.add("Strategic Marketing Management");

        arrayList_finance = new ArrayList<>();
        arrayList_finance.add("Financial Accounting");
        arrayList_finance.add("Corporate Finance");

        arrayList_hrm = new ArrayList<>();
        arrayList_hrm.add("Human Resource Development");

        arrayList_politic = new ArrayList<>();
        arrayList_politic.add("Politics and Industrialization in Africa");

        arrayList_relation = new ArrayList<>();
        arrayList_relation.add("Diplomacy");

        arrayList_philo = new ArrayList<>();
        arrayList_philo.add("Family Therapy");

        arrayList_dev = new ArrayList<>();
        arrayList_dev.add("Community Development");

        spinner_fac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
                faculty = (String) spinner_fac.getSelectedItem();

                if (position==0){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_science);
                }
                else if (position==1){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_commerce);
                }
                else if (position==2){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_arts);
                }
                spinner_dep.setAdapter(arrayAdapter_department);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_dep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
                department = (String) spinner_dep.getSelectedItem();

                if (position==0)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_computer);
                }
                else if (position==1)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_actuarial);
                }
                else if (position==2)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_chemist);
                }
                else if (position==3)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_nursing);
                }
                else if (position==4)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_accounting);
                }
                else if (position==5)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_finance);
                }
                else if (position==6)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_marketing);
                }
                else if (position==7)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_hrm);
                }
                else if (position==8)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_politic);
                }
                else if (position==9)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_relation);
                }
                else if (position==10)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_philo);
                }
                else if (position==11)
                {
                    arrayAdapter_subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_dev);
                }
                spinner_sub.setAdapter(arrayAdapter_subject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
                subject = (String) spinner_sub.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionGetSet sessionGetSet = new SessionGetSet();
                GetSetStaff staff = ((Context)AttendanceSessionActivity.this.getApplicationContext()).getGetSetStaff();

                sessionGetSet.setSession_staffID(staff.getStaffID());
                sessionGetSet.setSession_faculty(faculty);
                sessionGetSet.setSession_department(department);
                sessionGetSet.setSession_subject(subject);
                sessionGetSet.setSession_date(dateButton.getText().toString());

                DBHelper helper = new DBHelper(AttendanceSessionActivity.this);
                int sessionID = helper.addSession(sessionGetSet);

                ArrayList<GetSetStudent> studentList = helper.getAllStudentBy(faculty, department);
                ((Context)AttendanceSessionActivity.this.getApplicationContext()).setGetSetStudent(studentList);

                Intent intent = new Intent(AttendanceSessionActivity.this, AttendanceAddActivity.class);
                intent.putExtra("sessionID: ", sessionID);
                startActivity(intent);
            }
        });

        view_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionGetSet sessionGetSet = new SessionGetSet();
                GetSetStaff staff = ((Context)AttendanceSessionActivity.this.getApplicationContext()).getGetSetStaff();

                sessionGetSet.setSession_staffID(staff.getStaffID());
                sessionGetSet.setSession_department(department);
                sessionGetSet.setSession_faculty(faculty);
                sessionGetSet.setSession_subject(subject);

                DBHelper helper = new DBHelper(AttendanceSessionActivity.this);
                ArrayList<AttendanceGetSet> attendanceBeanList = helper.getTotalAttendanceBySessionID(sessionGetSet);
                ((Context)AttendanceSessionActivity.this.getApplicationContext()).setAttendanceGetSet(attendanceBeanList);

                Intent intent = new Intent(AttendanceSessionActivity.this,ViewAttendanceStudent.class);
                startActivity(intent);
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private void openDatePicker() {
        datePickerDialog.show();
    }

    public void initDatePicker(View view) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }


}