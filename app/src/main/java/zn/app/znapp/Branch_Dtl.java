package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Branch_Dtl extends AppCompatActivity {

    ImageView bt_back;

    EditText ed_branch_name,ed_start_date,ed_address,ed_contect_number,ed_mobile,ed_whatsapp_number,ed_email;
    CardView card_save;
    RequestQueue requestQueue_s;

    void Save_Address(String b_name,String start_date,String address,String contect,String mobile,String whatsapp,String email){

        requestQueue_s = Volley.newRequestQueue(Branch_Dtl.this);
        String Url = "https://zninfotech.com/znapi/branch.php";
        JSONObject object = new JSONObject();
        try {
            object.put("BranchName",b_name);
            object.put("StartDate",start_date);
            object.put("Address",address);
            object.put("ContactNo",contect);
            object.put("MobileNo",mobile);
            object.put("WhatsAppNo",whatsapp);
            object.put("Email",email);
        }catch (Exception e){
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent = new Intent(Branch_Dtl.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Branch_Dtl.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_s.add(jsonObjectRequest);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_dtl);
        bt_back = findViewById(R.id.im_back);
        card_save = findViewById(R.id.card_save);
        ed_branch_name = findViewById(R.id.ed_branch_name);
        ed_mobile = findViewById(R.id.ed_mo_number);
        ed_email = findViewById(R.id.ed_email);
        ed_address = findViewById(R.id.ed_address);
        ed_start_date = findViewById(R.id.ed_start_date);
        ed_contect_number = findViewById(R.id.ed_contect_number);
        ed_whatsapp_number = findViewById(R.id.ed_whatsapp_number);
        card_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b_name = ed_branch_name.getText().toString();
                String start_date = ed_start_date.getText().toString();
                String what_number = ed_whatsapp_number.getText().toString();
                String contect_no = ed_contect_number.getText().toString();
                String mobile = ed_mobile.getText().toString();
                String email = ed_email.getText().toString();
                String address = ed_address.getText().toString();
                if (b_name.isEmpty()){
                    ed_branch_name.setError("Enter name");
                    ed_branch_name.requestFocus();
                }else if (mobile.isEmpty()){
                    ed_mobile.setError("Enter mobile");
                    ed_mobile.requestFocus();
                }else if (email.isEmpty()){
                    ed_email.setError("Enter email");
                    ed_email.requestFocus();
                }else if (address.isEmpty()){
                    ed_address.setError("Enter address");
                    ed_address.requestFocus();
                }else {
                    Save_Address(b_name,start_date,address,contect_no,mobile,what_number,email);
                    overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
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
    }
}