package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.easy_attendence.Utils.Endpoints;
import com.example.easy_attendence.Utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class AddCourseActivity extends AppCompatActivity {
    private EditText courseNameEdit,courseDeptEdit,courseCodeEdit,courseTeacherEdit;
    private Button addCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseNameEdit=findViewById(R.id.course_nameSignUpEditText);
        courseCodeEdit=findViewById(R.id.course_CodeSignUpEditText);
        courseDeptEdit=findViewById(R.id.course_deptEditText);
        courseTeacherEdit=findViewById(R.id.course_teacherEditText);
        addCourse=findViewById(R.id.addCourseButtonId);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=courseNameEdit.getText().toString();
                String code=courseCodeEdit.getText().toString();
                String department=courseDeptEdit.getText().toString();
                String teacher=courseTeacherEdit.getText().toString();

                if(isValid(name,code,department,teacher))
                {
                    register(name,code,department,teacher);

                }
            }
        });

    }
    private boolean isValid(String name,String code,String department, String teacher){
        if(name.isEmpty())
        {
            courseNameEdit.setError("Enter Name");
            courseNameEdit.requestFocus();
            return false;
        }
        if(code.isEmpty())
        {
            courseCodeEdit.setError("Enter code");
            courseCodeEdit.requestFocus();
            return false;
        }

        if(department.isEmpty())
        {
            courseDeptEdit.setError("Enter Department");
            courseDeptEdit.requestFocus();
            return false;
        }

        if(teacher.isEmpty())
        {
            courseTeacherEdit.setError("Enter Teacher Name");
            courseTeacherEdit.requestFocus();
            return false;
        }
        return true;
    }
    private void register(final String name,final String code,final String department,final String teacher) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Endpoints.addCourses_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Toast.makeText(AddCourseActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddCourseActivity.this,AdminProfile.class));
                    AddCourseActivity.this.finish();
                }
                else {
                    Toast.makeText(AddCourseActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCourseActivity.this,"Something went wrong: ",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params=new HashMap<>();
                params.put("name",name);
                params.put("code",code);
                params.put("department",department);
                params.put("teacher",teacher);

                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
    private void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}