package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import zn.app.znapp.adapter.Add_Rol_Adapter;

public class Role_Disply_Act extends AppCompatActivity {
    CardView card_save;
    BottomSheetDialog bottomSheetDialog;
    EditText ed_role;
    RequestQueue requestQueue,requestQueue_d;
    ArrayList<Role_add_get_set> arrayList = new ArrayList<>();
    ImageView bt_add,bt_back,im_back;
    RecyclerView recyclerView;
    Add_Rol_Adapter adapter;

    void Disply_Role(){
        requestQueue_d = Volley.newRequestQueue(Role_Disply_Act.this);
        String Url = "https://zninfotech.com/znapi/role.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object =new JSONObject();
                try {
                   for (int i =0;i<response.length();i++){
                       object=response.getJSONObject(i);
                       arrayList.add(new Role_add_get_set(object.getString("RoleName")));
                   }
                   adapter = new Add_Rol_Adapter(Role_Disply_Act.this,arrayList);
                   recyclerView.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
                }catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Role_Disply_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_d.add(jsonArrayRequest);
    }
    void Save_Role(String role){
        requestQueue = Volley.newRequestQueue(Role_Disply_Act.this);
        String Url = "https://zninfotech.com/znapi/role.php";
        JSONObject object = new JSONObject();
        try {
                object.put("RoleName",role);
        }catch (Exception e){}
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(Role_Disply_Act.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Role_Disply_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_disply);
        bt_add = findViewById(R.id.bt_add);
        bt_back = findViewById(R.id.bt_back);
        recyclerView = findViewById(R.id.recycler_view);

        Disply_Role();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Role_Disply_Act.this));
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
                bottomSheetDialog = new BottomSheetDialog(Role_Disply_Act.this);
                bottomSheetDialog.setContentView(R.layout.edt_add_role);
                ed_role = bottomSheetDialog.findViewById(R.id.ed_role);
                im_back = bottomSheetDialog.findViewById(R.id.im_back_);
                card_save = bottomSheetDialog.findViewById(R.id.card_save);

                im_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.cancel();
                    }
                });
                card_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String role=ed_role.getText().toString();
                        if (role.isEmpty()){
                            ed_role.setError("Entet role");
                            ed_role.requestFocus();
                        }else{
                            Save_Role(role);
                        }
                    }
                });
                bottomSheetDialog.show();
            }
        });

    }
}