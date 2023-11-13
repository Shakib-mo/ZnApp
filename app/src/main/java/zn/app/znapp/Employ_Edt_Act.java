package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

public class Employ_Edt_Act extends AppCompatActivity {

    RequestQueue requestQueue;
    ImageView bt_back;
    EditText ed_name,ed_adderss_,ed_aadhar_no,ed_mo_number,ed_higest_qual,ed_email,ed_dob,ed_description,
            ed_rolr_id,ed_faculty_for,ed_salaried,ed_salary;
    CardView card_save;
    RadioButton male,female;

    String gen;

    void Save_Employ(String name,String address,String aadhar,String mob,String higest,String email,String dob
            ,String desc,String role,String faculty,String salaried,String salary,String gender){
        requestQueue = Volley.newRequestQueue(Employ_Edt_Act.this);
        String Url = "https://zninfotech.com/znapi/emp.php";
        JSONObject object = new JSONObject();
        try {
            object.put("Name",name);
            object.put("Address",address);
            object.put("AadharNo",aadhar);
            object.put("Mobile",mob);
            object.put("HeigestQual",higest);
            object.put("Email",email);
            object.put("DOJ",dob);
            object.put("Description",desc);
            object.put("RoleID",role);
            object.put("FacultyFor",faculty);
            object.put("Salaried",salaried);
            object.put("Salary",salary);
            object.put("Gender",gender);
        }catch (Exception e){}
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Employ_Edt_Act.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Employ_Edt_Act.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ_edt);
        bt_back = findViewById(R.id.im_back);
        ed_name = findViewById(R.id.ed_name);
        ed_adderss_ = findViewById(R.id.ed_adderss_);
        ed_aadhar_no = findViewById(R.id.ed_aadhar_no);
        ed_mo_number = findViewById(R.id.ed_mo_number);
        ed_higest_qual = findViewById(R.id.ed_higest_qual);
        ed_email = findViewById(R.id.ed_email);
        ed_dob = findViewById(R.id.ed_dob);
        ed_description = findViewById(R.id.ed_description);
        ed_rolr_id = findViewById(R.id.ed_rolr_id);
        ed_faculty_for = findViewById(R.id.ed_faculty_for);
        ed_salaried = findViewById(R.id.ed_salaried);
        ed_salary = findViewById(R.id.ed_salary);
        card_save = findViewById(R.id.card_save);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        if (male.isChecked()){
            gen="Male";
        }else {
            gen="Female";
        }

        card_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name.getText().toString();
                String address = ed_adderss_.getText().toString();
                String aadhar = ed_aadhar_no.getText().toString();
                String mobile = ed_mo_number.getText().toString();
                String higest_q = ed_higest_qual.getText().toString();
                String email = ed_email.getText().toString();
                String dob = ed_dob.getText().toString();
                String description = ed_description.getText().toString();
                String role = ed_rolr_id.getText().toString();
                String faculty = ed_faculty_for.getText().toString();
                String salaried = ed_salaried.getText().toString();
                String salary = ed_salary.getText().toString();

                Save_Employ(name,address,aadhar,mobile,higest_q,email,dob,description,role,faculty,
                        salaried,salary,gen);

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