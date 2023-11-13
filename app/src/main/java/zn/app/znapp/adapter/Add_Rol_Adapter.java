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

public class Add_Rol_Adapter extends RecyclerView.Adapter<Add_Rol_Adapter.ViewHolder> {
    ArrayList<Role_add_get_set> arrayList;
    Activity context;
    public Add_Rol_Adapter(Activity act,ArrayList<Role_add_get_set> array){
        this.arrayList = array;
        this.context = act;
    }
    @NonNull
    @Override
    public Add_Rol_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_add_role,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Add_Rol_Adapter.ViewHolder holder, int position) {
        holder.te_role.setText(arrayList.get(position).getRole());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView te_role;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            te_role = itemView.findViewById(R.id.te_role);
        }
    }
}
