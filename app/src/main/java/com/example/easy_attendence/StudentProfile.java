package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentProfile extends AppCompatActivity implements View.OnClickListener {
private Button viewCoursesButton,viewTeacherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        viewCoursesButton=findViewById(R.id.studentViewCourseButtonId);
        viewTeacherButton=findViewById(R.id.viewTeacherButtonId);

        viewCoursesButton.setOnClickListener(this);
        viewTeacherButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
                case R.id.viewTeacherButtonId:
                    startActivity(new Intent(StudentProfile.this,ViewTeacherActivity.class));
                    break;
                case R.id.studentViewCourseButtonId:
                    startActivity(new Intent(StudentProfile.this,ViewCourseActivity.class));
                    break;
        }

    }
}