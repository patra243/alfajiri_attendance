package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myattendance.Bean.GetSetStaff;
import com.example.myattendance.DB.Adapter;
import com.example.myattendance.DB.DBHelper;

public class FacultyAddActivity extends AppCompatActivity {

    EditText first,last,phone,email,pwd;
    Button submit,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_add);

        first = findViewById(R.id.txtFirst);
        last = findViewById(R.id.txtLast);
        phone = findViewById(R.id.txtPhone);
        email = findViewById(R.id.txtEmail);
        pwd = findViewById(R.id.txtPwd);

        submit = findViewById(R.id.btnSubmit);
        cancel = findViewById(R.id.btnCancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first1 = first.getText().toString();
                String last1 = last.getText().toString();
                String phone1 = phone.getText().toString();
                String email1 = email.getText().toString();
                String pwd1 = pwd.getText().toString();

                if (TextUtils.isEmpty(first1))
                {
                    first.setError("Please Enter Firstname");
                }
                else if (TextUtils.isEmpty(last1))
                {
                    last.setError("Please Enter Lastname");
                } else if (TextUtils.isEmpty(phone1))
                {
                    phone.setError("Please Enter Phone Number");
                }
                else if (TextUtils.isEmpty(email1))
                {
                    email.setError("Please Enter Email Address");
                }
                else if (TextUtils.isEmpty(pwd1))
                {
                    pwd.setError("Please Enter Password");
                }
                else{
                    GetSetStaff getSetStaff = new GetSetStaff();
                    getSetStaff.setStaff_fname(first1);
                    getSetStaff.setStaff_lname(last1);
                    getSetStaff.setStaff_phone(phone1);
                    getSetStaff.setStaff_email(email1);
                    getSetStaff.setStaff_password(pwd1);


                    DBHelper helper = new DBHelper(FacultyAddActivity.this);
                    helper.insertStaff(getSetStaff);


                    Intent intent = new Intent(FacultyAddActivity.this, MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(FacultyAddActivity.this, MenuActivity.class);
                startActivity(intent1);
            }
        });
    }
}