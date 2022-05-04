package com.example.myattendance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myattendance.Bean.GetSetStaff;
import com.example.myattendance.DB.DBHelper;

import java.util.ArrayList;

public class FacultyViewActivity extends AppCompatActivity {

    ArrayList<GetSetStaff> staffList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        listView = (ListView) findViewById(R.id.listView1);
        final ArrayList<String> staffAList = new ArrayList<String>();

        staffList = helper.getAllStaff();
        for (GetSetStaff getSetStaff : staffList)
        {
            String users = "Firstname: " + getSetStaff.getStaff_fname()+
                    "\nLastname: "+ getSetStaff.getStaff_fname()+
                    "\n Password: "+ getSetStaff.getStaff_password();

            staffAList.add(users);
            Log.d("users: ", users);
        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.activity_faculty_view, R.id.labela, staffAList);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int place, long l) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FacultyViewActivity.this);
                alertDialogBuilder.setTitle(getTitle()+"Decision");
                alertDialogBuilder.setMessage("Are you sure");

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        staffAList.remove(place);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        helper.deleteFaculty(staffList.get(place).getStaffID());
                        staffList = helper.getAllStaff();

                        for (GetSetStaff getSetStaff : staffList)
                        {
                            String users = "Firstname: " + getSetStaff.getStaff_fname()+
                                    "\nLastname: "+ getSetStaff.getStaff_lname()+
                                    "\n Password: "+ getSetStaff.getStaff_password();
                            staffAList.add(users);
                            Log.d("users: ", users);
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Toast.makeText(getApplicationContext(), "You chose to cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return false;
            }
        });
    }
}