package com.example.myattendance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myattendance.Bean.GetSetStudent;
import com.example.myattendance.DB.DBHelper;

import java.util.ArrayList;

public class ViewStudentBy extends Activity {

    ArrayList<GetSetStudent> studentList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    String faculty, department;

    DBHelper helper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        listView = (ListView) findViewById(R.id.listView1);
        ArrayList<String> studentBeanList = new ArrayList<String>();
        faculty = getIntent().getExtras().getString("faculty");
        department = getIntent().getExtras().getString("department");

        studentList = helper.getAllStudentBy(faculty, department);

        for (GetSetStudent getSetStudent : studentList)
        {
            String users = "Firstname: " + getSetStudent.getStudent_fname()+
                    "\nLastname: "+ getSetStudent.getStudent_lname()+
                    "\n Password: "+ getSetStudent.getStudent_password();
            studentBeanList.add(users);
            Log.d("users: ", users);
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_student_list, R.id.labelo, studentBeanList);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int place, long l)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentBy.this);
                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        studentBeanList.remove(place);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        helper.deleteStudent(studentList.get(place).getStudentID());
                        studentList = helper.getAllStudentBy(faculty, department);

                        for (GetSetStudent getSetStudent : studentList)
                        {
                            String users = " FirstName: " + getSetStudent.getStudent_fname()+"\n LastName: "
                                    +getSetStudent.getStudent_lname()+"\n Password: " + getSetStudent.getStudent_password();
                            studentBeanList.add(users);
                            Log.d("users: ", users);
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return false;
            }
        });
    }
}
