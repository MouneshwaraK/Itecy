package com.cstech.itecy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cstech.itecy.Activities.RegistrationActivity;
import com.cstech.itecy.ModelClasess.CountryCode;
import com.cstech.itecy.R;
import com.cstech.itecy.interFace.Countrycode;

import java.util.ArrayList;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<CountryCode> countryCodesList = new ArrayList<>();
    String selectCountry;
    Countrycode mCallBack;
    public CountryCodeAdapter(Context context, ArrayList<CountryCode> countryCodes, String select_country,Countrycode mCallBack) {
    this.mContext = context;
    this.countryCodesList = countryCodes;
    this.selectCountry = select_country;
    this.mCallBack = mCallBack;
    }

    @NonNull
    @Override
    public CountryCodeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_country_codes,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryCodeAdapter.MyViewHolder holder, int position) {
    CountryCode countryCode = countryCodesList.get(position);
    holder.tvSelectCountryName.setText(countryCode.getCountryCode());
    }

    @Override
    public int getItemCount() {
        return countryCodesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvSelectCountryName;
        LinearLayout llcCode;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSelectCountryName = (TextView) itemView.findViewById(R.id.tv_item);
            llcCode = (LinearLayout)itemView.findViewById(R.id.ll_cCode);
            llcCode.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (selectCountry.equalsIgnoreCase("Select Country")) {
                mCallBack.setSelectCountryCode(countryCodesList.get(position).getCountryCode());
            }
        }
    }

}
