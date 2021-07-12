package com.example.easy_attendence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHOlder> {

    private List<Teacher> TeacherList;

    public Adapter2( List<Teacher> teacherList) {
        TeacherList = teacherList;
    }

    @NonNull
    @Override
    public Adapter2.MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout2,parent,false);
        return new MyViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.MyViewHOlder holder, int position) {
        Teacher teacher=TeacherList.get(position);
        holder.name.setText("Name :"+teacher.getName());
        holder.course.setText("Course :"+teacher.getCourse());
        holder.post.setText("Post :"+teacher.getPost());
        holder.mobile.setText("Mobile :"+teacher.getMobile());
        holder.email.setText("Email :"+teacher.getEmail());

    }

    @Override
    public int getItemCount() {
        return TeacherList.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder {
        private TextView name,course,post,mobile,email;
        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.teacherNameTextViewId);
            course=(TextView)itemView.findViewById(R.id.teacherCourseTextViewId);
            post=(TextView)itemView.findViewById(R.id.teacherPostTextViewId);
            mobile=(TextView)itemView.findViewById(R.id.teacherMobileTextViewId);
            email=(TextView)itemView.findViewById(R.id.teacherEmailTextViewId);

        }
    }

}

