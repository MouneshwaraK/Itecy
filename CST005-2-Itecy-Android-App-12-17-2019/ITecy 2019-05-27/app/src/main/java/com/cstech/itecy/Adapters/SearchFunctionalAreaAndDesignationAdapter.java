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

import com.cstech.itecy.ModelClasess.DesignationsList;
import com.cstech.itecy.ModelClasess.EducationList;
import com.cstech.itecy.ModelClasess.FunctionalAreasList;
import com.cstech.itecy.ModelClasess.SpecializationList;
import com.cstech.itecy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFunctionalAreaAndDesignationAdapter extends BaseAdapter {


    Context context;
    List<DesignationsList> designationsLists;
    List<DesignationsList> originadesignationsLists;
    LayoutInflater inflater;
    String Filter="";

    public SearchFunctionalAreaAndDesignationAdapter(FragmentActivity activity, List<DesignationsList> designationsLists, String search_state) {
        this.context=activity;
        this.designationsLists=designationsLists;
        inflater=LayoutInflater.from(activity);
        originadesignationsLists=new ArrayList<>();
        Filter=search_state;
        try {
            originadesignationsLists.addAll(designationsLists);
        }catch (Exception e){}
    }

    Activity activity;
    List<FunctionalAreasList> functionalAreasLists;
    List<FunctionalAreasList> originalfunctionalAreasLists;
    public SearchFunctionalAreaAndDesignationAdapter(Activity activity, List<FunctionalAreasList> functionalAreasLists, String search_city) {

        this.context=activity;
        this.functionalAreasLists=functionalAreasLists;
        inflater=LayoutInflater.from(activity);
        originalfunctionalAreasLists=new ArrayList<>();
        Filter=search_city;
        try {
            originalfunctionalAreasLists.addAll(functionalAreasLists);
        }catch (Exception e){}
    }

    @Override
    public int getCount() {

        if (Filter.equalsIgnoreCase("search functional area")){

            return functionalAreasLists.size();
        } else {

            return designationsLists.size();
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

        if (Filter.equalsIgnoreCase("search functional area")){

            childItem.setText(functionalAreasLists.get(i).getFunctionalAreaName() );
        }else {
            childItem.setText(designationsLists.get(i).getDesignationName() );
        }


        return v;
    }

    public void filterFunctional(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        functionalAreasLists.clear();
        if (charText.length() == 0) {
            functionalAreasLists.addAll(originalfunctionalAreasLists);
            notifyDataSetChanged();
        } else {
            for (FunctionalAreasList Dsa : originalfunctionalAreasLists) {

                if (Dsa.getFunctionalAreaName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    functionalAreasLists.add(Dsa);
                }
                notifyDataSetChanged();
            }

            if (functionalAreasLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void filterDesignation(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        designationsLists.clear();
        if (charText.length() == 0) {
            designationsLists.addAll(originadesignationsLists);
            notifyDataSetChanged();
        } else {
            for (DesignationsList Dsa : originadesignationsLists) {

                if (charText!=null){

                    if (Dsa.getDesignationName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        designationsLists.add(Dsa);
                    }
                    notifyDataSetChanged();
                }

            }

            if (designationsLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }




}
