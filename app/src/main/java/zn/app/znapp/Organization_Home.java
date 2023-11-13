package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import org.json.JSONObject;

public class Organization_Home extends AppCompatActivity {

    CardView bt_save;
    ImageView bt_back;
    RequestQueue requestQueue_s;

    void Save_Data(String og_name,String ow_name,String contect,String address,String email){
        requestQueue_s = Volley.newRequestQueue(Organization_Home.this);
        String Url = "";
        JSONObject object = new JSONObject();
        try {
            object.put("",og_name);
            object.put("",ow_name);
            object.put("",contect);
            object.put("",address);
            object.put("",email);
        }catch (Exception e){}
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Organization_Home.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_s.add(jsonObjectRequest);
    }
    EditText ed_org_name,ed_owner_name,ed_cont_number,ed_address,ed_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_home);
        bt_save = findViewById(R.id.card_save);
        bt_back = findViewById(R.id.im_back);
        ed_org_name = findViewById(R.id.ed_organization_name);
        ed_owner_name = findViewById(R.id.ed_ph_owner_name);
        ed_cont_number = findViewById(R.id.ed_contect_number);
        ed_address = findViewById(R.id.ed_address);
        ed_email = findViewById(R.id.ed_email);

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
                String og_name=ed_org_name.getText().toString();
                String own_name=ed_owner_name.getText().toString();
                String contect=ed_cont_number.getText().toString();
                String address=ed_address.getText().toString();
                String email=ed_email.getText().toString();

                Save_Data(og_name,own_name,contect,address,email);

                Intent intent = new Intent(Organization_Home.this,Faculty_Act.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });
    }
}