package com.cstech.itecy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cstech.itecy.ModelClasess.CountryList;
import com.cstech.itecy.ModelClasess.SkillsList;
import com.cstech.itecy.R;

import java.util.List;

public class SpinnerAdapterForCountry extends ArrayAdapter<CountryList> {

    private Context context;
    List<CountryList> myObjs;
    LayoutInflater inflater;

    public SpinnerAdapterForCountry(Context context, int textViewResourceId, List<CountryList> myObjs) {
        super(context, textViewResourceId,myObjs);
        this.context = context;
        this.myObjs = myObjs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return myObjs.size();
    }

    public CountryList getItem(int position){
        return myObjs.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
        //TextView label = new TextView(context);
        //label.setText(myObjs.get(position).getClassName());
        //return label;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
        View row = inflater.inflate(R.layout.custom_spinner, parent, false);
        TextView main_text = row.findViewById(R.id.spnitem);
        main_text.setText(myObjs.get(position).getCountryName());

        return row;
    }



}
