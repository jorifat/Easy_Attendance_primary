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

public class StudentSignInActivity extends AppCompatActivity {
    private EditText studentSignInEmailEditText,studentSignInPasswordEditText;
    private TextView studentSignUpTextView;
    private Button studentSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_in);
        studentSignInEmailEditText=findViewById(R.id.studentSignInEmailEditText);
        studentSignInPasswordEditText=findViewById(R.id.studentSignInPasswordEditText);
        studentSignUpTextView=findViewById(R.id.studentSignUpText);
        studentSignInButton=findViewById(R.id.studentSignInButtonId);

        studentSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentSignInActivity.this,StudentRegisterActivity.class));
            }
        });

        studentSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentSignInEmailEditText.setError(null);
                studentSignInPasswordEditText.setError(null);
                String email=studentSignInEmailEditText.getText().toString().trim();
                final String password=studentSignInPasswordEditText.getText().toString().trim();
                if(isValid(email,password))
                {
                    login(email,password);
                }

            }
        });
    }
    private void login(final String email,final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Endpoints.studentSignIn_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Toast.makeText(StudentSignInActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentSignInActivity.this,StudentProfile.class));
                    StudentSignInActivity.this.finish();
                }
                else {
                    Toast.makeText(StudentSignInActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentSignInActivity.this,"Something went wrong: ",Toast.LENGTH_SHORT).show();
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
            studentSignInEmailEditText.setError("Enter an Email Address");
            studentSignInEmailEditText.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS .matcher(email).matches() )
        {
            studentSignInEmailEditText.setError("Enter a valid  Email Address");
            studentSignInEmailEditText.requestFocus();
            return false;
        }
        if(password.isEmpty())
        {
            studentSignInPasswordEditText.setError("Enter a password");
            studentSignInPasswordEditText.requestFocus();
            return false;
        }
        if(password.length()<6)
        {
            studentSignInPasswordEditText.setError("Minimum length of password should be 6");
            studentSignInPasswordEditText.requestFocus();
            return false;
        }

        return true;

    }

    private void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}