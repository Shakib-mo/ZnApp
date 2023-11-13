package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import zn.app.znapp.adapter.Add_Rol_Adapter;
import zn.app.znapp.adapter.Branch_Adapter;
import zn.app.znapp.adapter.Branch_GetSet;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView bt_add,bt_back;
    BottomSheetDialog bottomSheetDialog;
    RequestQueue requestQueue_Dis;
    ArrayList<Branch_GetSet> arrayList_D = new ArrayList<>();
    Add_Rol_Adapter adapter;
    Branch_Adapter branch_adapter;
    void Display_Data(){
        requestQueue_Dis =Volley.newRequestQueue(MainActivity.this);
        String Url ="https://zninfotech.com/znapi/branch.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONArray array = response;
                JSONObject object = new JSONObject();
                try {
                    for (int i=0;i<array.length();i++){
                        object = array.getJSONObject(i);
                        arrayList_D.add(new Branch_GetSet(object.getString("BranchNo"),object.getString("BranchName"),object.getString("StartDate"),
                                object.getString("Address"),object.getString("ContactNo"),object.getString("MobileNo"),object.getString("WhatsAppNo")
                                ,object.getString("Email"),object.getString("Status")));
                    }
                    branch_adapter = new Branch_Adapter(MainActivity.this,arrayList_D);
                    recyclerView.setAdapter(branch_adapter);
                    branch_adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_Dis.add(jsonArrayRequest);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_add = findViewById(R.id.bt_add);
        bt_back = findViewById(R.id.bt_back);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Display_Data();
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Branch_Dtl.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
            }
        });

        /*bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.edt_add_role);
                ed_role = bottomSheetDialog.findViewById(R.id.ed_role);
                im_back = bottomSheetDialog.findViewById(R.id.im_back);
                im_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.cancel();
                    }
                });
                bt_save = bottomSheetDialog.findViewById(R.id.card_save);
                bt_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        role=ed_role.getText().toString();
                        if (role.isEmpty()){
                            ed_role.setError("Entet role");
                            ed_role.requestFocus();
                        }else {
                            Save_Role(role);
                        }
                    }
                });
                bottomSheetDialog.show();
            }
        });*/
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.top_to_bottom, R.anim.top_to_bottom);
            }
        });
    }
}