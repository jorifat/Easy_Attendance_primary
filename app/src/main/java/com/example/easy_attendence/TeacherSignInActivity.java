package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

public class TeacherSignInActivity extends AppCompatActivity {

    private EditText teacherSignInEmailEditText,teacherSignInPasswordEditText;
    private TextView teacherSignUpTextView;
    private Button teacherSignInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_in);

        teacherSignInEmailEditText=findViewById(R.id.teacherSignInEmailEditText);
        teacherSignInPasswordEditText=findViewById(R.id.teacherSignInPasswordEditText);
        teacherSignUpTextView=findViewById(R.id.teacherSignUpText);
        teacherSignInButton=findViewById(R.id.teacherSignInButtonId);

        teacherSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherSignInActivity.this,TeacherRegister.class));
            }
        });

        teacherSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherSignInEmailEditText.setError(null);
                teacherSignInPasswordEditText.setError(null);
                String email=teacherSignInEmailEditText.getText().toString().trim();
                final String password=teacherSignInPasswordEditText.getText().toString().trim();
                if(isValid(email,password))
                {
                    login(email,password);

                }

            }
        });
    }

    private void login(final String email,final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Endpoints.teacherSignIn_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Toast.makeText(TeacherSignInActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TeacherSignInActivity.this,TeacherProfile.class));
                    TeacherSignInActivity.this.finish();
                }
                else {
                    Toast.makeText(TeacherSignInActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TeacherSignInActivity.this,"Something went wrong: ",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params=new HashMap<>();

                params.put("email",email);
                params.put("password",password);

                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
    private boolean isValid(String email,String password)
    {
        if(email.isEmpty())
        {
            teacherSignInEmailEditText.setError("Enter an Email Address");
            teacherSignInEmailEditText.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS .matcher(email).matches() )
        {
            teacherSignInEmailEditText.setError("Enter a valid  Email Address");
            teacherSignInEmailEditText.requestFocus();
            return false;
        }
        if(password.isEmpty())
        {
            teacherSignInPasswordEditText.setError("Enter a password");
            teacherSignInPasswordEditText.requestFocus();
            return false;
        }
        if(password.length()<6)
        {
            teacherSignInPasswordEditText.setError("Minimum length of password should be 6");
            teacherSignInPasswordEditText.requestFocus();
            return false;
        }

        return true;

    }

    private void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}