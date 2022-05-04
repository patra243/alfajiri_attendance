package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewStudentActivity extends AppCompatActivity {

    Spinner spinnerfac, spinnerdep;
    ArrayList<String> arrayList_faculty;
    ArrayAdapter<String> arrayAdapter_faculty;

    ArrayList<String> arrayList_science, arrayList_commerce, arrayList_arts;
    ArrayAdapter<String> arrayAdapter_department;

    String faculty, department;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        spinnerfac = (Spinner) findViewById(R.id.spinnerFac);
        spinnerdep = (Spinner) findViewById(R.id.spinnerDep);
        submit = (Button) findViewById(R.id.btnSubmit2);

        //Faculty Spinner
        arrayList_faculty = new ArrayList<>();
        arrayList_faculty.add("Science");
        arrayList_faculty.add("Commerce");
        arrayList_faculty.add("Arts & Social Science");

        arrayAdapter_faculty = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item,arrayList_faculty);
        spinnerfac.setAdapter(arrayAdapter_faculty);

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
        arrayList_arts.add("Philosophy");
        arrayList_arts.add("Development Studies");

        spinnerfac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(15);
                faculty = (String) spinnerfac.getSelectedItem();

                if (position==0){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_science);
                }
                else if (position==1){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_commerce);
                }
                else if (position==2){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_arts);
                }
                spinnerdep.setAdapter(arrayAdapter_department);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerdep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) adapterView.getChildAt(0)).setTextSize(15);
                department = (String) spinnerdep.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewStudentActivity.this, ViewStudentBy.class);
                intent.putExtra("Faculty", faculty);
                intent.putExtra("Department", department);
                startActivity(intent);
            }
        });
    }
}