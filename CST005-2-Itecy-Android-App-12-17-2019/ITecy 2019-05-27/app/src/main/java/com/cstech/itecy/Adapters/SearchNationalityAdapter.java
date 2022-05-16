package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cstech.itecy.ModelClasess.AllCitiesList;
import com.cstech.itecy.ModelClasess.LanguagesList;
import com.cstech.itecy.ModelClasess.Nationalities;
import com.cstech.itecy.ModelClasess.NationalitiesList;
import com.cstech.itecy.ModelClasess.SkillsList;
import com.cstech.itecy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchNationalityAdapter extends BaseAdapter {


    Context context;
    List<NationalitiesList> nationalitiesLists;
    List<NationalitiesList> originaNationalitiesLists;
    LayoutInflater inflater;
    public SearchNationalityAdapter(Context activity, List<NationalitiesList> nationalitiesLists) {
        this.context=activity;
        this.nationalitiesLists=nationalitiesLists;
        inflater=LayoutInflater.from(activity);
        originaNationalitiesLists=new ArrayList<>();
        try {
            originaNationalitiesLists.addAll(nationalitiesLists);
        }catch (Exception e){}
    }

    @Override
    public int getCount() {




        return  nationalitiesLists.size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=inflater.inflate(R.layout.search_child_data,null);



            TextView childItem = (TextView) v.findViewById(R.id.childItem);
            childItem.setText(nationalitiesLists.get(i).getNationalityName() );

        return v;
    }

    public void filter(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        nationalitiesLists.clear();
        if (charText.length() == 0) {
            nationalitiesLists.addAll(originaNationalitiesLists);
            notifyDataSetChanged();
        } else {
            for (NationalitiesList Dsa : originaNationalitiesLists) {
                String Skills=Dsa.getNationalityName();
                String skills = "";
                if (Skills!=null){
                    skills=Skills;
                }
                if (Dsa.getNationalityName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    nationalitiesLists.add(Dsa);

                }
                notifyDataSetChanged();
            }

            if (nationalitiesLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }





    }


}
