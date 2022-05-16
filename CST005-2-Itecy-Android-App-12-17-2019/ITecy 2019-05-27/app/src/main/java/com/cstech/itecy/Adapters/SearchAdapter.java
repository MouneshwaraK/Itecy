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

import com.cstech.itecy.ModelClasess.AllCitiesList;
import com.cstech.itecy.ModelClasess.LanguagesList;
import com.cstech.itecy.ModelClasess.SkillsList;
import com.cstech.itecy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends BaseAdapter {

    Activity activity;
    List<AllCitiesList> preferredLocationsList;
    List<AllCitiesList> orginalList;
    LayoutInflater inflater;

    List<LanguagesList> languagesLists;
    List<LanguagesList> originallanguagesLists;
    Context context;

    List<SkillsList> skillsList;
    List<SkillsList> originalskillsList;

    public SearchAdapter(Activity activity, List<AllCitiesList> preferredLocationsList) {
        this.activity=activity;
        this.preferredLocationsList=preferredLocationsList;
        inflater=LayoutInflater.from(activity);
        this.orginalList=new ArrayList<>();
        this.languagesLists=new ArrayList<>();
        this.skillsList=new ArrayList<>();
        try {
            this.orginalList.addAll(preferredLocationsList);

        } catch (Exception e) {

        }
    }

    public SearchAdapter(Context activity, List<LanguagesList> languagesLists) {
        this.context=activity;
        this.languagesLists=languagesLists;
        inflater=LayoutInflater.from(activity);
        this.originallanguagesLists=new ArrayList<>();
        this.preferredLocationsList=new ArrayList<>();
        this.skillsList=new ArrayList<>();
        try {
            this.originallanguagesLists.addAll(languagesLists);

        } catch (Exception e) {

        }
    }

    public SearchAdapter(FragmentActivity activity, List<SkillsList> skillsList) {
        this.context=activity;
        this.skillsList=skillsList;
        inflater=LayoutInflater.from(activity);
        this.originalskillsList=new ArrayList<>();
        this.preferredLocationsList=new ArrayList<>();
        this.languagesLists=new ArrayList<>();
        try {
            this.originalskillsList.addAll(skillsList);

        } catch (Exception e) {

        }
    }


    @Override
    public int getCount() {

        int size = 0;
        if (preferredLocationsList.size()>0){
            size=preferredLocationsList.size();
        }else if (languagesLists.size()>0){
            size= languagesLists.size();
        }else {
            size= skillsList.size();
        }
        return size;

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

        if (preferredLocationsList.size()>0) {
            TextView childItem = (TextView) v.findViewById(R.id.childItem);
            childItem.setText(preferredLocationsList.get(i).getCityName() + ", " + preferredLocationsList.get(i).getStateName() + ", " + preferredLocationsList.get(i).getCountryName());
        }

        if (languagesLists.size()>0) {
            TextView childItem = (TextView) v.findViewById(R.id.childItem);
            childItem.setText(languagesLists.get(i).getLanguageName() );
        }

        if (skillsList.size()>0) {
            TextView childItem = (TextView) v.findViewById(R.id.childItem);
            childItem.setText(skillsList.get(i).getSkillsName() );
        }

        return v;
    }

    public void filter(String charText) {


        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        preferredLocationsList.clear();
        if (charText.length() == 0) {
            preferredLocationsList.addAll(orginalList);
            notifyDataSetChanged();
        } else {
            for (AllCitiesList Dsa : orginalList) {
                String Skills=Dsa.getCityName();
                String skills = "";
                if (Skills!=null){
                    skills=Skills;
                }
                if (Dsa.getCityName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    preferredLocationsList.add(Dsa);

                }
                notifyDataSetChanged();
            }

            if (preferredLocationsList.size()==0){

                Toast.makeText(activity, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }





    }

    public void filterLanguages(String charText) {

        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        languagesLists.clear();
        if (charText.length() == 0) {
            languagesLists.addAll(originallanguagesLists);
            notifyDataSetChanged();
        } else {
            for (LanguagesList Dsa : originallanguagesLists) {
                String Skills=Dsa.getLanguageName();
                String skills = "";
                if (Skills!=null){
                    skills=Skills;
                }
                if (Dsa.getLanguageName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    languagesLists.add(Dsa);

                }
                notifyDataSetChanged();
            }

            if (languagesLists.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void filterSkills(String charText) {

        if (charText.length() != 0)
            charText = charText.toLowerCase(Locale.getDefault());
        skillsList.clear();
        if (charText.length() == 0) {
            skillsList.addAll(originalskillsList);
            notifyDataSetChanged();
        } else {
            for (SkillsList Dsa : originalskillsList) {
               /* String Skills=Dsa.getSkillsName();
                String skills = "";
                if (Skills!=null){
                    skills=Skills;
                }*/
                if (Dsa.getSkillsName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    skillsList.add(Dsa);

                }
                notifyDataSetChanged();
            }

            if (skillsList.size()==0){

                Toast.makeText(context, "No Data Available",Toast.LENGTH_LONG).show();
            }
        }
    }
}
