package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
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
import zn.app.znapp.adapter.Course_Name_Adapter;
import zn.app.znapp.adapter.Enqury_Adapter;

public class Enqury_Display extends AppCompatActivity {

    ImageView bt_add,bt_back;
    RecyclerView recyclerView,recyclerView_co,recycler_view_br_name;
    RequestQueue requestQueue,requestQueue_co,requestQueue_branch_name;
    Enqury_Adapter adapter;
    Course_Name_Adapter adapter_co;
    public Course_Name_Adapter.ClickListener listener;
    ArrayList<Employ_get_set> arrayList = new ArrayList<>();
    ArrayList<Employ_get_set> arrayList_co = new ArrayList<>();
    ArrayList<Branch_GetSet> arrayList_br_name = new ArrayList<>();
    Branch_Adapter_Name enquiryBranch_adapter_name;
    Course_Name_Adapter.ClickListener clickListener_branch;
    TextView te_branch_name,te_total_course;
    ArrayList<String> branchno=new ArrayList<>();
    ArrayList<String>array_courseNo = new ArrayList<>();
    Enqury_Adapter.ClickListner click_enquiry;


    void Display_Br_Name(){
        requestQueue_branch_name = Volley.newRequestQueue(Enqury_Display.this);
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
                    enquiryBranch_adapter_name = new Branch_Adapter_Name(Enqury_Display.this,arrayList_br_name,clickListener_branch);
                    recycler_view_br_name.setAdapter(enquiryBranch_adapter_name);
                    enquiryBranch_adapter_name.notifyDataSetChanged();
                }catch (Exception e){
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enqury_Display.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_branch_name.add(jsonArrayRequest);
    }



    void DisPaly_Enqury(String course_no){
        requestQueue = Volley.newRequestQueue(Enqury_Display.this);
        String Url = "https://zninfotech.com/znapi/enquiry.php";

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            object.put("CourseNo",course_no);
            array.put(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, course_no, Toast.LENGTH_SHORT).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i =0;i<response.length();i++){
                        object=response.getJSONObject(i);
                        arrayList.add(new Employ_get_set(object.getString("BranchNo"),object.getString("Name"),
                                object.getString("DOE"),object.getString("CourseFor"), object.getString("TotPerson")
                                ,object.getString("Gender"),object.getString("Course"),object.getString("Mobile"),
                                object.getString("Description"),object.getString("College")));
                    }
                    adapter = new Enqury_Adapter(Enqury_Display.this,arrayList,click_enquiry);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(Enqury_Display.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enqury_Display.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }



    void DisPaly_course(String br_no){
        requestQueue_co = Volley.newRequestQueue(Enqury_Display.this);
        String Url = "https://zninfotech.com/znapi/getcourse.php";

        JSONObject object = new JSONObject();
        JSONArray array1 = new JSONArray();
        try {
            object.put("BranchNo",br_no);
            array1.put(object);
        }catch (Exception e){
            e.printStackTrace();}

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, Url, array1, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i =0;i<response.length();i++){
                        object=response.getJSONObject(i);
                        arrayList_co.add(new Employ_get_set(object.getString("CourseName")));
                        array_courseNo.add(object.getString("CourseNo"));
                    }
                    adapter_co = new Course_Name_Adapter(Enqury_Display.this,arrayList_co,listener);
                    recyclerView_co.setAdapter(adapter_co);
                    adapter_co.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(Enqury_Display.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enqury_Display.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_co.add(jsonArrayRequest);
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enqury_display);

        bt_back = findViewById(R.id.bt_back);
        bt_add = findViewById(R.id.bt_add);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView_co = findViewById(R.id.recycler_course);
        recycler_view_br_name = findViewById(R.id.recycler_view_br_name);
        te_branch_name = findViewById(R.id.te_branch_name);
        te_total_course = findViewById(R.id.te_total_course);

        recycler_view_br_name.setHasFixedSize(true);
        recycler_view_br_name.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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
                Intent intent = new Intent(Enqury_Display.this,Enquiry.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);

            }
        });
        clickListener_branch = new Course_Name_Adapter.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                arrayList_co.clear();
                te_branch_name.setText("");
                recyclerView_co.setHasFixedSize(true);
                recyclerView_co.setLayoutManager(new LinearLayoutManager(Enqury_Display.this));
                te_branch_name.setText(arrayList_br_name.get(position).getBranch_name());
                DisPaly_course(branchno.get(position));


            }
        };
        listener =new Course_Name_Adapter.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                arrayList.clear();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Enqury_Display.this));
                DisPaly_Enqury(array_courseNo.get(position));
            }
        };

        click_enquiry = new Enqury_Adapter.ClickListner() {
            @Override
            public void onItemClick(int position, View view) {
                Dialog dialog = new Dialog(Enqury_Display.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.text_view_enqury);
                dialog.getWindow().setLayout(1000 ,1000);

                dialog.show();

                TextView te_br_name=dialog.findViewById(R.id.te_br_name);
                TextView te_name = dialog.findViewById(R.id.te_name);
                TextView te_startdate = dialog.findViewById(R.id.te_time_date);
                TextView te_course = dialog.findViewById(R.id.te_course);
                TextView te_remark = dialog.findViewById(R.id.te_remark);
                TextView te_mobile = dialog.findViewById(R.id.te_mobile);
                TextView te_total_person = dialog.findViewById(R.id.te_total_person);
                TextView te_gender = dialog.findViewById(R.id.te_gender);
                TextView te_description = dialog.findViewById(R.id.te_description);
                TextView te_collage_name = dialog.findViewById(R.id.te_collage_name);

                te_br_name.setText(arrayList.get(position).getBr_name());
                te_name.setText(arrayList.get(position).getName());
                te_startdate.setText(arrayList.get(position).getTime_date());
                te_course.setText(arrayList.get(position).getCourse());
                te_remark.setText(arrayList.get(position).getRemark());
                te_mobile.setText(arrayList.get(position).getMobile());
                te_total_person.setText(arrayList.get(position).getTotal_person());
                te_gender.setText(arrayList.get(position).getGender());
                te_description.setText(arrayList.get(position).getDescription());
                te_collage_name.setText(arrayList.get(position).getCollage_name());

            }
        };
    }
}