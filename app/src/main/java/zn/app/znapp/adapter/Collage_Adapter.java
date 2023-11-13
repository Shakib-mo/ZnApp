package zn.app.znapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import zn.app.znapp.Collage_Display;
import zn.app.znapp.R;
import zn.app.znapp.Role_add_get_set;

public class Collage_Adapter extends RecyclerView.Adapter<Collage_Adapter.ViewHolder> {
    ArrayList<Role_add_get_set> arrayList;
    Context context;
    public Collage_Adapter(Context con,ArrayList<Role_add_get_set> array){
        this.arrayList = array;
        this.context = con;
    }
    @NonNull
    @Override
    public Collage_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_collage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Collage_Adapter.ViewHolder holder, int position) {
        holder.te_collage_code.setText(arrayList.get(position).getCollage_code());
        holder.te_collage_name.setText(arrayList.get(position).getCollage_name());
        holder.te_address.setText(arrayList.get(position).getAddress());
        holder.te_email.setText(arrayList.get(position).getEmail());
        holder.te_course.setText(arrayList.get(position).getCourse());
        holder.te_contact_no.setText(arrayList.get(position).getContact_no());
        holder.te_TPOfficer.setText(arrayList.get(position).getTPOfficer());
        holder.te_Website.setText(arrayList.get(position).getWebsite());
        holder.te_description.setText(arrayList.get(position).getDescription());
        holder.te_branchNo.setText(arrayList.get(position).getBranch_no());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView te_collage_code,te_collage_name,te_address,te_email,te_course,te_contact_no,te_TPOfficer,te_Website,
                te_description,te_branchNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            te_collage_code = itemView.findViewById(R.id.te_collage_code);
            te_collage_name = itemView.findViewById(R.id.te_collage_name);
            te_address = itemView.findViewById(R.id.te_address);
            te_email = itemView.findViewById(R.id.te_email);
            te_course = itemView.findViewById(R.id.te_course);
            te_contact_no = itemView.findViewById(R.id.te_contact_no);
            te_TPOfficer = itemView.findViewById(R.id.te_TPOfficer);
            te_Website = itemView.findViewById(R.id.te_Website);
            te_description = itemView.findViewById(R.id.te_description);
            te_branchNo = itemView.findViewById(R.id.te_branchNo);
        }
    }
}
