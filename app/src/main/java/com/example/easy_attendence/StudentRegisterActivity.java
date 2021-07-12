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

public class StudentRegisterActivity extends AppCompatActivity {
    private EditText studentNameEdit,studentDeptEdit,studentPassEdit,studentEmailEdit,studentRollEdit,studentMobileEdit;
    private Button studentSignUp;
    private TextView studentSignInText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        studentNameEdit=findViewById(R.id.student_nameSignUpEditText);
        studentRollEdit=findViewById(R.id.student_rollSignUpEditText);
        studentDeptEdit=findViewById(R.id.student_deptSignUpEditText);
        studentMobileEdit=findViewById(R.id.student_mobileSignUpEditText);
        studentEmailEdit=findViewById(R.id.student_emailSignUpEditText);
        studentPassEdit=findViewById(R.id.student_passwordSignUpEditText);

        studentSignUp=findViewById(R.id.studentSignUpButtonId);
        studentSignInText=findViewById(R.id.studentSignUpText);

        studentSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=studentNameEdit.getText().toString();
                String roll=studentRollEdit.getText().toString();
                String department=studentDeptEdit.getText().toString();
                String mobile=studentMobileEdit.getText().toString();
                String email= studentEmailEdit.getText().toString().trim();
                String password=studentPassEdit.getText().toString().trim();
                if(isValid(name,roll,department,mobile,email,password))
                {
                    register(name,roll,department,mobile,email,password);

                }
            }
        });
        studentSignInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), StudentSignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValid(String name,String roll,String department, String mobile,String email,String password){
        if(name.isEmpty())
        {
            studentNameEdit.setError("Enter Name");
            studentNameEdit.requestFocus();
            return false;
        }
        if(roll.isEmpty())
        {
            studentRollEdit.setError("Enter Roll");
            studentRollEdit.requestFocus();
            return false;
        }

        if(department.isEmpty())
        {
            studentDeptEdit.setError("Enter Department");
            studentDeptEdit.requestFocus();
            return false;
        }

        if(mobile.isEmpty())
        {
            studentMobileEdit.setError("Enter Mobile No");
            studentMobileEdit.requestFocus();
            return false;
        }

        if(email.isEmpty())
        {
            studentEmailEdit.setError("Enter an Email Address");
            studentEmailEdit.requestFocus();
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            studentEmailEdit.setError("Enter a valid Email Address");
            studentEmailEdit.requestFocus();
            return false;

        }

        if(password.isEmpty())
        {
            studentPassEdit.setError("Enter a password");
            studentPassEdit.requestFocus();
            return false;
        }
        if(password.length()<6)
        {
            studentPassEdit.setError("Minimum length of password should be 6");
            studentPassEdit.requestFocus();
            return false;
        }

        return true;
    }
    private void register(final String name,final String roll,final String department,final String mobile, final String email, final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Endpoints.addStudent_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Toast.makeText(StudentRegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentRegisterActivity.this,StudentProfile.class));
                    StudentRegisterActivity.this.finish();
                }
                else {
                    Toast.makeText(StudentRegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentRegisterActivity.this,"Something went wrong: ",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params=new HashMap<>();
                params.put("name",name);
                params.put("roll",roll);
                params.put("department",department);
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
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}