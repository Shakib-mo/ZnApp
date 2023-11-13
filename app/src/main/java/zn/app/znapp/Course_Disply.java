package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import zn.app.znapp.adapter.Branch_Adapter_Name;
import zn.app.znapp.adapter.Branch_GetSet;
import zn.app.znapp.adapter.Course_Adapter;
import zn.app.znapp.adapter.Course_Name_Adapter;

public class Course_Disply extends AppCompatActivity {
    ImageView bt_back,bt_add;
    RequestQueue requestQueue,requestQueue_branch_name;
    RecyclerView recyclerView,recycler_branch;
    ArrayList<Employ_get_set>arrayList = new ArrayList<>();
    ArrayList<Branch_GetSet>arrayList_br_name = new ArrayList<>();
    Branch_Adapter_Name.ClickListener clickListener;
    Branch_Adapter_Name branch_adapter_name;
    ArrayList<String> branchno=new ArrayList<>();
    Course_Adapter adapter;
    public String Branch_Number;


    void Display_Br_Name(){
        requestQueue_branch_name = Volley.newRequestQueue(Course_Disply.this);
        String Url ="https://zninfotech.com/znapi/branch.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    branchno.clear();
                    arrayList_br_name.clear();
                    for (int i = 0;i<response.length();i++){
                        object= response.getJSONObject(i);
                        arrayList_br_name.add(new Branch_GetSet(object.getString("BranchName")));
                        branchno.add(object.getString("BranchNo"));
                    }
                    branch_adapter_name = new Branch_Adapter_Name(getApplicationContext(),arrayList_br_name,clickListener);
                    recycler_branch.setAdapter(branch_adapter_name);
                    branch_adapter_name.notifyDataSetChanged();
                }catch (Exception e){
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course_Disply.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_branch_name.add(jsonArrayRequest);
    }
    void Disply_Cours(String b_number){
        requestQueue = Volley.newRequestQueue(Course_Disply.this);
        String Url = "https://zninfotech.com/znapi/getcourse.php";
        SharedPreferences preferences = getSharedPreferences("Act", Context.MODE_PRIVATE);
        Branch_Number = preferences.getString("BranchNo",Branch_Number);

        JSONObject object = new JSONObject();
        JSONArray array1 = new JSONArray();
        try {
            object.put("BranchNo",Branch_Number);
            array1.put(object);
        }catch (Exception e){
            e.printStackTrace();}
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, Url, array1, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i=0;i<response.length();i++){
                        object = response.getJSONObject(i);
                        arrayList.add(new Employ_get_set(object.getString("CourseNo"),object.getString("CourseName"),object.getString("Fee"),
                                object.getString("Duration"),object.getString("BranchNo")));
                    }
                    adapter = new Course_Adapter(Course_Disply.this,arrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(Course_Disply.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course_Disply.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_disply);
        bt_back = findViewById(R.id.bt_back);
        bt_add= findViewById(R.id.bt_add);
        recyclerView = findViewById(R.id.recycler_view);
        recycler_branch = findViewById(R.id.recycler_branch);

        recycler_branch.setHasFixedSize(true);
        recycler_branch.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Display_Br_Name();

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.top_to_bottom, R.anim.top_to_bottom);
            }
        });
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course_Disply.this,Course_Edt.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });
        clickListener = new Branch_Adapter_Name.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                arrayList.clear();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Course_Disply.this));
                Disply_Cours(Branch_Number);
            }
        };
    }
}