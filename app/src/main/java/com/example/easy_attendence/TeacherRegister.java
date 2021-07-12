package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class TeacherRegister extends AppCompatActivity {
    private EditText teacherNameEdit,teacherPostEdit,teacherPassEdit,teacherEmailEdit,teacherCourseEdit,teacherMobileEdit;
    private Button teacherSignUp;
    private TextView teacherSignInText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        teacherNameEdit=findViewById(R.id.teacher_nameSignUpEditText);
        teacherCourseEdit=findViewById(R.id.teacher_courseSignUpEditText);
        teacherPostEdit=findViewById(R.id.teacher_postSignUpEditText);
        teacherMobileEdit=findViewById(R.id.teacher_mobileSignUpEditText);
        teacherEmailEdit=findViewById(R.id.teacher_emailSignUpEditText);
        teacherPassEdit=findViewById(R.id.teacher_passwordSignUpEditText);
        teacherSignInText=findViewById(R.id.teacherSignInText);
        teacherSignUp=findViewById(R.id.teacherSignUpButtonId);

        teacherSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=teacherNameEdit.getText().toString();
                String course=teacherCourseEdit.getText().toString();
                String post=teacherPostEdit.getText().toString();
                String mobile=teacherMobileEdit.getText().toString();
                String email= teacherEmailEdit.getText().toString().trim();
                String password=teacherPassEdit.getText().toString().trim();
                if(isValid(name,course,post,mobile,email,password))
                {
                    register(name,course,post,mobile,email,password);

                }
            }
        });
        teacherSignInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), TeacherSignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValid(String name,String course,String post, String mobile,String email,String password){
        if(name.isEmpty())
        {
            teacherNameEdit.setError("Enter Name");
            teacherNameEdit.requestFocus();
            return false;
        }
        if(course.isEmpty())
        {
            teacherCourseEdit.setError("Enter course");
            teacherCourseEdit.requestFocus();
            return false;
        }

        if(post.isEmpty())
        {
            teacherPostEdit.setError("Enter Post");
            teacherPostEdit.requestFocus();
            return false;
        }

        if(mobile.isEmpty())
        {
            teacherMobileEdit.setError("Enter Mobile No");
            teacherMobileEdit.requestFocus();
            return false;
        }

        if(email.isEmpty())
        {
            teacherEmailEdit.setError("Enter an Email Address");
            teacherEmailEdit.requestFocus();
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            teacherEmailEdit.setError("Enter a valid Email Address");
            teacherEmailEdit.requestFocus();
            return false;

        }

        if(password.isEmpty())
        {
            teacherPassEdit.setError("Enter a password");
            teacherPassEdit.requestFocus();
            return false;
        }
        if(password.length()<6)
        {
            teacherPassEdit.setError("Minimum length of password should be 6");
            teacherPassEdit.requestFocus();
            return false;
        }

        return true;
    }
    private void register(final String name,final String course,final String post,final String mobile, final String email, final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Endpoints.addTeacher_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Toast.makeText(TeacherRegister.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TeacherRegister.this,TeacherProfile.class));
                    TeacherRegister.this.finish();
                }
                else {
                    Toast.makeText(TeacherRegister.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TeacherRegister.this,"Something went wrong: ",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params=new HashMap<>();
                params.put("name",name);
                params.put("course",course);
                params.put("post",post);
                params.put("mobile",mobile);
                params.put("email",email);
                params.put("password",password);
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