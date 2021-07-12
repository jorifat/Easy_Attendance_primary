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

public class RegisterActivity extends AppCompatActivity {
private EditText adminNameEdit,adminEmailEdit,adminPassEdit;
private Button adminSignUp;
private TextView adminSignInText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        adminNameEdit=findViewById(R.id.admin_nameSignupEditText);
        adminEmailEdit=findViewById(R.id.admin_emailSignUpEditText);
        adminPassEdit=findViewById(R.id.admin_passwordSignUpEditText);
        adminSignUp=findViewById(R.id.adminSignUpButtonId);
        adminSignInText=findViewById(R.id.adminSignInText);

        adminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=adminNameEdit.getText().toString();
                String email= adminEmailEdit.getText().toString().trim();
                String password=adminPassEdit.getText().toString().trim();
                if(isValid(name,email,password))
                {
                    register(name,email,password);

                }
            }
        });
        adminSignInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean isValid(String name,String email,String password){
        if(name.isEmpty())
        {
            adminNameEdit.setError("Enter Name");
            adminNameEdit.requestFocus();
            return false;
        }

        if(email.isEmpty())
        {
            adminEmailEdit.setError("Enter an Email Address");
            adminEmailEdit.requestFocus();
            return false;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            adminEmailEdit.setError("Enter a valid Email Address");
            adminEmailEdit.requestFocus();
            return false;

        }

        if(password.isEmpty())
        {
            adminPassEdit.setError("Enter a password");
            adminPassEdit.requestFocus();
            return false;
        }
        if(password.length()<6)
        {
            adminPassEdit.setError("Minimum length of password should be 6");
            adminPassEdit.requestFocus();
            return false;
        }

        return true;
    }
    private void register(final String name, final String email, final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success"))
                {
                    Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,AdminProfile.class));
                    RegisterActivity.this.finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,"Something went wrong: ",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params=new HashMap<>();
                params.put("name",name);
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
