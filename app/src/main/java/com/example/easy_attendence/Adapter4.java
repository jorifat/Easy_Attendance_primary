package com.example.easy_attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter4 extends RecyclerView.Adapter<Adapter4.MyViewHOlder> {

    private List<Student2> StudentList;
    private Context context;
    private Context ctx;
    private RadioGroup lastCheckedRadioGroup=null;


    public Adapter4(List<Student2> studentList) {
        StudentList = studentList;
        context = ctx;
    }

    @NonNull
    @Override
    public Adapter4.MyViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.radio_button_layout,parent,false);
        return new MyViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter4.MyViewHOlder holder, final int position) {
        Student2 student=StudentList.get(position);
        holder.name.setText("Name :"+student.getName());
        holder.roll.setText("Roll :"+student.getRoll());
        holder.department.setText("Department :"+student.getDepartment());
        holder.present.setSelected(StudentList.get(position).isSelected());
        holder.present.setTag(StudentList.get(position));

        holder.absent.setSelected(StudentList.get(position).isSelected());
        holder.absent.setTag(StudentList.get(position));

        holder.radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup=(RadioGroup) v;
                int selbutid=radioGroup.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton) radioGroup.getChildAt(selbutid);
                Toast.makeText(v.getContext(), " "+rb.getText()+"" ,Toast.LENGTH_SHORT).show();


            }
        });

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton cb=(RadioButton) v;

                Student2 contact=(Student2) cb.getTag();

                contact.setSelected(cb.isSelected());
                StudentList.get(position).setSelected(cb.isSelected()
                );
            }
        });
        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton cb=(RadioButton) v;
                Student2 contact=(Student2) cb.getTag();
                contact.setSelected(cb.isSelected());
                StudentList.get(position).setSelected(cb.isSelected()
                );
            }
        });






    }

    @Override
    public int getItemCount() {
        return StudentList.size();
    }

    public class MyViewHOlder extends RecyclerView.ViewHolder {
        private RadioGroup radioGroup;
        public RadioButton present,absent;
        private TextView name,roll,department,title;
        public Student2 singleStudent;
        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.studentnameTextViewId);
            roll=(TextView)itemView.findViewById(R.id.studentrollTextViewId);
            department=(TextView)itemView.findViewById(R.id.studentdeptTextViewId);
            title=(TextView)itemView.findViewById(R.id.attendanceTextId);
            present = (RadioButton) itemView
                    .findViewById(R.id.radio_present);
            absent = (RadioButton) itemView
                    .findViewById(R.id.radio_absent);
            radioGroup = (RadioGroup)itemView.findViewById(R.id.attendanceTakeId);
        }
    }

}

