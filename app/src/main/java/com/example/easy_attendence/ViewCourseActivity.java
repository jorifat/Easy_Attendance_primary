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
import java.util.List;

public class ViewCourseActivity extends AppCompatActivity {
    private RecyclerView recyclerView3;
    private List<Course> courseList=new ArrayList<Course>();
    private Adapter3 adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        recyclerView3=findViewById(R.id.recyclerView3Id);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));
        adapter3=new Adapter3(courseList);
        recyclerView3.setAdapter(adapter3);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, Endpoints.viewCourses_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");

                    for (int i = 0; i < response.length(); i++) {


                        JSONObject object = array.getJSONObject(i);
                        Course course = new Course();
                        course.setCourse_name(object.getString("name"));
                        course.setCode(object.getString("code"));
                        course.setDepartment(object.getString("department"));
                        course.setTeacher(object.getString("teacher"));
                        courseList.add(course);
                        adapter3.notifyDataSetChanged();
                    }
                    recyclerView3.setAdapter(adapter3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewCourseActivity.this,"Something went wrong: ", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}