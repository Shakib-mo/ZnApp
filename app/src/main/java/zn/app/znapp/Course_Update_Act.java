package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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

public class Course_Update_Act extends AppCompatActivity {
    EditText ed_cours,ed_fees,ed_duratin;
    Spinner sp_branch;
    CardView card_update;
    ImageView bt_back;
    RequestQueue requestQueue,requestQueue_br;
    ArrayList<String> arrayList_name = new ArrayList<>();
    ArrayList<String> arrayList_no = new ArrayList<>();
    String course,fee,duration;

    void Sp_Branch(){
        requestQueue_br = Volley.newRequestQueue(this);
        String Url ="https://zninfotech.com/znapi/branch.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i =0;i<response.length();i++){
                        object = response.getJSONObject(i);
                        arrayList_name.add(object.getString("BranchName"));
                        arrayList_no.add(object.getString("BranchNo"));
                    }
                    sp_branch.setAdapter(new ArrayAdapter<String>(Course_Update_Act.this, android.R.layout.simple_spinner_dropdown_item,arrayList_name));
                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course_Update_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_br.add(jsonArrayRequest);
    }
    void Update(String branch,String co,String fee,String dura) {
        requestQueue = Volley.newRequestQueue(this);
        String Url = "https://zninfotech.com/znapi/getcourse.php";
        JSONObject object = new JSONObject();
        try {
            object.put("BranchNo", branch);
            object.put("CourseName", co);
            object.put("Fee", fee);
            object.put("Duration", dura);
        } catch (Exception e) {
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Course_Update_Act.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Course_Update_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_update);
        ed_cours = findViewById(R.id.ed_cours);
        ed_fees = findViewById(R.id.ed_fees);
        ed_duratin = findViewById(R.id.ed_duratin);
        sp_branch = findViewById(R.id.sp_branch);
        card_update = findViewById(R.id.card_update);
        bt_back = findViewById(R.id.im_back);

        Sp_Branch();

        Intent intent = getIntent();
        course = intent.getStringExtra("co");
        fee = intent.getStringExtra("fee");
        duration = intent.getStringExtra("du");
        ed_cours.setText(course);
        ed_fees.setText(fee);
        ed_duratin.setText(duration);


        card_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course = ed_cours.getText().toString().trim();
                fee = ed_fees.getText().toString().trim();
                duration = ed_duratin.getText().toString().trim();
                if (course.isEmpty()){
                    ed_cours.setError("Enter course");
                    ed_cours.requestFocus();
                }else if (fee.isEmpty()){
                    ed_fees.setError("Enter Fee");
                    ed_fees.requestFocus();
                }else if (duration.isEmpty()){
                    ed_duratin.setError("Enter Duration");
                    ed_duratin.requestFocus();
                }else {
                    String BranchNo = arrayList_no.get(sp_branch.getSelectedItemPosition());
                    Update(BranchNo,course,fee,duration);
                    Intent intent = new Intent(Course_Update_Act.this,Course_Disply.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
       sp_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       bt_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });
    }
}