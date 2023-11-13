package zn.app.znapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import zn.app.znapp.Course_Update_Act;
import zn.app.znapp.MainActivity;
import zn.app.znapp.R;
import zn.app.znapp.Role_add_get_set;
import zn.app.znapp.Update_Branch_Details_Act;

public class Branch_Adapter extends RecyclerView.Adapter<Branch_Adapter.ViewHolder> {
    Context context;
    ArrayList<Branch_GetSet> arrayList;
    public Branch_Adapter(Context con,ArrayList<Branch_GetSet> array){
        this.context = con;
        this.arrayList = array;
    }
    @NonNull
    @Override
    public Branch_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_branch,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Branch_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.te_br_number.setText(arrayList.get(position).getBranch_no());
        holder.te_br_name.setText(arrayList.get(position).getBranch_name());
        holder.te_start_data.setText(arrayList.get(position).getStart_date());
        holder.te_address.setText(arrayList.get(position).getAddress());
        holder.te_con_number.setText(arrayList.get(position).getContact_no());
        holder.te_mo_number.setText(arrayList.get(position).getMobile_no());
        holder.te_wh_number.setText(arrayList.get(position).getWhatsApp_no());
        holder.te_email.setText(arrayList.get(position).getEmail());
        holder.te_status.setText(arrayList.get(position).getStatus());

       // ((MainActivity)context).Branch_Name=arrayList.get(position).getBranch_no();
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
                                Intent intent = new Intent(context, Update_Branch_Details_Act.class);
                                context.startActivity(intent);
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
        TextView te_br_number,te_br_name,te_start_data,te_address,te_con_number,te_mo_number,te_wh_number,te_email,te_status;
        ImageView toolbar_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            te_br_number = itemView.findViewById(R.id.te_br_number);
            te_br_name = itemView.findViewById(R.id.te_br_name);
            te_start_data = itemView.findViewById(R.id.te_startdate);
            te_address = itemView.findViewById(R.id.te_address);
            te_con_number = itemView.findViewById(R.id.te_contect_number);
            te_mo_number = itemView.findViewById(R.id.te_mobile_number);
            te_wh_number = itemView.findViewById(R.id.te_whatsapp_number);
            te_email = itemView.findViewById(R.id.te_email);
            te_status = itemView.findViewById(R.id.te_status);
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
        String Url = "https://zninfotech.com/znapi/branch.php";
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
