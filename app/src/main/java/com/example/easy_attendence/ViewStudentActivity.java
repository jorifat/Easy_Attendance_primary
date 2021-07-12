package com.example.easy_attendence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.easy_attendence.Utils.Endpoints;
import com.example.easy_attendence.Utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Student> studentList=new ArrayList<Student>();
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(studentList);

        recyclerView.setAdapter(adapter);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Endpoints.viewStudent_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");

                    for (int i = 0; i < response.length(); i++) {


                        JSONObject object = array.getJSONObject(i);
                        Student student = new Student();
                        student.setName(object.getString("name"));
                        student.setRoll(object.getString("roll"));
                        student.setDepartment(object.getString("department"));
                        student.setMobile(object.getString("mobile"));
                        student.setEmail(object.getString("email"));
                        studentList.add(student);
                        Collections.sort(studentList, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return o1.getRoll().compareTo(o2.getRoll());
                            }
                        });
                        adapter.notifyDataSetChanged();
                    }

                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewStudentActivity.this,"Something went wrong: ", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}