package zn.app.znapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Course_Edt extends AppCompatActivity {

    ImageView bt_back;

    Spinner sp_Branch;
    EditText ed_course_name,ed_fee,ed_duration;
    CardView card_save;
    RequestQueue requestQueue,requestQueue_B;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList_number = new ArrayList<>();
    void Display_Branch(){
        requestQueue_B = Volley.newRequestQueue(this);
        String Url ="https://zninfotech.com/znapi/branch.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i = 0;i<response.length();i++){
                        object = response.getJSONObject(i);
                        arrayList.add(object.getString("BranchName"));
                        arrayList_number.add(object.getString("BranchNo"));

                    }
                    sp_Branch.setAdapter(new ArrayAdapter<String>(Course_Edt.this, android.R.layout.simple_spinner_dropdown_item,arrayList));

                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course_Edt.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_B.add(jsonArrayRequest);
    }
    void Save_Cours(String c_name,String fee_,String dura,String branch){
        requestQueue = Volley.newRequestQueue(Course_Edt.this);
        String Url = "https://zninfotech.com/znapi/course.php";
        JSONObject object = new JSONObject();
        try {
            object.put("CourseName",c_name);
            object.put("Fee",fee_);
            object.put("Duration",dura);
            object.put("BranchNo",branch);

        }catch (Exception e){
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Course_Edt.this, response.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Course_Edt.this,Course_Disply.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course_Edt.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edt);
        bt_back = findViewById(R.id.im_back);
        ed_course_name = findViewById(R.id.ed_cours);
        ed_fee = findViewById(R.id.ed_fees);
        ed_duration = findViewById(R.id.ed_duratin);
        sp_Branch = findViewById(R.id.sp_branch);

        card_save = findViewById(R.id.card_save);

        card_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branch = arrayList_number.get(sp_Branch.getSelectedItemPosition());
                String cours_name = ed_course_name.getText().toString();
                String fee = ed_fee.getText().toString();
                String duration = ed_duration.getText().toString();
                if (cours_name.isEmpty()){
                    ed_course_name.setError("Enter name");
                    ed_course_name.requestFocus();
                }else if (fee.isEmpty()){
                    ed_fee.setError("Enter fee");
                    ed_fee.requestFocus();
                }else if (duration.isEmpty()){
                    ed_duration.setError("Enter duration");
                    ed_duration.requestFocus();
                }else {
                    Save_Cours(cours_name,fee,duration,branch);
                }
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.top_to_bottom, R.anim.top_to_bottom);
            }
        });
        Display_Branch();
        sp_Branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}