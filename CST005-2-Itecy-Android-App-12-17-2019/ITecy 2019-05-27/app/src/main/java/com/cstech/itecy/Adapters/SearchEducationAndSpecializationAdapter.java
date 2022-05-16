package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.content.Context;
/*import android.support.v4.app.FragmentActivity;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.cstech.itecy.ModelClasess.CityList;
import com.cstech.itecy.ModelClasess.CountryList;
import com.cstech.itecy.ModelClasess.EducationList;
import com.cstech.itecy.ModelClasess.SpecializationList;
import com.cstech.itecy.ModelClasess.StatesList;
import com.cstech.itecy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchEducationAndSpecializationAdapter extends BaseAdapter {


    Context context;
    List<SpecializationList> specializationLists;
    List<SpecializationList> originaspecializationLists;
    LayoutInflater inflater;
    String Filter="";

    public SearchEducationAndSpecializationAdapter(FragmentActivity activity, List<SpecializationList> specializationLists, String search_state) {
        this.context=activity;
        this.specializationLists=specializationLists;
        inflater=LayoutInflater.from(activity);
        originaspecializationLists=new ArrayList<>();
        Filter=search_state;
        try {
            originaspecializationLists.addAll(specializationLists);
        }catch (Exception e){}
    }

    Activity activity;
    List<EducationList> degrees;
    List<EducationList> originalDegrees;
    public SearchEducationAndSpecializationAdapter(Activity activity, List<EducationList> degrees, String search_city) {

        this.context=activity;
        this.degrees=degrees;
        inflater=LayoutInflater.from(activity);
        originalDegrees=new ArrayList<>();
        Filter=search_city;
        try {
            originalDegrees.addAll(degrees);
        }catch (Exception e){}
    }

    @Override
    public int getCount() {

        if (Filter.equalsIgnoreCase("search degree")){

            return degrees.size();
        } else {

            return specializationLists.size();
        }


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

        if (Filter.equalsIgnoreCase("search degree")){

            childItem.setText(degrees.get(i).getDegree() );
        }else {
            childItem.setText(specializationLists.get(i).getEducationTypeName() );
        }


        return v;
    }

    public void filterDegree(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        degrees.clear();
        if (charText.length() == 0) {
            degrees.addAll(originalDegrees);
            notifyDataSetChanged();
        } else {
            for (EducationList Dsa : originalDegrees) {

                if (Dsa.getDegree().toLowerCase(Locale.getDefault()).contains(charText)) {
                    degrees.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (degrees.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void filterSpecialization(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        specializationLists.clear();
        if (charText.length() == 0) {
            specializationLists.addAll(originaspecializationLists);
            notifyDataSetChanged();
        } else {
            for (SpecializationList Dsa : originaspecializationLists) {

                if (Dsa.getEducationTypeName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    specializationLists.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (specializationLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }




}
