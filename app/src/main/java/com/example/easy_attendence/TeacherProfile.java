package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherProfile extends AppCompatActivity implements View.OnClickListener {
private Button viewStudentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        viewStudentButton=findViewById(R.id.teacherViewStudentButtonId);

        viewStudentButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.teacherViewStudentButtonId:
                startActivity(new Intent(TeacherProfile.this,ViewStudentActivity.class));
                break;
        }

    }
}