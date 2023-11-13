package zn.app.znapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import zn.app.znapp.Employ_get_set;
import zn.app.znapp.R;

public class Employ_Adapter extends RecyclerView.Adapter<Employ_Adapter.ViewHolder> {
    Context context;
    ArrayList<Employ_get_set> arrayList;
    public Employ_Adapter(Context con,ArrayList<Employ_get_set> arr){
        this.arrayList = arr;
        this.context = con;
    }
    @NonNull
    @Override
    public Employ_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_employ,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Employ_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.te_name.setText(arrayList.get(position).getName());
        holder.te_address.setText(arrayList.get(position).getAddress());
        holder.te_adhar_no.setText(arrayList.get(position).getAadhar_no());
        holder.te_mobile_no.setText(arrayList.get(position).getMobile());
        holder.te_gender.setText(arrayList.get(position).getGender());
        holder.te_heigest_qual.setText(arrayList.get(position).getHeigest_qual());
        holder.te_email.setText(arrayList.get(position).getEmail());
        holder.te_DOF.setText(arrayList.get(position).getDof());
        holder.te_description.setText(arrayList.get(position).getDescription());
        holder.te_Role_ID.setText(arrayList.get(position).getRole_id());
        holder.te_facultyFor.setText(arrayList.get(position).getFaculty_for());
        holder.te_salaried.setText(arrayList.get(position).getSalaried());
        holder.te_Salary.setText(arrayList.get(position).getSalary());
        holder.toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_toolbar,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete_menu:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Are you sure you want to Delete");
                                builder.setCancelable(true);
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Delete_Api_Data(arrayList.get(position).getBranch_no(),position);
                                        notifyDataSetChanged();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                return true;
                            case R.id.update_menu:
                                Toast.makeText(context, "UnderConstrection", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return true;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView te_name,te_address,te_adhar_no,te_mobile_no,te_gender,te_heigest_qual,te_email,
                te_DOF,te_description,te_Role_ID,te_facultyFor,te_salaried,te_Salary;
        ImageView toolbar_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            te_name = itemView.findViewById(R.id.te_name);
            te_address = itemView.findViewById(R.id.te_address);
            te_adhar_no = itemView.findViewById(R.id.te_adhar_no);
            te_mobile_no = itemView.findViewById(R.id.te_mobile_no);
            te_gender = itemView.findViewById(R.id.te_gender);
            te_heigest_qual = itemView.findViewById(R.id.te_heigest_qual);
            te_email = itemView.findViewById(R.id.te_email);
            te_DOF = itemView.findViewById(R.id.te_DOF);
            te_description = itemView.findViewById(R.id.te_description);
            te_Role_ID = itemView.findViewById(R.id.te_Role_ID);
            te_facultyFor = itemView.findViewById(R.id.te_facultyFor);
            te_salaried = itemView.findViewById(R.id.te_salaried);
            te_Salary = itemView.findViewById(R.id.te_Salary);
            toolbar_menu = itemView.findViewById(R.id.toolbar_menu);

        }
    }
    public void Delete_position(int position){
        arrayList.remove(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
    public void Delete_Api_Data(String br_no,int position) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String Url = "https://zninfotech.com/znapi/emp.php";
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loding");
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("BranchNo", br_no);
        } catch (Exception e) {
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, Url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                Delete_position(position);
                notifyItemChanged(position);
                notifyDataSetChanged();
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
