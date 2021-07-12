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

public class SignInActivity extends AppCompatActivity {
    private EditText signInEmailEditText,signInPasswordEditText;
    private TextView signUpTextView;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInEmailEditText=findViewById(R.id.adminSignInEmailEditText);
        signInPasswordEditText=findViewById(R.id.adminSignInPasswordEditText);
        signUpTextView=findViewById(R.id.adminSignUpText);
        signInButton=findViewById(R.id.adminSignInButtonId);

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,RegisterActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInEmailEditText.setError(null);
                signInPasswordEditText.setError(null);
                String email=signInEmailEditText.getText().toString().trim();
                final String password=signInPasswordEditText.getText().toString().trim();
                if(isValid(email,password))
                {
                    login(email,password);

                }

            }
        });
    }

    private void login(final String email,final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Endpoints.login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Toast.makeText(SignInActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this,AdminProfile.class));
                    SignInActivity.this.finish();
                }
                else {
                    Toast.makeText(SignInActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignInActivity.this,"Something went wrong: ",Toast.LENGTH_SHORT).show();
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
            signInEmailEditText.setError("Enter an Email Address");
            signInEmailEditText.requestFocus();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS .matcher(email).matches() )
        {
            signInEmailEditText.setError("Enter a valid  Email Address");
            signInEmailEditText.requestFocus();
            return false;
        }
        if(password.isEmpty())
        {
            signInPasswordEditText.setError("Enter a password");
            signInPasswordEditText.requestFocus();
            return false;
        }
        if(password.length()<6)
        {
            signInPasswordEditText.setError("Minimum length of password should be 6");
            signInPasswordEditText.requestFocus();
            return false;
        }

        return true;

    }

    private void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}