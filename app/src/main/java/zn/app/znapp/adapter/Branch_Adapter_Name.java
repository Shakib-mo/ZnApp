package zn.app.znapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import zn.app.znapp.R;

public class Branch_Adapter_Name extends RecyclerView.Adapter<Branch_Adapter_Name.ViewHolder> {
    private ArrayList<Branch_GetSet> arrayList;
    private Context context;
    public static Course_Name_Adapter.ClickListener clickListener;
    public Branch_Adapter_Name(Context co, ArrayList<Branch_GetSet> arr, Course_Name_Adapter.ClickListener cli){
        this.arrayList = arr;
        this.context = co;
        clickListener  = cli;
    }
    @NonNull
    @Override
    public Branch_Adapter_Name.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_branch_name,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Branch_Adapter_Name.ViewHolder holder, int position) {
        holder.te_branch_name.setText(arrayList.get(position).getBranch_name());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView te_branch_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            te_branch_name = itemView.findViewById(R.id.te_branch_name);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickListener.onItemClick(v,position);
        }
    }
    public interface ClickListener extends Course_Name_Adapter.ClickListener {
        void onItemClick(View view,int position);
    }
}
