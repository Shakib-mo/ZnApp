package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class Logine_Act extends AppCompatActivity {

    Button bt_logine;
    EditText ed_name,ed_password;
    RequestQueue requestQueue,requestQueue_br;
    Spinner spinner;
    String name="";
    String psw="";

    public static final String pref="login";
    ArrayList<String> arrayList = new ArrayList<>();

    void Disply_Branch(){
        requestQueue_br = Volley.newRequestQueue(Logine_Act.this);
        String Url ="https://zninfotech.com/znapi/branch.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject object = new JSONObject();
                try {
                    for (int i =0;i<response.length();i++){
                        object= response.getJSONObject(i);
//                        arrayList.add(object.getString("BranchName"));
                        arrayList.add(object.getString("BranchNo"));
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(Logine_Act.this,
                            android.R.layout.simple_spinner_dropdown_item,arrayList));

                }catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Logine_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue_br.add(jsonArrayRequest);
    }

    void Logine_ad(String a, String b){
        requestQueue = Volley.newRequestQueue(Logine_Act.this);
        String Url ="https://zninfotech.com/znapi/admin.php";
        JSONObject object = new JSONObject();
        try {
            object.put("UserName",a);
            object.put("Psw",b);
        }
        catch (Exception ex){}
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("Response").equals("Valid User")){
                        Intent intent = new Intent(Logine_Act.this,First_MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slid_up, R.anim.slid_up);
                    }
                    else{
                        Toast.makeText(Logine_Act.this, response.getString("Response"), Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){
                    Toast.makeText(Logine_Act.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Logine_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logine);
        bt_logine = findViewById(R.id.bt_summit);
        ed_password = findViewById(R.id.ed_password);
        ed_name = findViewById(R.id.ed_name);
        spinner = findViewById(R.id.spiner_);
        Disply_Branch();
        bt_logine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ed_name.getText().toString();
                psw = ed_password.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("Act", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.clear();
       // editor.commit()
                editor.putString("BranchNo",arrayList.get(spinner.getSelectedItemPosition()));
                editor.putString("BranchNo",arrayList.get(spinner.getSelectedItemPosition()));
                editor.putBoolean("validate",true);

                editor.commit();
                if (name.isEmpty()){
                    ed_name.setError("Enter name");
                    ed_name.requestFocus();
                }else if (psw.isEmpty()) {
                    ed_password.setError("Enter password");
                    ed_password.requestFocus();
                }else {
                    Logine_ad(name,psw);
                }
            }
        });
        SharedPreferences shared=getSharedPreferences("Act",0);
        Boolean logins=shared.getBoolean("validate",false);
        if(logins){
            Intent intent = new Intent(Logine_Act.this,First_MainActivity.class);
            startActivity(intent);
            finish();
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}