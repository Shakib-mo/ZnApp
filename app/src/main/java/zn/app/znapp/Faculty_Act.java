package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Faculty_Act extends AppCompatActivity {

    CardView bt_save;
    ImageView bt_back;
    EditText ed_name,ed_mobile,ed_email,ed_address,ed_aadhar,ed_qualifition;
    RadioButton female,male;
    RequestQueue requestQueue_s,requestQueue_u;
    void Update_Data(String name,String mobile,String email,String address,String ad_number,String quali){
        requestQueue_u = Volley.newRequestQueue(Faculty_Act.this);
        String Url = "";
        JSONObject object = new JSONObject();
        try {
            object.put("",name);
            object.put("",mobile);
            object.put("",email);
            object.put("",address);
            object.put("",ad_number);
            object.put("",quali);
        }catch (Exception e){
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Faculty_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_u.add(jsonObjectRequest);
    }
    void Save_Data(String name,String mobile,String email,String address,String ad_number,String quali){
        requestQueue_s = Volley.newRequestQueue(Faculty_Act.this);
        String Url = "";
        JSONObject object = new JSONObject();
        try {
            object.put("",name);
            object.put("",mobile);
            object.put("",email);
            object.put("",address);
            object.put("",ad_number);
            object.put("",quali);
        }catch (Exception e){
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Faculty_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_s.add(jsonObjectRequest);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        bt_save= findViewById(R.id.card_save);
        bt_back = findViewById(R.id.im_back);

        ed_name = findViewById(R.id.ed_name);
        ed_mobile = findViewById(R.id.ed_phone);
        ed_email = findViewById(R.id.ed_email);
        ed_address = findViewById(R.id.ed_address);
        ed_aadhar = findViewById(R.id.ed_aadhar_number);
        ed_qualifition = findViewById(R.id.ed_highar_qualification);
        female = findViewById(R.id.female_);
        male = findViewById(R.id.male_);



        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.top_to_bottom, R.anim.top_to_bottom);
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name.getText().toString();
                String mobile = ed_mobile.getText().toString();
                String email = ed_email.getText().toString();
                String address = ed_address.getText().toString();
                String aadhar = ed_aadhar.getText().toString();
                String quali = ed_qualifition.getText().toString();

                Save_Data(name,mobile,email,address,aadhar,quali);
                Update_Data(name,mobile,email,address,aadhar,quali);
            }
        });
    }
}