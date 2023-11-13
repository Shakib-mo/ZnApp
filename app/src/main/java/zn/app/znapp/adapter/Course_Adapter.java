package zn.app.znapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import zn.app.znapp.Course_Disply;
import zn.app.znapp.Course_Update_Act;
import zn.app.znapp.Employ_get_set;
import zn.app.znapp.R;
import zn.app.znapp.Role_add_get_set;

public class Course_Adapter extends RecyclerView.Adapter<Course_Adapter.ViewHolder> {
    ArrayList<Employ_get_set>arrayList;
    Activity context;
    public Course_Adapter(Activity con,ArrayList<Employ_get_set>array){
        this.arrayList = array;
        this.context = con;
    }
    @NonNull
    @Override
    public Course_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_course,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Course_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.te_cr_name.setText(arrayList.get(position).getCourse_name());
        holder.te_fee.setText(arrayList.get(position).getFee());
        holder.te_duration.setText(arrayList.get(position).getDuration());
        holder.te_branch_no.setText(arrayList.get(position).getBranch_no());
        holder.te_Branch_name.setText(arrayList.get(position).getBr_name());
        //((Course_Disply)context).Branch_Number=arrayList.get(position).getBranch_no();
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
                                        Delete_Api_Data(arrayList.get(position).getCourse_no(),position);
                                        notifyDataSetChanged();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                return true;
                            case R.id.update_menu:
                                Intent intent = new Intent(context,Course_Update_Act.class);
                                intent.putExtra("co",arrayList.get(position).getCourse_name());
                                intent.putExtra("fee",arrayList.get(position).getFee());
                                intent.putExtra("du",arrayList.get(position).getDuration());
                                context.startActivity(intent);
                                context.finish();
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
        TextView te_Branch_name,te_cr_name,te_fee,te_duration,te_branch_no;
        ImageView toolbar_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            te_cr_name = itemView.findViewById(R.id.te_cr_name);
            te_fee = itemView.findViewById(R.id.te_fee);
            te_duration = itemView.findViewById(R.id.te_duration);
            te_branch_no = itemView.findViewById(R.id.te_Branch_number);
            toolbar_menu = itemView.findViewById(R.id.toolbar_menu);
            te_Branch_name = itemView.findViewById(R.id.te_Branch_name);
        }
    }
    public void Delete_position(int position){
        arrayList.remove(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
    public void Delete_Api_Data(String br_no,int position) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String Url = "https://zninfotech.com/znapi/course.phpgi";
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loding");
        progressDialog.show();
        JSONObject object = new JSONObject();
        try {
            object.put("CourseNo", br_no);
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
