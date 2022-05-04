package com.example.myattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myattendance.Bean.GetSetStaff;
import com.example.myattendance.DB.DBHelper;


public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText username, passwd;
    Spinner spinn;
    String userrole;
    private String[] userRoleString = new String[] {"Admin", "Faculty", "Student"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        login = findViewById(R.id.btnLogin);
        username = findViewById(R.id.txtEmail);
        passwd = findViewById(R.id.txtPass);
        spinn = findViewById(R.id.spin);

        spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO Auto generated method stub
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                userrole = (String) spinn.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> ada_role = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        ada_role.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(ada_role);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userrole.equals("Admin"))
                {
                    String user_name = username.getText().toString();
                    String pass_word = passwd.getText().toString();
                    if (TextUtils.isEmpty(user_name))
                    {
                        username.setError("Username does not exist");
                    }
                    else if (TextUtils.isEmpty(pass_word))
                    {
                        passwd.setError("Incorrect Password");
                    }
                    else
                    {
                        if (user_name.equals("admin") & pass_word.equals("Admin321")){
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                else if (userrole.equals("Faculty"))
                {
                    String user_name = username.getText().toString();
                    String pass_word = passwd.getText().toString();

                    if (TextUtils.isEmpty(user_name))
                    {
                        username.setError("Username does not exist");
                    }
                    else if(TextUtils.isEmpty(pass_word))
                    {
                        passwd.setError("Incorrect Password");
                    }
                    DBHelper helper = new DBHelper(LoginActivity.this);
                    GetSetStaff getSetStaff = helper.validateStaff(user_name, pass_word);

                    if (getSetStaff!=null)
                    {
                        Intent intent1 = new Intent(LoginActivity.this, AttendanceSessionActivity.class);
                        startActivity(intent1);
                        ((Context)LoginActivity.this.getApplicationContext()).setGetSetStaff(getSetStaff);
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}