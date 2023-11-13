package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import zn.app.znapp.adapter.Employ_Adapter;

public class Employ_Display_Act extends AppCompatActivity {
    ImageView bt_back,bt_add;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    ArrayList<Employ_get_set> arrayList = new ArrayList<>();
    Employ_Adapter adapter;
    String Branch_Number;
    void Disply_Emp(String b_name){
        requestQueue = Volley.newRequestQueue(Employ_Display_Act.this);
        String Url = "https://zninfotech.com/znapi/emp.php";
        SharedPreferences preferences = getSharedPreferences("Act", Context.MODE_PRIVATE);
        Branch_Number = preferences.getString("BranchNo",b_name);
        Toast.makeText(this, Branch_Number, Toast.LENGTH_SHORT).show();
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            object.put("BranchNo",b_name);
            array.put(object);
        }catch (Exception e){
            e.printStackTrace();}

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i =0;i<response.length();i++){
                        object = response.getJSONObject(i);
                        arrayList.add(new Employ_get_set(object.getString("Name"),object.getString("Address"),object.getString("AadharNo"),
                                object.getString("Mobile"), object.getString("Gender"),object.getString("HeigestQual"),object.getString("Email"),
                                object.getString("DOJ"),object.getString("Description"),object.getString("RoleName"),object.getString("FacultyFor"),
                                object.getString("Salaried"),object.getString("Salary")));
                    }
                    adapter = new Employ_Adapter(Employ_Display_Act.this,arrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }catch (Exception e){
                    Toast.makeText(Employ_Display_Act.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Employ_Display_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_display);
        recyclerView = findViewById(R.id.recycler_view);
        bt_add = findViewById(R.id.bt_add);
        bt_back = findViewById(R.id.bt_back);

        Disply_Emp(Branch_Number);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Employ_Display_Act.this));

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
                Intent intent = new Intent(Employ_Display_Act.this,Employ_Edt_Act.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);

            }
        });
    }
}