package com.cstech.itecy.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cstech.itecy.ModelClasess.AllCitiesList;
import com.cstech.itecy.ModelClasess.CompaniesList;
import com.cstech.itecy.R;

import java.util.List;

public class SpinnerAdapterForPreferredLocation extends ArrayAdapter<AllCitiesList> {

    private Activity context;
    List<AllCitiesList> myObjs;
    LayoutInflater inflater;

    public SpinnerAdapterForPreferredLocation(Activity context, int textViewResourceId, List<AllCitiesList> myObjs) {
        super(context, textViewResourceId,myObjs);
        this.context = context;
        this.myObjs = myObjs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return myObjs.size();
    }

    public AllCitiesList getItem(int position){
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
        main_text.setText(myObjs.get(position).getCityName());

        return row;
    }



}
