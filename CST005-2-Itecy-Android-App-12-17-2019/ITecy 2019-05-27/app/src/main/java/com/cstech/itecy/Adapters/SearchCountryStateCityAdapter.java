package com.cstech.itecy.Adapters;

import android.app.Activity;
/*import android.content.Context;
import android.support.v4.app.FragmentActivity;*/
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.cstech.itecy.ModelClasess.CityList;
import com.cstech.itecy.ModelClasess.CountryList;
import com.cstech.itecy.ModelClasess.NationalitiesList;
import com.cstech.itecy.ModelClasess.StatesList;
import com.cstech.itecy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchCountryStateCityAdapter extends BaseAdapter {


    Context context;
    List<CountryList> countryLists;
    List<CountryList> originaNationalitiesLists;
    LayoutInflater inflater;
    String Filter="";
    public SearchCountryStateCityAdapter(Context activity, List<CountryList> countryLists) {
        this.context=activity;
        this.countryLists=countryLists;
        inflater=LayoutInflater.from(activity);
        originaNationalitiesLists=new ArrayList<>();
        try {
            originaNationalitiesLists.addAll(countryLists);
        }catch (Exception e){}
    }
    List<StatesList> statesLists;
    List<StatesList> originalStatesLists;
    public SearchCountryStateCityAdapter(FragmentActivity activity, List<StatesList> statesLists, String search_state) {
        this.context=activity;
        this.statesLists=statesLists;
        inflater=LayoutInflater.from(activity);
        originalStatesLists=new ArrayList<>();
        Filter=search_state;
        try {
            originalStatesLists.addAll(statesLists);
        }catch (Exception e){}
    }

    Activity activity;
    List<CityList> cityLists;
    List<CityList> originalCities;
    public SearchCountryStateCityAdapter(Activity activity, List<CityList> cityLists, String search_city) {

        this.context=activity;
        this.cityLists=cityLists;
        inflater=LayoutInflater.from(activity);
        originalCities=new ArrayList<>();
        Filter=search_city;
        try {
            originalCities.addAll(cityLists);
        }catch (Exception e){}
    }

    @Override
    public int getCount() {

        if (Filter.equalsIgnoreCase("search state")){

            return statesLists.size();
        }else if (Filter.equalsIgnoreCase("search city")){

            return cityLists.size();
        }else {
            return  countryLists.size();
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

        if (Filter.equalsIgnoreCase("search state")){

            childItem.setText(statesLists.get(i).getStateName() );
        }else if (Filter.equalsIgnoreCase("search city")){

            childItem.setText(cityLists.get(i).getCityName() );
        }else {
            childItem.setText(countryLists.get(i).getCountryName() );
        }


        return v;
    }

    public void filter(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        countryLists.clear();
        if (charText.length() == 0) {
            countryLists.addAll(originaNationalitiesLists);
            notifyDataSetChanged();
        } else {
            for (CountryList Dsa : originaNationalitiesLists) {

                if (Dsa.getCountryName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    countryLists.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (countryLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void filterStates(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        statesLists.clear();
        if (charText.length() == 0) {
            statesLists.addAll(originalStatesLists);
            notifyDataSetChanged();
        } else {
            for (StatesList Dsa : originalStatesLists) {

                if (Dsa.getStateName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    statesLists.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (statesLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void filterCities(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        cityLists.clear();
        if (charText.length() == 0) {
            cityLists.addAll(originalCities);
            notifyDataSetChanged();
        } else {
            for (CityList Dsa : originalCities) {

                if (Dsa.getCityName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    cityLists.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (cityLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }


}
