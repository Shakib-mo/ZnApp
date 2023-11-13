package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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

import zn.app.znapp.adapter.Collage_Adapter;

public class Collage_Display extends AppCompatActivity {

    RecyclerView recycler_view;
    ImageView bt_back;
    ArrayList<Role_add_get_set> arrayList = new ArrayList<>();
    Collage_Adapter adapter;
    RequestQueue requestQueue;
    public String Branch_Number;
    void Disply_Collage(String b_name){
        requestQueue = Volley.newRequestQueue(Collage_Display.this);
        String Url = "https://zninfotech.com/znapi/college.php";
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
                    for (int i=0;i<response.length();i++){
                        object = response.getJSONObject(i);
                        arrayList.add(new Role_add_get_set(object.getString("CollegeCode"),object.getString("CollegeName")
                                ,object.getString("Address"), object.getString("Email"),object.getString("Course"),
                                object.getString("ContactNo"),object.getString("TPOfficer"), object.getString("Website")
                                ,object.getString("Description"),object.getString("BranchNo")));
                    }
                    adapter = new Collage_Adapter(Collage_Display.this,arrayList);
                    recycler_view.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Collage_Display.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage_display);
        recycler_view = findViewById(R.id.recycler_view);
        bt_back = findViewById(R.id.im_back);

        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(Collage_Display.this));
        Disply_Collage(Branch_Number);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}