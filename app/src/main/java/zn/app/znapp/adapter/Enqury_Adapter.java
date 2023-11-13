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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import zn.app.znapp.Course_Disply;
import zn.app.znapp.Employ_get_set;
import zn.app.znapp.Enqury_Display;
import zn.app.znapp.R;

public class Enqury_Adapter extends RecyclerView.Adapter<Enqury_Adapter.ViewHolder> {
    Context context;
    ArrayList<Employ_get_set> arrayList;
    public ClickListner clickListner;
    public Enqury_Adapter(Context con,ArrayList<Employ_get_set> array,Enqury_Adapter.ClickListner click){
        this.context = con;
        this.arrayList = array;
        clickListner = click;

    }
    @NonNull
    @Override
    public Enqury_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_enquiry_short_name,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Enqury_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.te_br_name.setText(arrayList.get(position).getBr_name());
        holder.te_name.setText(arrayList.get(position).getName());
        holder.te_time_date.setText(arrayList.get(position).getTime_date());
        holder.te_course.setText(arrayList.get(position).getCourse());
        //((Enqury_Display)context).Branch_Number=arrayList.get(position).getBr_name();
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView te_br_name,te_name,te_time_date,te_course;
        ImageView toolbar_menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            te_br_name = itemView.findViewById(R.id.te_br_name);
            te_name = itemView.findViewById(R.id.te_name);
            te_time_date = itemView.findViewById(R.id.te_time_date);
            te_course = itemView.findViewById(R.id.te_course);
            toolbar_menu = itemView.findViewById(R.id.toolbar_menu);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickListner.onItemClick(position,v);
        }
    }
    public interface ClickListner{
        void onItemClick(int position,View view);
    }
    public void Delete_position(int position){
        arrayList.remove(position);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    public void Delete_Api_Data(String br_no,int position) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String Url = "https://zninfotech.com/znapi/enquiry.php";
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
