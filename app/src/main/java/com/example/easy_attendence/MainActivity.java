package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button studentButton, teacherButton, adminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminButton = findViewById(R.id.adminButtonId);
        studentButton = findViewById(R.id.studentButtonId);
        teacherButton = findViewById(R.id.teacherButtonId);

        adminButton.setOnClickListener(this);
        studentButton.setOnClickListener(this);
        teacherButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adminButtonId:
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                break;
            case R.id.studentButtonId:
                startActivity(new Intent(MainActivity.this, StudentSignInActivity.class));
                break;
            case R.id.teacherButtonId:
                startActivity(new Intent(MainActivity.this, TeacherSignInActivity.class));
                break;
        }
    }

}