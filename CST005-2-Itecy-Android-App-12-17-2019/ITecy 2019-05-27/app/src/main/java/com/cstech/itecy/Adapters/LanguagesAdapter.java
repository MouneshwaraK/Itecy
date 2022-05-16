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

import com.cstech.itecy.ModelClasess.LanguagesList;
import com.cstech.itecy.R;
import com.cstech.itecy.utils.Utils;

import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.MyViewHolder> {
    Context activity;
    List<LanguagesList> languagesLists;
    LayoutInflater inflater;

    public LanguagesAdapter(Context activity, List<LanguagesList> languagesLists) {

        this.activity=activity;
        this.languagesLists=languagesLists;
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

        holder.language.setText(languagesLists.get(i).getLanguageName());

        holder.headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.languagesLists.remove(i);

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return languagesLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        public TextView language,clearlanguages;
        public LinearLayout headerLayout;
        public MyViewHolder(View itemView) {
            super(itemView);

            this.language = (TextView) itemView.findViewById(R.id.skills);
            this.clearlanguages = (TextView) itemView.findViewById(R.id.clearSkills);
            this.headerLayout = (LinearLayout) itemView.findViewById(R.id.headerLayout);


        }

    }
}
