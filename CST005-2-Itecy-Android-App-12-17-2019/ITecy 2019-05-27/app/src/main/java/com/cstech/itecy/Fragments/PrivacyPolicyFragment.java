package com.cstech.itecy.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.cstech.itecy.BuildConfig;
import com.cstech.itecy.R;

public class PrivacyPolicyFragment extends Fragment {

    TextView webSite;
    TextView AppVersion;
    public PrivacyPolicyFragment() {
        // Required empty public constructor
    }


    WebView webView;
    TextView appVersion;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_about_us, container, false);

        webSite=(TextView)view.findViewById(R.id.webSite);
        AppVersion=(TextView)view.findViewById(R.id.AppVersion);

        AppVersion.setText(BuildConfig.VERSION_NAME);

        webSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String companyUrl="http://testitecy.azurewebsites.net/Home/privacy";
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(companyUrl));
                startActivity(intent);
            }
        });
        return view;
    }

}