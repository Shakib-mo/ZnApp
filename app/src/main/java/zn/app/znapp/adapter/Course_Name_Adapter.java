package zn.app.znapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import zn.app.znapp.Employ_get_set;
import zn.app.znapp.R;

public class Course_Name_Adapter extends RecyclerView.Adapter<Course_Name_Adapter.ViewHolder> {
    Activity context;
    ArrayList<Employ_get_set>arrayList;
    public static ClickListener clickListener;
    public Course_Name_Adapter(Activity co,ArrayList<Employ_get_set> arr,ClickListener cli){
        this.arrayList = arr;
        this.context = co;
        clickListener =cli;
    }
    @NonNull
    @Override
    public Course_Name_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_course_name,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Course_Name_Adapter.ViewHolder holder, int position) {
        holder.te_course.setText(arrayList.get(position).getCourse());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView te_course;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            te_course = itemView.findViewById(R.id.te_course);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickListener.onItemClick(v,position);
        }
    }
   public interface ClickListener{
        void onItemClick(View view,int position);
   }
}
