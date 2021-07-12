package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RadioButtonActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Student2> studentList= new ArrayList<>();
    private Adapter4 adapter4;
    private RadioGroup radioGroup;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        recyclerView=findViewById(R.id.recyclerView4Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       adapter4=new Adapter4(studentList);

        recyclerView.setAdapter(adapter4);
    }
}