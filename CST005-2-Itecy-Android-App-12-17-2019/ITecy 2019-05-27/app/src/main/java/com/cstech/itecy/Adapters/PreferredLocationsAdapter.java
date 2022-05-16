package com.cstech.itecy.Adapters;

import android.content.Context;
/*import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cstech.itecy.ModelClasess.AllCitiesList;
import com.cstech.itecy.R;
import com.cstech.itecy.utils.Utils;

import java.util.List;

public class PreferredLocationsAdapter extends RecyclerView.Adapter<PreferredLocationsAdapter.MyViewHolder> {
    Context activity;
    List<AllCitiesList> skill;
    LayoutInflater inflater;

    public PreferredLocationsAdapter(Context activity, List<AllCitiesList> skill) {

        this.activity=activity;
        this.skill=skill;
        inflater=LayoutInflater.from(activity);

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        //view.setOnClickListener(MainActivity.myOnClickListener);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.skills, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(listItem);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int i) {

        holder.skills.setText(skill.get(i).getCityName());

        holder.headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.preferredLocations.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return skill.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        public TextView skills,clearSkills;
        public LinearLayout headerLayout;
        public MyViewHolder(View itemView) {
            super(itemView);

            this.skills = (TextView) itemView.findViewById(R.id.skills);
            this.clearSkills = (TextView) itemView.findViewById(R.id.clearSkills);
            this.headerLayout = (LinearLayout) itemView.findViewById(R.id.headerLayout);


        }

    }
}
