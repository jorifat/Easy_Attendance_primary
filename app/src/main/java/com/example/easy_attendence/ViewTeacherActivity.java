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

public class ViewTeacherActivity extends AppCompatActivity {

    private RecyclerView recyclerView2;
    private List<Teacher> teacherList=new ArrayList<Teacher>();
    private Adapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teacher);

        recyclerView2=findViewById(R.id.recyclerView2Id);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter2=new Adapter2(teacherList);
        recyclerView2.setAdapter(adapter2);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Endpoints.viewTeachers_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("data");

                    for (int i = 0; i < response.length(); i++) {


                        JSONObject object = array.getJSONObject(i);
                        Teacher teacher = new Teacher();
                        teacher.setName(object.getString("name"));
                        teacher.setCourse(object.getString("course"));
                        teacher.setPost(object.getString("post"));
                        teacher.setMobile(object.getString("mobile"));
                        teacher.setEmail(object.getString("email"));
                        teacherList.add(teacher);
                        adapter2.notifyDataSetChanged();
                    }
                    recyclerView2.setAdapter(adapter2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewTeacherActivity.this,"Something went wrong: ", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", "" +error.getMessage());

            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}