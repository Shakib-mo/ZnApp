package zn.app.znapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import zn.app.znapp.R;
import zn.app.znapp.Role_add_get_set;

public class Status_Adapter extends RecyclerView.Adapter<Status_Adapter.ViewHolder> {
    public static ClickListner clickListner;
    Activity activity;
    ArrayList<Role_add_get_set>arrayList;
    public Status_Adapter(Activity act,ArrayList<Role_add_get_set> array,ClickListner click){
        this.activity = act;
        this.arrayList = array;
        clickListner = click;
    }
    @NonNull
    @Override
    public Status_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_status,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Status_Adapter.ViewHolder holder, int position) {
        holder.te_br_name.setText(arrayList.get(position).getBranch_name());
        holder.te_enquiry.setText(arrayList.get(position).getEnquiry());
        holder.te_course.setText(arrayList.get(position).getCourse_name());
        holder.te_mobile_no.setText(arrayList.get(position).getMobile_no());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView te_br_name,te_enquiry,te_course,te_mobile_no;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            te_br_name = itemView.findViewById(R.id.te_br_name);
            te_enquiry = itemView.findViewById(R.id.te_enquiry);
            te_course = itemView.findViewById(R.id.te_course);
            te_mobile_no = itemView.findViewById(R.id.te_mobile_no);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickListner.onItemClick(v,position);
        }
    }
    public interface ClickListner{
        void onItemClick(View view,int position);
    }
}
