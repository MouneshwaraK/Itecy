package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.cstech.itecy.ModelClasess.CompaniesList;
import com.cstech.itecy.ModelClasess.EducationList;
import com.cstech.itecy.ModelClasess.IndustriesList;
import com.cstech.itecy.ModelClasess.SpecializationList;
import com.cstech.itecy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchCompanyAndIndustryAdapter extends BaseAdapter {


    Context context;
    List<IndustriesList> industriesLists;
    List<IndustriesList> originaindustriesLists;
    LayoutInflater inflater;
    String Filter="";

    public SearchCompanyAndIndustryAdapter(FragmentActivity activity, List<IndustriesList> industriesLists, String search_state) {
        this.context=activity;
        this.industriesLists=industriesLists;
        inflater=LayoutInflater.from(activity);
        originaindustriesLists=new ArrayList<>();
        Filter=search_state;
        try {
            originaindustriesLists.addAll(industriesLists);
        }catch (Exception e){}
    }

    Activity activity;
    List<CompaniesList> companiesLists;
    List<CompaniesList> originalcompaniesLists;

    public SearchCompanyAndIndustryAdapter(Activity activity, List<CompaniesList> companiesLists, String search_city) {

        this.context=activity;
        this.companiesLists=companiesLists;
        inflater=LayoutInflater.from(activity);
        originalcompaniesLists=new ArrayList<>();
        Filter=search_city;
        try {
            originalcompaniesLists.addAll(companiesLists);
        }catch (Exception e){}
    }

    @Override
    public int getCount() {

        if (Filter.equalsIgnoreCase("search company")){

            return companiesLists.size();
        } else {

            return industriesLists.size();
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

        if (Filter.equalsIgnoreCase("search company")){

            childItem.setText(companiesLists.get(i).getCompanyName() );
        }else {
            childItem.setText(industriesLists.get(i).getIndustryName() );
        }


        return v;
    }

    public void filterCompany(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        companiesLists.clear();
        if (charText.length() == 0) {
            companiesLists.addAll(originalcompaniesLists);
            notifyDataSetChanged();
        } else {
            for (CompaniesList Dsa : originalcompaniesLists) {

                if (Dsa.getCompanyName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    companiesLists.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (companiesLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void filterIndustry(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        industriesLists.clear();
        if (charText.length() == 0) {
            industriesLists.addAll(originaindustriesLists);
            notifyDataSetChanged();
        } else {
            for (IndustriesList Dsa : originaindustriesLists) {

                if (Dsa.getIndustryName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    industriesLists.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (industriesLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }




}
