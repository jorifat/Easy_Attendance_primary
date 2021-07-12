package com.example.easy_attendence;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static java.lang.String.valueOf;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHOlder> {

    private List<Student> StudentList;

    public Adapter( List<Student> studentList) {
        StudentList = studentList;
    }

    @NonNull
    @Override
    public MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout,parent,false);
        return new MyViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHOlder holder, int position) {
        Student student=StudentList.get(position);
        holder.name.setText("Name :"+student.getName());
        holder.roll.setText("Roll :"+student.getRoll());
        holder.department.setText("Department :"+student.getDepartment());
        holder.mobile.setText("Mobile :"+student.getMobile());
        holder.email.setText("Email :"+student.getEmail());

    }

    @Override
    public int getItemCount() {
        return StudentList.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder {
        private TextView name,roll,department,mobile,email;
        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.studentnameTextViewId);
            roll=(TextView)itemView.findViewById(R.id.studentrollTextViewId);
            department=(TextView)itemView.findViewById(R.id.studentdeptTextViewId);
            mobile=(TextView)itemView.findViewById(R.id.studentMobileTextViewId);
            email=(TextView)itemView.findViewById(R.id.studentEmailTextViewId);

        }
    }

}
