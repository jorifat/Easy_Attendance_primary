package com.example.easy_attendence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter3 extends RecyclerView.Adapter<Adapter3.MyViewHOlder> {

private List<Course> CourseList;

public Adapter3( List<Course> courseList) {
        CourseList = courseList;
        }

@NonNull
@Override
public Adapter3.MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout3,parent,false);
        return new MyViewHOlder(view);
        }

@Override
public void onBindViewHolder(@NonNull Adapter3.MyViewHOlder holder, int position) {
        Course course=CourseList.get(position);
        holder.name.setText("Name :"+course.getCourse_name());
        holder.code.setText("Course :"+course.getCode());
        holder.department.setText("Post :"+course.getDepartment());
        holder.teacher.setText("Mobile :"+course.getTeacher());

        }

@Override
public int getItemCount() {
        return CourseList.size();
        }

public class MyViewHOlder extends RecyclerView.ViewHolder {
    private TextView name,code,department,teacher;
    public MyViewHOlder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.courseNameTextViewId);
        code=(TextView)itemView.findViewById(R.id.CourseCodeTextViewId);
        department=(TextView)itemView.findViewById(R.id.courseDepartmentTextViewId);
        teacher=(TextView)itemView.findViewById(R.id.CourseTeacherTextViewId);


    }
}

}

