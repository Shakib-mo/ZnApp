package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import zn.app.znapp.adapter.Course_Name_Adapter;

public class Enquiry extends AppCompatActivity {

    Spinner sp_branch_name,sp_date,sp_course,sp_collage_name;
    EditText ed_name,ed_person,ed_qulification,ed_description,ed_mo_number;
    CardView card_save;
    ImageView bt_back;
    RadioButton male,female;
    String gender;
    String date="00-00-0000";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    RequestQueue requestQueue_name,requestQueue_course,requestQueue,requestQueue_collage;

    ArrayList<String>arrayList_name = new ArrayList<>();
    ArrayList<String>arrayList_course = new ArrayList<>();
    ArrayList<String>array_CourseNo = new ArrayList<>();
    ArrayList<String>array_Collage_name = new ArrayList<>();
    ArrayList<String>array_Collage_no = new ArrayList<>();


    void Save_Data(String branchNo,String name,String time_date,String course_for,String course,String mob,
                   String total_person,String gender,String description,String collage){
        requestQueue = Volley.newRequestQueue(Enquiry.this);
        String Url = "https://zninfotech.com/znapi/enquiry.php";
        JSONObject object = new JSONObject();
        try {
            object.put("BranchNo",branchNo);
            object.put("Name",name);
            object.put("DOE",time_date);
            object.put("CourseFor",course_for);
            object.put("Course",course);
            object.put("Mobile",mob);
            object.put("TotPerson",total_person);
            object.put("Gender",gender);
            object.put("Description",description);
            object.put("College",collage);
        }catch (Exception e){}
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent = new Intent(Enquiry.this,Enqury_Display.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enquiry.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    void Disply_Branch_name_spin(){
        requestQueue_name = Volley.newRequestQueue(Enquiry.this);
        String Url = "https://zninfotech.com/znapi/branch.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array = response;
                JSONObject object = new JSONObject();
                try {
                    for (int i = 0;i<array.length();i++){
                        object =array.getJSONObject(i);
                        arrayList_name.add(object.getString("BranchNo"));
                    }
                    sp_branch_name.setAdapter(new ArrayAdapter<String>(Enquiry.this,
                            android.R.layout.simple_spinner_dropdown_item,arrayList_name));

                }catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enquiry.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue_name.add(jsonArrayRequest);

    }

    void Disply_Collage_name_spin(){
        requestQueue_collage = Volley.newRequestQueue(Enquiry.this);
        String Url = "https://zninfotech.com/znapi/college.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array = response;
                JSONObject object = new JSONObject();
                try {
                    for (int i = 0;i<array.length();i++){
                        object =array.getJSONObject(i);
                        array_Collage_name.add(object.getString("CollegeName"));
                        array_Collage_no.add(object.getString("CollegeCode"));

                    }
                    sp_collage_name.setAdapter(new ArrayAdapter<String>(Enquiry.this,
                            android.R.layout.simple_spinner_dropdown_item,array_Collage_name));

                }catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enquiry.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue_collage.add(jsonArrayRequest);

    }


    void DisPaly_course(String branch_Number){
        requestQueue_course = Volley.newRequestQueue(Enquiry.this);
        String Url = "https://zninfotech.com/znapi/getcourse.php";
        arrayList_course.clear();

        JSONObject object = new JSONObject();
        JSONArray array1 = new JSONArray();
        try {
            object.put("BranchNo",branch_Number);
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
                        arrayList_course.add(object.getString("CourseName"));
                        array_CourseNo.add(object.getString("CourseNo"));
                    }
                    sp_course.setAdapter(new ArrayAdapter<String>(Enquiry.this,
                            android.R.layout.simple_spinner_dropdown_item,arrayList_course));
                }catch (Exception e){
                    Toast.makeText(Enquiry.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Enquiry.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_course.add(jsonArrayRequest);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        card_save = findViewById(R.id.card_save);
        sp_date = findViewById(R.id.sp_date);
        sp_course = findViewById(R.id.sp_course);
        sp_branch_name = findViewById(R.id.sp_branch_name);
        ed_name = findViewById(R.id.ed_name);
        ed_person = findViewById(R.id.ed_person);
        ed_qulification = findViewById(R.id.ed_qulification);
        ed_description = findViewById(R.id.ed_description);
        ed_mo_number = findViewById(R.id.ed_mo_number);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        bt_back = findViewById(R.id.im_back);
        sp_collage_name = findViewById(R.id.sp_collage_name);

        Disply_Branch_name_spin();
        Disply_Collage_name_spin();

        if (male.isChecked()){
            gender = "Male";
        }else {
            gender = "Female";
        }
        if (female.isChecked()){
            gender = "Female";
        }else {
            gender = "Male";
        }

        card_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= ed_name.getText().toString();
                String person = ed_person.getText().toString();
                String course = ed_qulification.getText().toString();
                String description = ed_description.getText().toString();
                String mobile = ed_mo_number.getText().toString();
                String branch = arrayList_name.get(sp_branch_name.getSelectedItemPosition());
                String courseFor = array_CourseNo.get(sp_course.getSelectedItemPosition());
                String collage = array_Collage_no.get(sp_collage_name.getSelectedItemPosition());
                Save_Data(branch,name,date,courseFor,course,mobile,person,gender,description,collage);
            }
        });


        sp_branch_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DisPaly_course(arrayList_name.get(position));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //DisPaly_course(arrayList_course.get(parent.getSelectedItemPosition()));
            }
        });
        sp_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_collage_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sp_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = df.format(cal.getTime());

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (sp_date.getSelectedItem().toString()) {
                    case "Pick a date":
                        DatePickerDialog dialog = new DatePickerDialog(Enquiry.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                mDateSetListener,
                                year,month,day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();

                    case "Today":
                        sp_date.setTextAlignment(Integer.parseInt(formattedDate));
                    case "Tomorrow":
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.top_to_bottom, R.anim.top_to_bottom);
            }
        });
    }
}