package com.cstech.itecy.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cstech.itecy.ModelClasess.SkillsList;
import com.cstech.itecy.R;
import com.cstech.itecy.utils.Utils;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.MyViewHolder> {
    Context activity;
    List<SkillsList> skill;
    LayoutInflater inflater;

    public SkillsAdapter(Context activity, List<SkillsList> skill) {

        this.activity=activity;
        this.skill=skill;
        inflater=LayoutInflater.from(activity);
        notifyDataSetChanged();
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

        holder.skills.setText(skill.get(i).getSkillsName());

        holder.headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //skill.remove(i);
                Utils.skillName.remove(i);

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
