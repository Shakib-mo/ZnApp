package zn.app.znapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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

import zn.app.znapp.adapter.Course_Adapter;
import zn.app.znapp.adapter.Enqury_Adapter;
import zn.app.znapp.adapter.Status_Adapter;

public class First_MainActivity extends AppCompatActivity {

    ImageView bt_back;
    CardView bt_course,bt_branch,card_role,card_emply,card_enquiry,card_collage,card_logOut;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    ArrayList<Role_add_get_set>arrayList = new ArrayList<>();
    Status_Adapter adapter;
    Status_Adapter.ClickListner clickListner;

    String name;
    void Disply_Cours(){
        requestQueue = Volley.newRequestQueue(First_MainActivity.this);
        String Url = "https://zninfotech.com/znapi/branch.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i=0;i<response.length();i++){
                        object = response.getJSONObject(i);
                        arrayList.add(new Role_add_get_set(object.getString("BranchName"),
                                object.getString("Email"),object.getString("ContactNo")
                                ,object.getString("MobileNo")));
                    }
                    adapter = new Status_Adapter(First_MainActivity.this,arrayList,clickListner);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(First_MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);
        bt_back = findViewById(R.id.im_back);
        bt_branch = findViewById(R.id.card_branch);
        bt_course = findViewById(R.id.card_course);
        card_role = findViewById(R.id.card_role);
        card_emply = findViewById(R.id.card_emply);
        card_enquiry = findViewById(R.id.card_enquiry);
        card_collage = findViewById(R.id.card_collage);
        recyclerView = findViewById(R.id.recycler_view_status);
        card_logOut = findViewById(R.id.card_logOut);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(First_MainActivity.this,LinearLayoutManager.HORIZONTAL,true));
        Disply_Cours();


        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.top_to_bottom, R.anim.top_to_bottom);
            }
        });
        card_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(First_MainActivity.this,Role_Disply_Act.class);
                startActivity(i);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });
        card_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(First_MainActivity.this,Enqury_Display.class);
                startActivity(i);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });


        bt_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(First_MainActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });
        bt_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(First_MainActivity.this, Course_Disply.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });
        card_emply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(First_MainActivity.this,Employ_Display_Act.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });
        card_collage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(First_MainActivity.this,Collage_Display.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });
        clickListner = new Status_Adapter.ClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(First_MainActivity.this,Branch_Enquiry_Display.class);
                startActivity(intent);
            }
        };

        card_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Act", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                 editor.commit();

                Intent intent = new Intent(First_MainActivity.this, Logine_Act.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}