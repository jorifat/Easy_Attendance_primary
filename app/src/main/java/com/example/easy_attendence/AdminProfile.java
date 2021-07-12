package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminProfile extends AppCompatActivity implements View.OnClickListener {
private Button addCourseButton,viewCourseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        addCourseButton=findViewById(R.id.addCourseButtonId);
        viewCourseButton=findViewById(R.id.viewCourseButtonId);

        addCourseButton.setOnClickListener(this);
        viewCourseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addCourseButtonId:
                startActivity(new Intent(AdminProfile.this,AddCourseActivity.class));
                break;

            case R.id.viewCourseButtonId:
                startActivity(new Intent(AdminProfile.this,ViewCourseActivity.class));
                break;
        }

    }
}